package sudokuproject;

// Interface mit Generic für die Number-Parser -> Umwandler
public interface NumberParser<T> {
    T parse(String input);
}
