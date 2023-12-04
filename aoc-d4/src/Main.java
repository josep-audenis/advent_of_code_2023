import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        List<String> lines;
        lines = readFromFile();
        String[] initial;
        String[] separator;
        String[] first;
        String[] second;
        int equals = 0;
        int points = 0;
        int[] winning;
        int[] played;
        for (String line: lines){
            equals = 0;
            initial = line.split(":");
            separator = initial[1].split("\\|");
            first = cleanArray(separator[0].split(" "));
            second = cleanArray(separator[1].split(" "));
            winning = getNumbersArray(first);
            played = getNumbersArray(second);
            for (int number: winning){
                for (int compare: played){
                    if (number == compare) equals++;
                }
            }
            points += Math.pow(2,equals-1);

        }
        System.out.println(points);

    }

    public static int[] getNumbersArray(String[] line){
        int[] array = new int[line.length];
        for (int i = 0; i < line.length;i++){
            array[i] = Integer.parseInt(line[i]);
        }
        return array;
    }

    public static List<String> readFromFile(){
        try{
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            return lines;
        }catch (IOException e){
            System.out.println("Cam't open file :(");
            return null;
        }
    }

    public static String[] cleanArray(String[] string){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(string));
        arrayList.removeIf(String::isEmpty);
        return arrayList.toArray(new String[0]);

    }
}