package sudokuproject;

public class SudokuGrid {
    private int[][] board;

    // Konstruktor: Initialisiere das Board
    public SudokuGrid(int[][] board) {
        this.board = board;
    }

    // Methode zum Abrufen eines Wertes auf dem Board
    public int getValue(int row, int col) {
        return board[row][col];
    }

    // Methode zum Setzen eines Wertes auf dem Board
    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    // Methode zur Überprüfung, ob eine Zahl in einer bestimmten Position sicher ist
    public boolean isSafe(int row, int col, int num) {
        // Überprüfe die Zeile und die Spalte
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    // Methode zum Überprüfen, ob das Sudoku gelöst ist
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
