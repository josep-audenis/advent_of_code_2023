import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        int id;
        int idSum = 0;
        int power = 0;
        char color;
        boolean valid = true;
        boolean two = false;
        int red = 0;
        int blue = 0;
        int green = 0;
        List<String> lines;
        lines = readFromFile();
        String[] parts_id;
        String[] parts_cubs;
        String[] parts_games;
        int number = 0;
        for (String line : lines){
            System.out.print(line + " - ");
            parts_id = line.split(":");
            if (parts_id[0].length() > 6) {
                id = Character.getNumericValue(parts_id[0].charAt(5))*10+Character.getNumericValue(parts_id[0].charAt(6));
            }
            else id = Character.getNumericValue(parts_id[0].charAt(5));
            parts_games = parts_id[1].split(";");
            valid = true;
            red = 0;
            blue = 0;
            green = 0;
            for (String game : parts_games){
                parts_cubs = game.split(",");
                for (String cubs : parts_cubs){
                    two = false;
                    color = cubs.charAt(3);
                    if (color == ' '){
                        color = cubs.charAt(4);
                        two = true;
                    }
                    switch (color){
                        case 'b':
                            number = getNumber(two,cubs);
                            if (number > blue) blue = number;
                            //if (number > MAX_BLUE) valid = false;
                            break;
                        case 'r':
                            number = getNumber(two,cubs);
                            if (number > red) red = number;
                            //if (number > MAX_RED) valid = false;
                            break;
                        case 'g':
                            number = getNumber(two,cubs);
                            if (number > green) green = number;
                            //if (number > MAX_GREEN) valid = false;
                            break;
                        default:
                            System.out.println("T'has equivocat " + cubs +"(" + color + ")");
                            break;
                    }
                    //if (!valid) break;
                }
                //if (!valid) break;
            }
            /*
            if (valid){
                idSum += id;
            }
            System.out.println(valid);
             */
            int total = (red*green*blue);
            System.out.println("blue: " + blue + " green: " + green + " red: "+ red+ " total: " + total);
            power += total;
        }
        System.out.println(power);
        //System.out.println(idSum);



    }

    public static int getNumber(boolean two, String cubs){
        if (two){
            return Character.getNumericValue(cubs.charAt(1))*10+Character.getNumericValue(cubs.charAt(2));
        } else return Character.getNumericValue(cubs.charAt(1));
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
}