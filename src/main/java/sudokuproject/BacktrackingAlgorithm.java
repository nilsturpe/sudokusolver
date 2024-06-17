package sudokuproject;

public class BacktrackingAlgorithm {
    public boolean solve(SudokuGrid grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid.getValue(row, col) == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (grid.isPossible(row, col, num)) {
                            grid.setValue(row, col, num);
                            if (solve(grid)) {
                                return true;
                            } else {
                                grid.setValue(row, col, 0); // Rücksetzen
                            }
                        }
                    }
                    return false; // Keine Zahl passt
                }
            }
        }
        return true; // Das Board ist vollständig
    }
}
