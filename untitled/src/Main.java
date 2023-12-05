import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().run(); // No static context.
    }

    private void run() throws Exception {

        List<String> lines = Files.readAllLines(Path.of("input/input.txt"));

        List<Long> seeds = Arrays.stream(lines.remove(0).substring(7).split(" "))
                .map(Long::parseLong)
                .collect(ArrayList::new, List::add, List::addAll);

        // Parse seed-ranges for part 2:
        List<List<Long>> ranges = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i += 2) {
            ranges.add(Arrays.asList(seeds.get(i), seeds.get(i) + seeds.get(i + 1)));
        }

        List<List<Long>> updatedRanges = new ArrayList<>();
        while (!lines.isEmpty()) {
            String line = lines.remove(0);
            if (line.isEmpty()) {
                continue;
            } else if (!line.contains("map")) {

                // Process current mapping
                List<Long> mapping = Arrays.stream(line.split(" "))
                        .map(Long::parseLong)
                        .collect(ArrayList::new, List::add, List::addAll);

                List<Long> mapFrom = Arrays.asList(mapping.get(1), mapping.get(1) + mapping.get(2));
                long mappingChange = mapping.get(0) - mapping.get(1);

                for (int rangeId = 0; rangeId < ranges.size(); rangeId++) {
                    final List<Long> currentRange = ranges.get(rangeId);
                    // Detect overlap:
                    if (mapFrom.get(0) <= currentRange.get(1) && mapFrom.get(1) > currentRange.get(0)) {
                        ranges.remove(rangeId--);

                        List<Long> rangeToMap = Arrays.asList(Math.max(currentRange.get(0), mapFrom.get(0)),
                                Math.min(currentRange.get(1), mapFrom.get(1)));
                        List<Long> mappedRange = Arrays.asList(rangeToMap.get(0) + mappingChange, rangeToMap.get(1) + mappingChange);

                        updatedRanges.add(mappedRange);

                        // Add back unchanged parts:
                        if (currentRange.get(0) < rangeToMap.get(0)) {
                            ranges.add(Arrays.asList(currentRange.get(0), rangeToMap.get(0) - 1));
                        }
                        if (currentRange.get(1) > rangeToMap.get(1)) {
                            ranges.add(Arrays.asList(rangeToMap.get(1), currentRange.get(1) - 1));
                        }
                    }
                }

            } else {
                // Copy changed mapping to ranges:
                ranges.addAll(updatedRanges);
                updatedRanges.clear();
            }
        }

        // Copy changed mapping to ranges:
        ranges.addAll(updatedRanges);

        System.out.println(ranges.stream().mapToLong(r -> r.get(0)).min());
    }
}