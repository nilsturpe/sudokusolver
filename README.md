# Sudoku Solver
 
## Einführung
 
Web-App mit JavaFX, die den Benutzer ein Sudoku-Feld mit römischen oder arabischen Zahlen ausfüllen lässt und dieses daraufhin mit Hilfe eines Backtracking-Algorithmuses löst.
 
## Algorithmuswahl
 
Der Backtracking-Algorithmus ist der beliebteste Algorithmus, um Sudoku zu lösen.
Wir haben uns für den Algorithmus entschieden, aufgrund der einfachen Nachvollziehbarkeit und Effektivität, da er systematisch alle möglichen Varianten untersucht. 
 
## Projektstruktur
 
- `Main.java`: Hauptdatei des Programmes.
- `SudokuSolver.java`: Verbindet das Sudokubrett mit dem Algorithmus.
- `SudokuGrid.java`: Logisches Sudoku-Feld.
- `BacktrackingAlgorithm.java`: Implementierung des Backtracking Algorithmuses. 
- `NumberParser.java`: Interface für Zahlen-Umwandlungs-Klassen.
- `RomanNumberParser.java`: Klasse zum Umwandeln von römischen Zahlen zu Integer.
- `ArabicNumberParser.java`: Klasse zum Umwandeln von arabischen Zahlen zu Integer.
- `SudokuSolverApp.java`: Enthält JavaFX App, sowie die logische Abfolge des Programmes. 
 
## Ausführen des Projekts
 
1. JavaFX sowie Java-Version 22 muss installiert sein.
2. Alle Java-Dateien müssen kompiliert werden.
3. Main.java ausführen.

Wir empfehlen VS Code.
 
## Funktionsweise
 
Das Frontend wird mit JavaFX erstellt.
Im Backend ist der Algorithmus und die Logik implementiert, um das Sudoku lösen zu können. 
 
## Kommentare im Code
 
Der Code ist mit Kommentaren versehen, die die Funktionsweise der einzelnen Methoden und Klassen erklären.
Sie erklären ebenfalls unsere näheren Gedankengänge. 

## Auffälligkeiten bei der Entwicklung

Es ist uns aufgefallen, dass sehr viele Eingabeüberprüfungen notwendig sind, um eine einwandfreie Benutzung zu ermöglichen.
Ebenfalls war es relativ komplex, das Sudokubrett graphisch korrekt und anschaulich darzustellen, während das logische Brett uns relativ einfach fiel. 