module sudokuproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens sudokuproject to javafx.fxml;
    exports sudokuproject;
}
