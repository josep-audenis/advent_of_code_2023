import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static final String FILE_PATH = "input/input.txt";
    private static final int SIZE = 10;

    public static void main(String[] args) {
        List<String> lines;
        lines = readFromFile();
        String[] initial;
        String[] separator;
        String[] first;
        String[] second;
        int equals = 0;
        int sum = 0;
        int actualCopies = 1;
        int[] winning;
        int[] played;
        int[] copies = new int[SIZE];
        for (int i = 0; i < SIZE; i++){
            copies[i] = 1;
        }
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
            actualCopies = copies[0];
            for (int i = 1; i < SIZE; i++){
                copies[i-1] = copies[i];
            }
            copies[SIZE-1] = 1;
            for (int i = 0; i < equals; i++){
                copies[i] += actualCopies;
            }
            //sum += Math.pow(2,equals-1);
            sum += actualCopies;
        }
        System.out.println(sum);

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