## Description

Java text-based Sudoku game and solver implementing backtracking algorithms.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Explanation of program


### `Main.java` class:

`main` method: This is the entry point of the program. It displays a menu to the user and based on the user`s input, it either starts the game, invokes the solver, displays rules and information, or exits the program.

`title` method: The method prints the game title and the developer's name.

`clear` method: This method clears the console screen by printing the escape characters.

`enter` method: This method prompts the user to press the enter key to continue and waits for the user`s input.

`rules` method: This method displays the rules and instructions of the Sudoku game and the Sudoku solver.

`solveGame` method: This method allows the user to input a Sudoku board and invokes the `Solver.solveBoard` method to solve it. It first prompts the user to input the values of the Sudoku board row by row. Then, it calls the `Solver.solveBoard` method to solve the board and prints the user-inputted board and the solved board.

`game` method: This method starts the Sudoku game.
It generates a Sudoku board using the `Sudoku` class, fills it with values, and assigns it to `gameBoard`. It allows the user to input values for specific coordinates on the board. The user can continue inputting values or choose to finish. If the user finishes, it displays the original board, the user-inputted board, and the solved board. Uses method `checkSolution` to see if both values of the user-inputted board and the solved board are the same; if so, it returns proper, showing that the user won the game, or it returns false, showing that the loser provided the wrong solution.

`printBoard` method: This method prints the Sudoku board. It takes a two-dimensional array representing the Sudoku board as input. It iterates over the rows and columns of the board and prints the values. It also adds horizontal and vertical lines to separate the three-by-three boxes.

`checkSolution` method: This method checks if the user`s Sudoku board matches the solution. It takes two two-dimensional arrays representing the user`s board and the solution as input. It compares each cell of the two boards and returns `false` if and the cell does not match. If all cells match, it returns `true`.


### `Solver.java` class:

`numberInRow`, `numberInCol`, `numberInBox` methods: These methods determine whether a given number already exists in a particular row, column, or each 3*3 box. They return a boolean value, which is `true` if the number already exists and `false` if it does not.

`validPlacement` method: This method determines whether inserting a given number in a specific row, three by three box, and column is a good move. For example, if the number is already in a different row, column, or 3*3 box. If the move is valid, it returns `true`; otherwise, it returns `false`.

`solveBoard` method: This method implements a backtracking algorithm to solve the Sudoku puzzle. It starts by iterating through every cell of the board. If the cell is empty (for example, it contains a 0), the method attempts to place each number from 1 to 9 in the cell and recursively calls itself to solve the puzzle with the new number in place. If the recursive call returns `true`, the puzzle is solved, and the method returns `true`. Otherwise, the number is removed from the cell, and the following number is tried. If all numbers from 1 to 9 have been tried and none lead to a solution, the method returns `false`, indicating that the puzzle is unsolvable.


### `Sudoku.java` class:

`Sudoku` method: This is the constructor of the class. Based on the provided arguments, it initializes the gridSize, k, and boxSize variables. It also creates a 2D array called a grid to represent the Sudoku grid.

`fillValues` method: This method generates a Sudoku puzzle by filling the grid. It consists of the following steps:
- It calls the fillDiagonal() method to fill the diagonal boxes of the grid.
- It calls the fillRemaining(0, boxSize) method to fill the remaining cells of the grid.
- Finally, it calls the removeKDigits() method to remove k digits from the grid and create a playable puzzle.

`fillDiagonals` method: This method fills the diagonal boxes of the grid. It iterates through the grid in box-sized increments and calls the fillBox(int row, int col) method to fill each box.
 
`unUsedInBox` method: This method checks if a given number num is unused within a specific grid box. It iterates over the cells within the box defined by the rowStart and colStart indices and checks if the number already exists in any of the cells.

`fillBox` method: This method fills a grid box starting from the specified row and column. It iterates over each cell in the box and generates random numbers using the randomGenerator(int num) method. It checks if the generated number is unused within the box using the unUsedInBox method and assigns it to the current cell.

`randomGenerator` method: This method generates a random number between 1 and the specified num. It uses the Math.random() method to generate a random decimal between 0 and 1, multiply it by num, and then rest the result to an integer.

`CheckIfSafe` method: This method checks if placing a number num at the specified position (i, j) in the grid is safe. It checks if the number is unused in the current row, column, and box by calling the unUsedInRow, unUsedInCol, and unUsedInBox methods, respectively.

`unUsedInRow` method: This method checks if the number num is unused in the specified row i. It iterates over the cells in the row and returns false if the number is found, indicating that it is already used.

`unUsedInCol` method: This method checks if the number num is unused in column j. It iterates over the cells in the column and returns false if the number is found, indicating that it is already used.

`fillRemaining` method: This method uses backtracking to fill the remaining cells of the grid. It starts from the specified position (i, j) and recursively tries different numbers until a valid solution is found. It returns true if the grid is filled, indicating a solution is found, and false otherwise.

`removeKDigits` method: This method removes `k` digits from the initial grid to create a playable puzzle. It iterates `k` times and selects a random cell using the `randomGenerator` method. If the selected cell is not already empty (its value is not 0), it sets the value of that cell to 0, effectively removing the digit. This process is repeated `k` times to remove `k` digits from the initial grid and create a playable puzzle.
