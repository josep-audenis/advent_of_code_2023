import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String text = null;
        int first;
        int last;
        int calibration = 0;

        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("one","1");
        dictionary.put("two","2");
        dictionary.put("three","3");
        dictionary.put("four","4");
        dictionary.put("five","5");
        dictionary.put("six","6");
        dictionary.put("seven","7");
        dictionary.put("eight","8");
        dictionary.put("nine","9");

        while (s.hasNext()) {
            text = s.nextLine();
            first = Character.getNumericValue(findChar(text, true, dictionary));
            last = Character.getNumericValue(findChar(text, false, dictionary));
            calibration += first*10 + last;
            System.out.print(text + ": " + first + " + " + last + " = " + first + last + " -> " + calibration + "\n");
        }
    }


    public static char findChar(String text, boolean forward, Map<String, String> dictionary) {
        String newText = "";
        String numberText = "";
        boolean letter = false;
        boolean number = false;
        int flag = 0;
        boolean end = false;
        while(flag < text.length()){
            number = false;
            if (Character.isDigit(text.charAt(flag))){
                newText += text.charAt(flag);
                if (!end) flag++;
                if (end) break;
            }
            else{
                for (String entry : dictionary.keySet()){
                    for (int i = 0; i < entry.length(); i++){
                        char entrychar = entry.charAt(i);
                        char textchar = text.charAt(flag);
                        if (entry.charAt(i) == text.charAt(flag)){
                            letter = true;
                            numberText += entry.charAt(i);
                            if (flag == text.length()-1) end = true;
                            if (!end) flag++;
                        } else {
                            letter = false;
                            if (i > 0) {
                                flag -= i;

                            }
                            if (Character.isDigit(text.charAt(flag))){
                                number = true;
                            }
                            numberText = "";
                            break;
                        }
                    }
                    if (letter) {
                        if (dictionary.containsKey(numberText)) {
                            newText += dictionary.get(numberText);
                            flag--;
                            numberText = "";
                            break;
                        }
                    }
                    if (number) break;
                    if (end) break;
                }
                if (!letter && !number){
                    if (!end) flag++;
                }
            }
            if (end) break;
            if (flag == text.length()-1) end = true;
        }
        if (forward) return newText.charAt(0);
        else return newText.charAt(newText.length()-1);
    }
}