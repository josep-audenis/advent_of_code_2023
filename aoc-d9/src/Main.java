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
        List<String> file= readFromFile();
        int sum = 0;
        boolean end = false;
        int prediction = 0;
        for (String line : file){
            List<String> split = Arrays.asList(line.split(" "));
            List<List<Integer>> sequence = new ArrayList<>();
            List<Integer> history = new ArrayList<>();
            for (String num : split){
                history.add(Integer.parseInt(num));
            }
            sequence.add(history);

            do{
                end = true;
                List<Integer> subHistory = new ArrayList<>();
                for (int i = 1; i < history.size(); i++){
                    subHistory.add(history.get(i)-history.get(i-1));
                    if (history.get(i)-history.get(i-1) != 0) end = false;
                }
                sequence.add(subHistory);
                history = new ArrayList<>();
                history = subHistory;
            }while(!end);
            for (int i = 1; i < sequence.size()+1; i++){
                //prediction += sequence.get(sequence.size()-i).getLast();
                prediction = sequence.get(sequence.size()-i).getFirst() - prediction;
            }
            sum += prediction;
            prediction = 0;
        }
        System.out.println(sum);

    }

    public static List<String> readFromFile(){
        try{
            return Files.readAllLines(Path.of(FILE_PATH));
        }catch (IOException e){
            System.out.println("Can't open file :(");
            return null;
        }
    }
}