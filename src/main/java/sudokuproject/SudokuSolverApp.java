package sudokuproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    private TextField[][] sudokuFields = new TextField[9][9];
    // logisches Sudoku-Grid
    private SudokuGrid sudokuGrid;
    private NumberParser<Integer> arabicParser;
    private NumberParser<Integer> romanParser;
    private Label duplicateMessageLabel;

    public SudokuSolverApp() {
        this.arabicParser = new ArabicNumberParser();
        this.romanParser = new RomanNumberParser();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Solver");

        Label titleLabel = new Label("Sudoku Solver");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label subtitleLabel = new Label("You can enter Roman or Arabic numbers.");
        subtitleLabel.setStyle("-fx-font-size: 16px;");

        // Label, um anzuzeigen, falls Duplikate vorhanden sind
        duplicateMessageLabel = new Label();
        duplicateMessageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        // visuelles Sudoku Grid
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

                // Ränder Kästen
                int top;
                int right;
                int bottom;
                int left;
        
                // Bestimmte Ränder müssen dicker gemacht werden, damit die 3x3 Kästchenform deutlich wird        
                // Jedes Dritte Kästchen, gezählt von Anfang an, und von Oben ist ein 3x3 Kästchen-Rand
                if (i % 3 == 0) {
                    top = 2; 
                } else {
                    top = 1; 
                }
        
                // Jedes dritte Kästchen, ab dem dritten Kästchen, ist der rechte Rand eines 3x3 Kästchen
                if (j % 3 == 2) {
                    right = 2; 
                } else {
                    right = 1; 
                }
        
                // Erstellen der unteren Ränder
                if (i % 3 == 2) {
                    bottom = 2;
                } else {
                    bottom = 1; 
                }
        
                // Erstellen der linken Ränder
                if (j % 3 == 0) {
                    left = 2; 
                } else {
                    left = 1; 
                }
        
                BorderWidths borderWidths = new BorderWidths(top, right, bottom, left);

                BorderStroke borderStroke = new BorderStroke(
                    Color.BLACK, 
                    BorderStrokeStyle.SOLID, 
                    CornerRadii.EMPTY, 
                    borderWidths
                );

                // Kästchen wird erstellt
                field.setBorder(new Border(borderStroke));

                sudokuFields[i][j] = field;
                gridPane.add(field, j, i);
            }
        }

        // Solve Button
        Button solveButton = new Button("Solve");
        solveButton.setPrefWidth(100);
        solveButton.setOnAction(e -> solveSudoku());

        // Clear Button
        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(100);
        clearButton.setOnAction(e -> clearGrid());

        HBox buttonBox = new HBox(10, solveButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, titleLabel, subtitleLabel, gridPane, duplicateMessageLabel, buttonBox);
        vbox.setStyle("-fx-padding: 20px;");
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        // Fullscreen deaktivieren
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }

    // Überprüfung, ob eingegebene Zahlen gültig sind
    // Falls nicht, werden diese nicht angezeigt und nicht übernommen
    private boolean isValidInput(String input) {
        if (input.length() > 2) {
            return false;
        }
        Integer arabicValue = arabicParser.parse(input);
        Integer romanValue = romanParser.parse(input);
        return (arabicValue != 0 && arabicValue <= 9) || (romanValue != 0 && romanValue <= 9);
    }

    // Überprüfung, ob der Benutzer Zahlen falsch eingetragen hat
    private boolean checkForDuplicates() {
        // Logisches Sudoku-Brett wird erstellt 
        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = sudokuFields[i][j].getText();
                if (!text.isEmpty()) {
                    int value = parseInput(text);
                    if (value >= 1 && value <= 9) {
                        board[i][j] = value;
                    }
                }
            }
        }

        // Es wird überprüft, dass keine Duplikate in den Zeilen und den Spalten sind
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[10];
            boolean[] colCheck = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    if (rowCheck[board[i][j]]) {
                        duplicateMessageLabel.setText("Duplicate value found in row " + (i + 1));
                        return false;
                    }
                    rowCheck[board[i][j]] = true;
                }
                if (board[j][i] != 0) {
                    if (colCheck[board[j][i]]) {
                        duplicateMessageLabel.setText("Duplicate value found in column " + (i + 1));
                        return false;
                    }
                    colCheck[board[j][i]] = true;
                }
            }
        }

        // Es wird überprüft, dass keine Duplikate in den 3x3 Kästen sind
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                boolean[] boxCheck = new boolean[10];
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (board[r][c] != 0) {
                            if (boxCheck[board[r][c]]) {
                                duplicateMessageLabel.setText("Duplicate value found in row " + (row + 1) + ", column " + (col + 1));
                                return false;
                            }
                            boxCheck[board[r][c]] = true;
                        }
                    }
                }
            }
        }

        duplicateMessageLabel.setText("");  
        return true;
    }

    // Es wird erst versucht das Sudoku zu lösen, wenn keine Duplikate eingegeben worden sind
    private void solveSudoku() {
        if (!checkForDuplicates()) {
            return;
        }

        // Logisches Sudoku-Brett wird erstellt 
        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = sudokuFields[i][j].getText();
                if (!text.isEmpty()) {
                    int value = parseInput(text);
                    if (value >= 1 && value <= 9) {
                        board[i][j] = value;
                    }
                }
            }
        }

        sudokuGrid = new SudokuGrid(board);
        SudokuSolver solver = new SudokuSolver(sudokuGrid);

        if (solver.solve()) {
            displaySolution(sudokuGrid);
        } else {
            duplicateMessageLabel.setText("No solution found.");
        }
    }

    // Methode um Zahlen einheitlich umzuwandeln 
    private int parseInput(String text) {
        Integer arabicValue = arabicParser.parse(text);
        Integer romanValue = romanParser.parse(text);

        if (arabicValue != 0) {
            return arabicValue;
        } else if (romanValue != 0) {
            return romanValue;
        } else {
            return 0;
        }
    }

    // Ausgabe der Nachricht, falls ein Duplikat eingegeben wurde
    private void displaySolution(SudokuGrid grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuFields[i][j].getText().isEmpty()) {
                    sudokuFields[i][j].setText(String.valueOf(grid.getValue(i, j)));
                    sudokuFields[i][j].setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                }
            }
        }
    }

    private void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j].setText("");
                sudokuFields[i][j].setStyle("-fx-text-fill: black;");
            }
        }
        duplicateMessageLabel.setText("");  
    }

    public static void main(String[] args) {
        launch(args);
    }
}
