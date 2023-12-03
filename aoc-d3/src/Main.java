import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        int number = 1;
        int sum = 0;
        char[][] matrix = readFromFile();
        System.out.println(matrix.length);
        System.out.println(matrix[1].length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "[" + i + "]" + "[" + j + "]\t");
            }
            System.out.println();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!Character.isDigit(matrix[i][j]) && !Character.isAlphabetic(matrix[i][j]) && matrix[i][j] != '.') {
                    if (Character.isDigit(matrix[i][j - 1])) sum += findNumber(matrix, i, j - 1);
                    if (Character.isDigit(matrix[i][j + 1])) sum += findNumber(matrix, i, j + 1);
                    if (Character.isDigit(matrix[i - 1][j])) sum += findNumber(matrix, i - 1, j);
                    else{
                        if (Character.isDigit(matrix[i - 1][j - 1])) sum += findNumber(matrix, i - 1, j - 1);
                        if (Character.isDigit(matrix[i - 1][j + 1])) sum += findNumber(matrix, i - 1, j + 1);
                    }
                    if (Character.isDigit(matrix[i + 1][j])) sum += findNumber(matrix, i + 1, j);
                    else{
                        if (Character.isDigit(matrix[i + 1][j - 1])) sum += findNumber(matrix, i + 1, j - 1);
                        if (Character.isDigit(matrix[i + 1][j + 1])) sum += findNumber(matrix, i + 1, j + 1);
                    }


                }
            }
        }
        System.out.println(sum);
    }


    public static char[][] readFromFile () {
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

    public static int findNumber( char[][] matrix, int i, int j){
        System.out.println("\n" + matrix[i][j] + "[" + i + "][" + j + "]");
        boolean left = false;
        int flag = 1;
        int newJ;
        boolean right = false;
        String number = String.valueOf(matrix[i][j]);
        while (!left) {
            if (j > 0) {
                newJ = j-flag;
                if (Character.isDigit(matrix[i][newJ])) {
                    System.out.println(number + " += " + Character.getNumericValue(matrix[i][newJ]) * 10);
                    number = (matrix[i][newJ]) + number;
                    System.out.println("left - " + (newJ) + " = " + matrix[i][newJ] + " -> " + number);
                    if (newJ > 0) flag++;
                    else break;
                } else break;
            } else break;
        }
        flag = 1;
        while (!right) {
            if (j < matrix[i].length - 1) {
                newJ = j+flag;
                if (Character.isDigit(matrix[i][newJ])) {
                    number = number + Character.getNumericValue(matrix[i][newJ]);
                    System.out.println("right - " + (newJ) + " = " + matrix[i][newJ] + " -> " + number);
                    if (newJ < matrix[i].length - 1) flag++;
                    else break;
                } else break;
            } else break;
        }
        return Integer.parseInt(number);
    }

}