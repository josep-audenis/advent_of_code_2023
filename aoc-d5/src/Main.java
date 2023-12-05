import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        List<String> lines = readFromFile();
        List<List<Long>> ranges = new ArrayList<>();
        List<List<Long>> newRanges = new ArrayList<>();
        List<Long> seeds = new ArrayList<>();
        List<Long> mmRange;
        List<Long> mmRangeLength;

        long destination = 0;
        long source = 0;
        long rLength = 0;
        long difference = 0;
        long minValue = 0;

        String firstLine = lines.getFirst();
        String[] parts = firstLine.split(":");
        String[] seedString = parts[1].split(" ");
        lines.remove(0);

        for(int i = 1; i < seedString.length; i++){
            seeds.add(Long.parseLong(seedString[i]));
        }
        for (int i = 0; i < seeds.size(); i += 2){
            ranges.add(Arrays.asList(seeds.get(i), seeds.get(i) + seeds.get(i+1)));
        }

        while (!lines.isEmpty()){
            String line = lines.remove(0);
            if (line.isEmpty()){
                continue;
            } else if (!Character.isAlphabetic(line.charAt(0))){
                parts = line.split(" ");
                destination = Long.parseLong(parts[0]);
                source = Long.parseLong(parts[1]);
                rLength = Long.parseLong(parts[2]);
                difference = destination-source;
                List<Long> convert = Arrays.asList(source, source+rLength);

                for (int i = 0; i < ranges.size(); i++){
                    final List<Long> current = ranges.get(i);
                    if (source <= current.get(1) && source+rLength > current.get(0)){
                        ranges.remove(i--);

                        mmRange = Arrays.asList(Math.max(current.get(0), convert.get(0)),
                                Math.min(current.get(1), convert.get(1)));
                        mmRangeLength = Arrays.asList(mmRange.get(0) + difference, mmRange.get(1) + difference);

                        newRanges.add(mmRangeLength);

                        if (current.get(0) < mmRange.get(0)){
                            ranges.add(Arrays.asList(current.get(0), mmRange.get(0) - 1));
                        }
                        if (current.get(1) > mmRange.get(1)){
                            ranges.add(Arrays.asList(mmRange.get(1), current.get(1) - 1));
                        }
                    }
                }
            } else {
                ranges.addAll(newRanges);
                newRanges.clear();
            }
        }
        ranges.addAll(newRanges);
        System.out.println(ranges.stream().mapToLong(r -> r.get(0)).min());
    }

    public static List<String> readFromFile(){
        try{
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            return lines;
        }catch (IOException e){
            System.out.println("Can't open file :(");
            return null;
        }
    }
}