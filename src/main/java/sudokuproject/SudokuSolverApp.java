package sudokuproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuSolverApp extends Application {
    
    private TextField[][] fields = new TextField[9][9];
    private SudokuGrid grid;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Solver");

        // Überschrift
        Label titleLabel = new Label("Sudoku Solver");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Unterüberschrift
        Label subtitleLabel = new Label("Du kannst römische oder arabische Zahlen eingeben");
        subtitleLabel.setStyle("-fx-font-size: 16px;");

        // Sudoku Grid
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                field.setPrefWidth(40);
                field.setPrefHeight(40);
                fields[i][j] = field;
                gridPane.add(field, j, i);
            }
        }

        // Solve Button
        Button solveButton = new Button("Solve");
        solveButton.setOnAction(e -> solveSudoku());

        VBox vbox = new VBox(10, titleLabel, subtitleLabel, gridPane, solveButton);
        vbox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void solveSudoku() {
        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = fields[i][j].getText();
                if (!text.isEmpty()) {
                    int value = parseInput(text);
                    if (value >= 1 && value <= 9) {
                        board[i][j] = value;
                    }
                }
            }
        }

        grid = new SudokuGrid(board);
        SudokuSolver solver = new SudokuSolver(grid);

        if (solver.solve()) {
            displaySolution(grid);
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }

    private int parseInput(String text) {
        switch (text.toUpperCase()) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            default:
                try {
                    return Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    return 0;
                }
        }
    }

    private void displaySolution(SudokuGrid grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j].setText(String.valueOf(grid.getValue(i, j)));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
