import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        int sum = 0;
        List<String> lines = readFromFile();
        for (String line : lines) {
            int pos = 0;
            String[] splited = line.split(" ");
            String spring = splited[0];
            
            int[] nums = new int[splited[1].split(",").length];
            for (int i = 0; i < splited[1].split(",").length; i++) {
                int num = Integer.parseInt(splited[1].split(",")[i]);
                nums[i] = num;
            }
            //System.out.print(spring + " - ");
            for (int num : nums) {
                //System.out.print(num + ",");
            }
            pos = cercaDinamica(0, 0, spring, nums, new HashMap<>());
            //System.out.println( " = " + pos);
            sum += pos;
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

    public static int cercaDinamica(int p, int f, String cadena, int[] nums, Map<String, Integer> map){
        int res = 0;
        String key;
        if (p >= cadena.length()){
            if (f == nums.length) return 1;
            else return 0;
        }
        key = p + " - " + f;
        if (map.containsKey(key)){
            return map.get(key);
        }
        if (cadena.charAt(p) == '.' || cadena.charAt(p) == '?'){
            res += cercaDinamica(p+1, f, cadena, nums, map);
        }
        if (f < nums.length && (cadena.charAt(p) == '#' || cadena.charAt(p) == '?') &&
                (p + nums[f] <= cadena.length() && cadena.substring(p, p + nums[f]).indexOf('.') == -1) &&
                (p + nums[f] == cadena.length() || cadena.charAt(p + nums[f]) != '#')){
            res += cercaDinamica(p + nums[f]+1, f+1, cadena, nums, map);
        }
        map.put(key, res);
        return res;
    }
}