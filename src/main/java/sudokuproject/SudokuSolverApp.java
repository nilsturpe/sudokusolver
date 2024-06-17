package sudokuproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

public class SudokuSolverApp extends Application {

    private TextField[][] fields = new TextField[9][9];
    private SudokuGrid grid;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Solver");

        // Title
        Label titleLabel = new Label("Sudoku Solver");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Subtitle
        Label subtitleLabel = new Label("You can enter Roman or Arabic numerals");
        subtitleLabel.setStyle("-fx-font-size: 16px;");

        // Sudoku Grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                field.setPrefWidth(40);
                field.setPrefHeight(40);
                field.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                field.setStyle("-fx-alignment: center;");

                field.addEventFilter(KeyEvent.KEY_TYPED, e -> {
                    if (!isValidInput(field.getText() + e.getCharacter())) {
                        e.consume();
                    }
                });

                // Set border styles
                BorderWidths borderWidths = new BorderWidths(
                    (i % 3 == 0) ? 2 : 1,
                    (j % 3 == 2) ? 2 : 1,
                    (i % 3 == 2) ? 2 : 1,
                    (j % 3 == 0) ? 2 : 1
                );

                BorderStroke borderStroke = new BorderStroke(
                    Color.BLACK, 
                    BorderStrokeStyle.SOLID, 
                    CornerRadii.EMPTY, 
                    borderWidths
                );

                field.setBorder(new Border(borderStroke));

                fields[i][j] = field;
                gridPane.add(field, j, i);
            }
        }

        // Solve Button
        Button solveButton = new Button("Solve");
        solveButton.setOnAction(e -> solveSudoku());

        // Clear Button
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearGrid());

        HBox buttonBox = new HBox(10, solveButton, clearButton);
        buttonBox.setStyle("-fx-alignment: center;");

        VBox vbox = new VBox(10, titleLabel, subtitleLabel, gridPane, buttonBox);
        vbox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isValidInput(String input) {
        if (input.length() > 2) {
            return false;
        }
        switch (input.toUpperCase()) {
            case "I":
            case "II":
            case "III":
            case "IV":
            case "V":
            case "VI":
            case "VII":
            case "VIII":
            case "IX":
                return true;
            default:
                return input.matches("[1-9]");
        }
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
            System.out.println("No solution found.");
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
                if (fields[i][j].getText().isEmpty()) {
                    fields[i][j].setText(String.valueOf(grid.getValue(i, j)));
                    fields[i][j].setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                }
            }
        }
    }

    private void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j].setText("");
                fields[i][j].setStyle("-fx-text-fill: black;");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
