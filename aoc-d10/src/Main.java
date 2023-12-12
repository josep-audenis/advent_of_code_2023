import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        char[][] matrix = readFromFile();
        int[] cords = new int[2];
        List<List<Integer>> search1 = new ArrayList<>();
        List<Integer> temp;
        List<List<Integer>> search2 = new ArrayList<>();
        boolean change;
        boolean found = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'S') {
                    cords[0] = i;
                    cords[1] = j;
                    found = true;
                    //System.out.println(Arrays.toString(cords));
                    break;
                }
            }
            if (found) break;
        }
        temp = new ArrayList<>();
        temp.add(cords[0]);
        temp.add(cords[1]);
        search1.add(temp);
        search2.add(temp);
        //DRETA
        if (cords[1] < matrix[cords[1]].length-1 && (matrix[cords[0]][cords[1] + 1] == '-' || matrix[cords[0]][cords[1] + 1] == '7' || matrix[cords[0]][cords[1] + 1] == 'J')) {
            temp = new ArrayList<>();
            temp.add(cords[0]);
            temp.add(cords[1]+1);
            search1.add(temp);
        }
        //ABAIX
        if (cords[0] < matrix.length-1 && (matrix[cords[0] + 1][cords[1]] == '|' || matrix[cords[0] + 1][cords[1]] == 'L' || matrix[cords[0] + 1][cords[1]] == 'J')) {
            temp = new ArrayList<>();
            temp.add(cords[0]+1);
            temp.add(cords[1]);
            if (search1.size() == 1) {
                search1.add(temp);
            } else {
                search2.add(temp);
            }
        }
        //ESQUERRA
        if (cords[1] > 0 && (matrix[cords[0]][cords[1] - 1] == '-' || matrix[cords[0]][cords[1] - 1] == 'F' || matrix[cords[0]][cords[1] - 1] == 'L')) {
            temp = new ArrayList<>();
            temp.add(cords[0]);
            temp.add(cords[1]-1);
            if (search1.size() == 1) {
                search1.add(temp);
            } else {
                search2.add(temp);
            }
        }
        //ADALT
        if (cords[0] > 0 && (matrix[cords[0] - 1][cords[1]] == '|' || matrix[cords[0] - 1][cords[1]] == 'F' || matrix[cords[0] - 1][cords[1]] == '7')) {
            temp = new ArrayList<>();
            temp.add(cords[0]-1);
            temp.add(cords[1]);
            if (search1.size() == 1) {
                search1.add(temp);
            } else {
                search2.add(temp);
            }
        }
        int cows = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[0][j] == '.') matrix[0][j] = 'o';
                if (matrix[j][0] == '.') matrix[j][0] = 'o';
                if (matrix[matrix.length-1][j] == '.') matrix[matrix.length-1][j] = 'o';
                if (matrix[j][matrix[j].length-1] ==  '.') matrix[j][matrix[j].length-1] = 'o';
                System.out.print(matrix[i][j] + "[" + i+ "][" + j + "] ");
                change = true;
                if (matrix[i][j] == '.'){
                    if (matrix[i-1][j-1] == 'o'){
                        change = false;
                    }
                    if (matrix[i-1][j] == 'o'){
                        change = false;
                    }
                    if (matrix[i-1][j+1] == 'o'){
                        change = false;
                    }
                    if (matrix[i][j-1] == 'o'){
                        change = false;
                    }
                    if (matrix[i][j] == 'o'){
                        change = false;
                    }
                    if (matrix[i][j+1] == 'o'){
                        change = false;
                    }
                    if (matrix[i+1][j-1] == 'o'){
                        change = false;
                    }
                    if (matrix[i+1][j] == 'o'){
                        change = false;
                    }
                    if (matrix[i+1][j+1] == 'o'){
                        change = false;
                    }
                    if (change){
                        matrix[i][j] = 'I';
                        cows++;
                    }else{
                        matrix[i][j] = 'o';
                    }
                }
            }
            System.out.println();
        }

        found = false;
        int count = 1;
        do {
            nextCoords(matrix,search1);
            nextCoords(matrix,search2);
            count++;
            if (search1.get(search1.size() - 1).equals(search2.get(search2.size() - 1))){
                found = true;
            }
        } while (!found);
        System.out.println(count);
        System.out.println(cows);
    }

    public static char[][] readFromFile() {
        char[][] matrix;
        String rawLines = "";
        String line;
        int index = 0;
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
            matrix = new char[convertedLines.length][];
            for (int i = 0; i < convertedLines.length; i++) {
                matrix[i] = convertedLines[i].toCharArray();
            }

        } catch (IOException e) {
            System.out.println("Can't open file :(");
            return null;
        }
        return matrix;
    }

    public static void nextCoords(char[][] matrix, List<List<Integer>> search) {
        List<Integer> temp = new ArrayList<>();
        int[] actual = new int[2];
        int[] prev = new int[2];
        actual[0] = search.get(search.size()-1).get(0);
        actual[1] = search.get(search.size()-1).get(1);
        prev[0] = search.get(search.size()-2).get(0);
        prev[1] = search.get(search.size()-2).get(1);
        switch(matrix[actual[0]][actual[1]]){
            case '-':
                if (actual[1] > prev[1]){
                    temp.add(actual[0]);
                    temp.add(actual[1]+1);
                }else{
                    temp.add(actual[0]);
                    temp.add(actual[1]-1);
                }
                search.add(temp);
                break;
            case '|':
                if (actual[0] > prev[0]){
                    temp.add(actual[0]+1);
                    temp.add(actual[1]);
                }else {
                    temp.add(actual[0] - 1);
                    temp.add(actual[1]);
                }
                search.add(temp);
                break;
            case '7':
                if (actual[0] == prev[0]){
                    temp.add(actual[0]+1);
                    temp.add(actual[1]);
                }else{
                    temp.add(actual[0]);
                    temp.add(actual[1]-1);
                }
                search.add(temp);
                break;
            case 'J':
                if (actual[0] == prev[0]){
                    temp.add(actual[0]-1);
                    temp.add(actual[1]);
                }else{
                    temp.add(actual[0]);
                    temp.add(actual[1]-1);
                }
                search.add(temp);
                break;
            case 'L':
                if (actual[0] == prev[0]){
                    temp.add(actual[0]-1);
                    temp.add(actual[1]);
                }else{
                    temp.add(actual[0]);
                    temp.add(actual[1]+1);
                }
                search.add(temp);
                break;
            case 'F':
                if (actual[0] == prev[0]){
                    temp.add(actual[0]+1);
                    temp.add(actual[1]);
                }else{
                    temp.add(actual[0]);
                    temp.add(actual[1]+1);
                }
                search.add(temp);
                break;
        }
    }
}