package sudokuproject;

public class ArabicNumberParser implements NumberParser<Integer> {
    @Override
    public Integer parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0; // Ungültige Eingabe
        }
    }

}
