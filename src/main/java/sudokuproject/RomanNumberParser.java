package sudokuproject;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberParser implements NumberParser<Integer> {
    private static final Map<Character, Integer> ROMAN_TO_INT = new HashMap<>();

    // Erstellt Map für die Klasse, welche alle relevanten Römischen Zahlen mit arabischen Gegenstück mappt
    // Für Sudoku werden nur Zahlen von I bis X verwendet
    static {
        ROMAN_TO_INT.put('I', 1);
        ROMAN_TO_INT.put('V', 5);
        ROMAN_TO_INT.put('X', 10);
    }

    // Umwandlung zu Arabischer Zahl 
    // Besteht die Zahl aus zwei oder mehr Ziffern gibt es folgende Fälle:
    // Ist die erste Ziffer kleiner, so wird sie von der Zweiten abgezogen
    // Ist die zweite Ziffer größer, so werden die Zahlen addiert
    @Override
    public Integer parse(String input) {
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            int s1 = ROMAN_TO_INT.getOrDefault(input.charAt(i), 0);
            if (i + 1 < input.length()) {
                int s2 = ROMAN_TO_INT.getOrDefault(input.charAt(i + 1), 0);
                if (s1 >= s2) {
                    result = result + s1;
                } else {
                    result = result + s2 - s1;
                    i++;
                }
            } else {
                result = result + s1;
            }
        }
        return result;
    }

}
