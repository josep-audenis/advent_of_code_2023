import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        long arr[][];
        int column = -1;
        long destination = 0;
        long source = 0;
        long range = 0;
        long diference = 0;
        int nSeeds = 0;
        List<String> lines;
        lines = readFromFile();
        String firstLine = lines.getFirst();
        String[] parts = firstLine.split(":");
        parts = parts[1].split(" ");
        nSeeds = parts.length-1;
        arr = new long[nSeeds][8];
        for (int i = 0; i < nSeeds;i++){
            arr[i][0] = Long.parseLong(parts[i+1]);
        }
        for (String line : lines){
            System.out.println(line);
            if (!line.isEmpty() && Character.isAlphabetic(line.charAt(0))){
                if (column > 0){
                    arr = completeZeros(arr,nSeeds,column);
                }
                column++;

            }
            else if (!line.isEmpty() && Character.isDigit(line.charAt(0))){
                parts = line.split(" ");
                destination = Long.parseLong(parts[0]);
                source = Long.parseLong(parts[1]);
                range = Long.parseLong(parts[2]);
                diference = destination-source;
                for (int i = 0; i < nSeeds; i++){
                    if (arr[i][column-1] >= source && arr[i][column-1] <= source+range && arr[i][column] == 0){
                        arr[i][column] = arr[i][column-1]+diference;
                    }
                }
            }
            for (int i = 0; i < nSeeds; i++){
                for (int j = 0; j < 8; j++){
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("------------------");

        }
        arr = completeZeros(arr,nSeeds,column);

        System.out.println();
        for (int i = 0; i < nSeeds; i++){
            for (int j = 0; j < 8; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println(lookForSmallest(arr,nSeeds));

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

    public static long[][] completeZeros(long[][] arr, int nSeeds, int column){
        for (int i = 0; i < nSeeds; i++){
            if (arr[i][column] == 0) arr[i][column] = arr[i][column-1];
        }
        return arr;
    }

    public static long lookForSmallest(long[][] arr, int nSeeds){
        long smallest = arr[0][7];
        for (int i = 0; i < nSeeds; i++){
            if (arr[i][7] < smallest) smallest = arr[i][7];
        }
        return smallest;
    }
}