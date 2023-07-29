public class Sudoku {
    //instance methods
    public int[] grid[]; //the board, grid, etc...
    int gridSize; //9
    int boxSize; //3
    int k; //k amount of missing cells for the player to input

    Sudoku(int gridSize, int k) {
        //initializes constructors of the class to variables
        this.gridSize = gridSize;
        this.k = k;
        Double boxSizeInitizalize = Math.sqrt(gridSize); //boxsize = sqrt(gridSize)
        boxSize = boxSizeInitizalize.intValue(); //initialize boxSize
        grid = new int[gridSize][gridSize]; //initialize grid
    }
    public void fillValues() {
        //generate a Sudoku puzzle by filling the 9*9 grid
        fillDiagonal(); //fill diagonal boxes of the grid
        fillRemaining(0, boxSize); //fill the remaining the cells of the grid
        removeKDigits(); //removes k digits from the grid
    }
    private void fillDiagonal() {
        //fills the diagonal boxes of the grid
        for(int i = 0; i < gridSize; i = i + boxSize) { //iterates throught he grid in box-sized increments
            fillBox(i, i); //calls the follBox(row, col) method fill each box
        }
    }
    private boolean numberInBox(int rowStart, int colStart, int num) { 
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {//loop to interate over each cell
                if (grid[rowStart+i][colStart+j] == num) { //checks if any given number is unused within a specific grid box
                    return false; //return false if the number if found in box
                }
            }
        }
        return true; //return true, otherwise
    }
    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) { //iterate every cell with loop
                do {
                    num = randomGenerator(gridSize); //randomly generate a number
                }
                while (!numberInBox(row, col, num)); //checks if the generated number is unused within the box
                grid[row+i][col+j] = num; //fill random number into that grid
            }
        }
    }
    private int randomGenerator(int num) {
        return (int) Math.floor((Math.random()*num + 1)); //random number generated within parameter given
    }
    private boolean validPlacement(int i, int j, int num) {
        return (numberInRow(i, num) && numberInCol(j, num) && numberInBox(i-i%boxSize, j-j%boxSize, num)); //checks if the number is unused in the curret row, column, and box
    }
    private boolean numberInRow(int i,int num) {
        for (int j = 0; j < gridSize; j++) //find any unused number in row
           if (grid[i][j] == num)
                return false; //return false if number is found
        return true; //return true if the number is not found
    }
 
    private boolean numberInCol(int j,int num) {
        for (int i = 0; i < gridSize; i++) //find any unused number in col
            if (grid [i][j] == num)
                return false; //return false if number is found
        return true; //return true if the number is not found
    }
    private boolean fillRemaining(int i, int j) {
        //checks and balances
        if (j >= gridSize  && i <= gridSize - 1) { //checks if the column index has exceeded the grid size and moves to the next row
            i = i + 1; //if true, increase row by 1
            j = 0; //set col to 0 to start from the first col of the new row
        }
        if (i >= gridSize && j >= gridSize) { //checks if both the row and col indices have exceeded the gridSize, indicating that all cells have been filled
            return true; //return true to indicate board is good
        }
        if (i < boxSize) {  //checks if the current row is within the first set of boxes
            if (j < boxSize) { //checks if current col is within the first set of boxes in the current row
                j = boxSize; //if true, set j to boxSize, effectively moving to the next col in the same box
            }
        }
        else if (i < gridSize - boxSize) { //checks if the current row is within the second set of boxes
            if (j == (int)(i/boxSize)*boxSize) { //if true, check if the current col is at the last col of the current box
                j = j + boxSize; //if true, increment j by boxSize, move the first col of the next box in the same row
            }
        }
        else { //checks if the current col is at the last col of the last box in the current row
            if (j == gridSize - boxSize) {
                i = i + 1; //if true, increase i by 1 to move to the next row
                j = 0; //set j to 0 to start from the first col of the new row
                if (i >= gridSize) {
                    return true; //once reaching the max gridSize, board is good
                }
            }
        }
        for (int num = 1; num <= gridSize; num++) { //start a loop from 1-9 representing the numbers to try filling in the current cell
            if (validPlacement(i, j, num)) { //this ensures that the number doesn't violate any Sudoku rukes
                grid[i][j] = num; //assign num to that specific cell
                if (fillRemaining(i, j + 1)) { //recursively call this method with the same index i, but incrementing the col index 'j' by 1
                //this moves to the next col in the current row
                    return true; //this means the remaining cells of the grid have been sucessfully filled, and a solution is found
                }
                grid[i][j] = 0; //this means the number placement didn't lead to a good board, reseting the current cell back to 0
            }
        }
        return false; //returns false to backtrack and try different numbers in previous cells
    }
    public void removeKDigits() {
        int count = k; //represents the number of cells to be removed from grid
        while (count != 0) { //start a loop until k becoems 0
            int cellID = randomGenerator(gridSize*gridSize) - 1; //generate random number from 0-80 (81 cells, 9*9)
            int i = (cellID/gridSize); //calculate row index by dividing cellID by gridSize
            int j = cellID % 9; //taking the remainder of cellID and gridSize to find col index
            if (j != 0) { //checks if j is not equal to 0, to handle the case where j is at the first co
                j = j - 1; //subtract 1 from j to move to the previous col
            }
            if (grid[i][j] != 0) { //check if the value in the cell is not equal to 0
                count--; //if true, decrease count by 1 to indicate a digit has been removed
                grid[i][j] = 0; //set that specific grid value to 0 (or unknown number)
            }
        }
    }
}