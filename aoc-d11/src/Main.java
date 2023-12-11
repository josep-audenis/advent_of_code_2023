import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";
    public static void main(String[] args) {
        List<List<Character>> matrix = readFromFile();
        List<List<Character>> mat = new ArrayList<>();
        List<List<Integer>> originalGalaxies = new ArrayList<>();
        List<List<Integer>> galaxiesTemp = new ArrayList<>();
        List<List<Integer>> galaxiesEnd = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        int diferencex;
        int diferencey;
        boolean empty;
        for (int i = 0; i < matrix.size(); i++){
            mat.add(matrix.get(i));
            empty = true;
            for (int j = 0; j < matrix.get(i).size(); j++){
                if (matrix.get(i).get(j) == '#'){
                    empty = false;
                    List<Integer> cords = new ArrayList<>();
                    cords.add(i);
                    cords.add(j);
                    originalGalaxies.add(cords);
                }
            }
            if (empty) {
                mat.add(matrix.get(i));
            }
        }
        for (int i = 0; i < matrix.get(0).size(); i++){
            empty = true;
            for (int j = 0; j < matrix.size(); j++){
                if (matrix.get(j).get(i) == '#') {
                    empty = false;
                    break;
                }
            }
            if (empty){
                if (i+1 < matrix.get(0).size()){
                    for (int j = 0; j < matrix.size(); j++){
                        matrix.get(j).add(i, '.');
                    }
                    i++;
                }
            }
        }
        for (int i = 0; i < mat.size(); i++) {
            for (int j = 0; j < mat.get(i).size(); j++) {
                if (mat.get(i).get(j) == '#'){
                    List<Integer> cords = new ArrayList<>();
                    cords.add(i);
                    cords.add(j);
                    galaxiesTemp.add(cords);
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < galaxiesTemp.size(); i++){
            diferencex = galaxiesTemp.get(i).get(0) - originalGalaxies.get(i).get(0);
            diferencey = galaxiesTemp.get(i).get(1) - originalGalaxies.get(i).get(1);
            List<Integer> cords = new ArrayList<>();
            cords.add(originalGalaxies.get(i).get(0) + Math.abs(diferencex)*999999);
            cords.add(originalGalaxies.get(i).get(1) + Math.abs(diferencey)*999999);
            galaxiesEnd.add(cords);
        }
        for (int i = 0; i < galaxiesEnd.size()-1; i++){
            for (int j = i+1 ; j < galaxiesEnd.size(); j++){
                distances.add((manhattan(galaxiesEnd.get(i), galaxiesEnd.get(j))));
                sum += (manhattan(galaxiesEnd.get(i), galaxiesEnd.get(j)));
            }
        }
        System.out.println(sum);
    }

    public static List<List<Character>> readFromFile() {
        List<List<Character>> mat = new ArrayList<>();
        String rawLines = "";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            while ((line = reader.readLine()) != null) {
                rawLines += "\n" + line;
            }
            reader.close();
            String[] convertedLines = rawLines.split("\n");
            for (int i = 1; i < convertedLines.length; i++) {
                convertedLines[i - 1] = convertedLines[i];
            }
            List<Character> temp = new ArrayList<>();
            for (int i = 0; i < convertedLines.length-1; i++) {
                temp = new ArrayList<>();
                for (int j = 0; j < convertedLines[i].length(); j++){
                    temp.add((char) convertedLines[i].charAt(j));
                }
                mat.add(temp);
            }
        } catch (IOException e) {
            System.out.println("Can't open file :(");
            return null;
        }
        return mat;
    }

    public static int manhattan(List<Integer> origin, List<Integer> end){
        int x1 = origin.get(0);
        int x2 = end.get(0);
        int y1 = origin.get(1);
        int y2 = end.get(1);
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}