import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "input/input.txt";

    public static void main(String[] args) {
        List<List<String>> hands = new ArrayList<>();
        int sum = 0;
        List<String> lines = readFromFile();
        String[] parts;
        for (String line : lines){
            List<String> hand = new ArrayList<>();
            parts = line.split(" ");
            hand.add( parts[0]);
            hand.add( parts[1]);
            hands.add(hand);
        }
        hands = selectionSort(hands);
        for (int i = 0; i < hands.size();i++){
            System.out.println(hands.size()-i + ") " + hands.get(i) + " " + (i+1)*Integer.parseInt(hands.get(i).getLast()));
            sum += (i+1)*Integer.parseInt(hands.get(i).getLast());
            System.out.println("\t" + sum);

        }
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

    private static List<List<String>> selectionSort(List<List<String>> hands){
        int minIndex = 0;
        for(int i = 0; i < hands.size() - 1; i++){
            minIndex = i;
            for(int j = i + 1; j < hands.size(); j++){
                if(!compareHands(hands.get(j), hands.get(minIndex))) {  //compareHands(hands.get(j), hands.get(minIndex))  -  Integer.parseInt(hands.get(j).getLast()) > Integer.parseInt(hands.get(minIndex).getLast())
                    minIndex = j;
                }
            }
            List<String> temp = hands.get(minIndex);
            hands.set(minIndex, hands.get(i));
            hands.set(i, temp);
        }

        return hands;
    }

    public static boolean compareHands(List<String> hand1, List<String> hand2){
        int first1 = 0;
        int first2 = 0;
        int firstJ1 = 0;
        int firstJ2 = 0;
        int second1 = 0;
        int second2 = 0;
        int secondJ1 = 0;
        int secondJ2 = 0;
        int index1 = 0;
        int index2 = 0;
        List<String> cardValues = new ArrayList<>();
        cardValues.add("A");
        cardValues.add("K");
        cardValues.add("Q");
        cardValues.add("T");
        cardValues.add("9");
        cardValues.add("8");
        cardValues.add("7");
        cardValues.add("6");
        cardValues.add("5");
        cardValues.add("4");
        cardValues.add("3");
        cardValues.add("2");
        cardValues.add("J");
        for (int i = 0; i < cardValues.size()-1; i++){
            if (countOccurrences(hand1.getFirst(),cardValues.get(i)) > first1){
                second1 = first1;
                first1 = countOccurrences(hand1.getFirst(),cardValues.get(i));
            } else if (countOccurrences(hand1.getFirst(),cardValues.get(i)) > second1) second1 = (countOccurrences(hand1.getFirst(),cardValues.get(i)));
            if ((countOccurrences(hand1.getFirst(),cardValues.get(i)) + countOccurrences(hand1.getFirst(),"J")) > firstJ1){
                secondJ1 = firstJ1;
                firstJ1 = countOccurrences(hand1.getFirst(),cardValues.get(i)) + countOccurrences(hand1.getFirst(),"J");
            } else if ((countOccurrences(hand1.getFirst(),cardValues.get(i)) + countOccurrences(hand1.getFirst(),"J")) > secondJ1) secondJ1 = (countOccurrences(hand1.getFirst(),cardValues.get(i))) + countOccurrences(hand1.getFirst(),"J");

            if (countOccurrences(hand2.getFirst(),cardValues.get(i)) > first2){
                second2 = first2;
                first2 = countOccurrences(hand2.getFirst(),cardValues.get(i));
            } else if (countOccurrences(hand2.getFirst(),cardValues.get(i)) > second2) second2 = (countOccurrences(hand2.getFirst(),cardValues.get(i)));
            if ((countOccurrences(hand2.getFirst(),cardValues.get(i)) + countOccurrences(hand2.getFirst(),"J")) > firstJ2){
                secondJ2 = firstJ2;
                firstJ2 = countOccurrences(hand2.getFirst(),cardValues.get(i)) + countOccurrences(hand2.getFirst(),"J");
            } else if ((countOccurrences(hand2.getFirst(),cardValues.get(i)) + countOccurrences(hand2.getFirst(),"J")) > secondJ2) secondJ2 = (countOccurrences(hand2.getFirst(),cardValues.get(i))) + countOccurrences(hand2.getFirst(),"J");

        }
        if (firstJ1 < firstJ2) return false;
        else if (firstJ1 > firstJ2) return true;
        else if (firstJ1 < 4 && second1 < second2) return false;
        else if (firstJ1 < 4 && second1 > second2) return true;
        else {
            for (int i = 0; i < hand1.getFirst().length(); i++){
                index1 = cardValues.indexOf(String.valueOf(hand1.get(0).charAt(i)));
                index2 = cardValues.indexOf(String.valueOf(hand2.get(0).charAt(i)));
                if (index1 > index2) return false;
                else if (index1 < index2) return true;
            }
        }
        return false;
    }

    public static int countOccurrences(String string, String value){
        int count = 0;
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) == value.charAt(0)){
                count++;
            }
        }
        return count;
    }
}