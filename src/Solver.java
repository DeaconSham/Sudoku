public class Solver {
    private static final int gridSize = 9;
    int [][] board = new int [gridSize][gridSize];
    public static boolean numberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == number) {
                return true; //determines whether a number is already given within this row
            }
        }
        return false;
    }
    private static boolean numberInCol(int[][] board, int number, int col) {
        for (int i = 0; i < gridSize; i++) {
            if (board[i][col] == number) { //determines whether a number is already given within this col
                return true;
            }
        }
        return false;
    }
    private static boolean numberInBox(int[][] board, int number, int row, int column) {
        int boxRow = row - row % 3;
        int bowCol = column - column % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = bowCol; j < bowCol + 3; j++) {
                if (board[i][j] == number) { 
                return true; //determines if there is a number given within this 3*3 box already
                }
            }
        }
        return false;
    }
    private static boolean validPlacement(int[][] board, int number, int row, int col) {
        return !numberInRow(board, number, row) && !numberInCol(board, number, col) && !numberInBox(board, number, row, col);
        //determines whether inserting a given number in a specific row and column is a good move
        //i.e. if number is already in a different row, column, or 3*3 box it returns false, otherwise true
    }
    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) { //start loop to interate every row and col
                if (board[row][col] == 0) { //check if the current cell is empty (0)
                    for (int numberTry = 1; numberTry <= gridSize; numberTry++) { //check if empty, enter a number from 1 to 9
                        if (validPlacement(board, numberTry, row, col)) { //check if the current number can be placed in the current cell calling the 'validPlacement' method
                            board[row][col] = numberTry; //if the number can be placed without violating any rules, assign the number to that specific cell
                            if (solveBoard(board)) { //recursively call the solveBoard method to continue solving
                                return true; //if returns true, it means board has been successfully solved
                            }
                            else { //if return false, it means the current number placement didn't llead to a cell, sets the current cell back to 0
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false; //after trying all numbers and it doesn't lead to a solution, return false to backtrack and try different numbers in previous cells
                }
            }
        }
        return true; //if all cells have been filled and no empty cells remain, return true to indicate it has been solved
    }
}