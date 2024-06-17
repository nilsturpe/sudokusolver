package sudokuproject;

public class SudokuGrid {
    private int[][] board;

    // Konstruktor
    public SudokuGrid(int[][] board) {
        this.board = board;
    }

    // Getter
    public int getValue(int row, int col) {
        return board[row][col];
    }

    // Setter
    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    // Prüfung, ob die Zahl an dieser Stelle einsetzbar ist
    // Es wird zuerst geschaut, ob sie bereits in der Zeile, dann der Spalte und dann im 3x3 Kasten vorhanden ist
    public boolean isPossible(int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    // Prüfung, ob das Sudoku gelöst ist
    // Stellt sicher, dass keine Zeile leer ist
    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
