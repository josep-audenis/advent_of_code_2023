import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";
    public static void main(String[] args) {
        List<String> lines = readFromFile();
        List<String> first = (Arrays.asList(lines.get(0).split(":")));
        List<String> last = (Arrays.asList(lines.get(1).split(":")));
        List<Integer> time = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        int total = 1;
        int beat;
        int travel;
        first = new ArrayList<>(Arrays.asList(first.get(1).split(" ")));
        first.removeAll(Collections.singleton(""));
        last = new ArrayList<>(Arrays.asList(last.get(1).split(" ")));
        last.removeAll(Collections.singleton(""));

        for (int i = 0; i < first.size(); i++){
            time.add(Integer.parseInt(first.get(i)));
            distance.add(Integer.parseInt(last.get(i)));
        }

        for (int i = 0; i < time.size();i++){
            beat = 0;
            for (int j = 0; j < time.get(i); j++){
                travel = j*(time.get(i)-j);
                System.out.println("travel: " + travel);
                if (travel > distance.get(i)) beat++;
                System.out.println("beat: " + beat);
            }
            System.out.println("total: " + total);
            total *= beat;
        }
        System.out.println(total);



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