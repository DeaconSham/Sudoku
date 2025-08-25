# Sudoku Game & Solver

This project is a console-based Sudoku game and solver built in Java. It allows users to play a game of Sudoku with a generated puzzle or input their own puzzle to be solved by a backtracking algorithm.

## Features

*   **Sudoku Game**: Play a game with a puzzle of a specified difficulty (by changing how many numbers are removed).
*   **Backtracking Solver**: Input any Sudoku puzzle and have the program solve it instantly.
*   **Input Validation**: The solver checks if a number can be placed in a specific row, column, or 3x3 box.
*   **Puzzle Generation**: Creates a valid, solved Sudoku board and then removes a specified number of digits to generate a playable puzzle.
*   **User-Friendly Console Interface**: A simple menu allows the user to choose between playing the game, using the solver, or reading the rules.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

You will need to have a Java Development Kit (JDK) installed to compile and run the project.

### Installation & Running

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/DeaconSham/Sudoku.git
    ```
2.  **Compile the Java source code:**
    ```sh
    javac *.java
    ```
3.  **Run the application:**
    ```sh
    java Main
    ```

## How to Use

When you run the application, you will be presented with a main menu:

*   `1`: **Start Game**: Begins a new Sudoku game with a generated puzzle. You will be prompted to enter the row, column, and the number you wish to place.
*   `2`: **Solve a Game**: Allows you to input your own 9x9 Sudoku puzzle row by row. The program will then display the solved version.
*   `3`: **Rules and Information**: Displays the rules of Sudoku and instructions on how to use the program.
*   `4`: **Exit**: Closes the application.

During the game, you can enter coordinates (row and column) and the desired number. Once you believe you have solved the puzzle, you can finish the game, and the program will check your solution against the correct one.

## File Structure

*   **`Main.java`**: The entry point of the application. It handles the main menu, user input, and calls methods for the game and solver logic.
*   **`Sudoku.java`**: Responsible for generating a new Sudoku puzzle. It starts by creating a full, valid board and then removes a specified number of digits.
*   **`Solver.java`**: Contains the logic for solving a Sudoku puzzle. It uses a backtracking algorithm to find a valid solution for any given board state.
