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

        String at = "";
        String ad = "";

        int total = 1;
        int beat;
        long travel;
        long totalTime = 0;
        long totalDistance = 0;

        first = new ArrayList<>(Arrays.asList(first.get(1).split(" ")));
        first.removeAll(Collections.singleton(""));
        last = new ArrayList<>(Arrays.asList(last.get(1).split(" ")));
        last.removeAll(Collections.singleton(""));

        for (int i = 0; i < first.size(); i++){
            //time.add(Integer.parseInt(first.get(i)));
            ad += last.get(i);
            at += first.get(i);
            //distance.add(Integer.parseInt(last.get(i)));
        }
        totalDistance = Long.parseLong(ad);
        totalTime = Long.parseLong(at);

        System.out.println("d:" +totalDistance);
        System.out.println("t" + totalTime);

        //for (int i = 0; i < time.size();i++){
        beat = 0;
        for (long j = 0; j < totalTime; j++){
            travel = j*(totalTime-j);
            System.out.println("travel: " + travel);
            if (travel > totalDistance) beat++;
            System.out.println("beat: " + beat);
        }
        System.out.println("total: " + total);
        total *= beat;
        //}
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