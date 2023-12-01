import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String cadena = null;
        int first;
        int last;
        int calibration = 0;
        while (s.hasNext()) {
            cadena = s.nextLine();
            first = Character.getNumericValue(findChar(cadena, 1));
            last = Character.getNumericValue(findChar(cadena, 0));
            calibration += first*10 + last;
            System.out.print(cadena + ": " + first + " + " + last + " = " + first + last + " -> " + calibration + "\n");
        }
    }
    public static char findChar(String cadena, int forward){
        char caracter;
        for  (int i = 0; i < (cadena.length()); i++){
            if (forward == 1) caracter = cadena.charAt(i);
            else caracter = cadena.charAt(cadena.length() - 1 - i);
            if (Character.isDigit(caracter)) return caracter;
        }
        return '\0';
    }
}