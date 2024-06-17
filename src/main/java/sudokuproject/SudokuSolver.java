package sudokuproject;

// Klasse, die das Sudoku-Brett mit dem Algorithmus löst 
public class SudokuSolver {
    private SudokuGrid grid;  
    private BacktrackingAlgorithm algorithm;

 
    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid; 
        this.algorithm = new BacktrackingAlgorithm();
    }
 
    public boolean solve() {
        return algorithm.solve(grid);
    }
}
