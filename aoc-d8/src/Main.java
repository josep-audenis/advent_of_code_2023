import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input = readFromFile();
        List<List<String>> nodes = new ArrayList<>();
        List<Character> instructions = new ArrayList<>();
        int count = 0;
        for (char ch : input.get(0).toCharArray()){
            instructions.add(ch);
        }
        input.remove(1);
        input.remove(0);
        for (String n : input){
            String[] temp = n.split(" = ");
            List<String> node = new ArrayList<>();
            node.add(temp[0]);
            temp = temp[1].split(", ");
            node.add(temp[0].replace("(", ""));
            node.add(temp[1].replace(")", ""));
            nodes.add(node);
        }
        int j = 0;
        List<String> actualNode = new ArrayList<>();
        for (List<String> node : nodes){
            if (node.getFirst().equals("AAA")){
                actualNode = node;
                break;
            }
        }
        while (true){
            for (char instruction : instructions){
                System.out.println(instruction + " - " + j + " - " + count + " - " + actualNode);
                j++;
                for (int i = 0; i < nodes.size(); i++){
                    List<String> comparingNode = nodes.get(i);
                    if (actualNode.getFirst().equals("ZZZ")){
                        break;
                    }
                    //System.out.println(actualNode  + "?=" + comparingNode.getFirst());
                    if (instruction == 'L') {
                        if (actualNode.get(1).equals(comparingNode.getFirst())) {
                            actualNode = comparingNode;
                            count++;
                            break;
                        }
                        if (i == nodes.size()-1) i = -1;
                        continue;
                    }
                    if (instruction == 'R') {
                        if (actualNode.getLast().equals(comparingNode.getFirst())) {
                            actualNode = comparingNode;
                            count++;
                            break;
                        }
                        if (i == nodes.size()-1) i = 0;
                        continue;
                    }
                }
            }
            if (actualNode.getFirst().equals("ZZZ")){
                break;
            }
        }
        System.out.println(count);


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