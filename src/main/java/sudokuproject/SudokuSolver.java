package sudokuproject;

public class SudokuSolver {
    private SudokuGrid grid;
    private SolverAlgorithm algorithm;

    // Konstruktor: Initialisiere das SudokuGrid und den Algorithmus
    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid;
        this.algorithm = new BacktrackingAlgorithm(); // Verwende den Backtracking-Algorithmus
    }

    // Methode zum Lösen des Sudoku
    public boolean solve() {
        return algorithm.solve(grid);
    }
}
