import java.util.*;

public class Main {
    public static Scanner ui = new Scanner (System.in); //initialize Scanner
    //coloured text variables initalized
    public static final String ANSI_WHITE = "\u001b[37m";
	public static final String ANSI_BLACK = "\u001b[30m";
	public static final String ANSI_RED = "\u001b[31m";
	public static final String ANSI_GREEN = "\u001b[32m";
	public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_RESET = "\u001b[0m";
    private static final int gridSize = 9;
    public static void main(String[] args) throws Exception {
        //entry point of program
        clear();
        while (true) {
            //display menu
            title();
            System.out.println(ANSI_WHITE + "\n1. Play Game\n2. Solver\n3. Rules and Information\n4. Quit and Exit Program" + ANSI_RESET);
            int input = Integer.parseInt(ui.nextLine());
            if (input == 1) { //start game();
                clear();
                game();
                }
            else if (input == 2) { //start solveGame();
                clear();
                solveGame();
            }
            else if (input == 3) { //display rules();
                clear();
                rules();
            }
            else if (input == 4) { //exits program
                ui.close();
                clear();
                System.out.println(ANSI_WHITE + "Goodbye, thanks for playing!" + ANSI_RESET);
                System.exit(0);
            }
            else {
                clear();
                System.out.println(ANSI_RED + "Sorry, but " + input + " is not an avaliable option, so try something else." + ANSI_RESET);
                enter();
            }
        }
    }
    public static void title() { //ASCII art for menu
        System.out.println(ANSI_WHITE + "░██████╗██╗░░░██╗██████╗░░█████╗░██╗░░██╗██╗░░░██╗" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "██╔════╝██║░░░██║██╔══██╗██╔══██╗██║░██╔╝██║░░░██║" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "╚█████╗░██║░░░██║██║░░██║██║░░██║█████═╝░██║░░░██║" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "░╚═══██╗██║░░░██║██║░░██║██║░░██║██╔═██╗░██║░░░██║" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "██████╔╝╚██████╔╝██████╔╝╚█████╔╝██║░╚██╗╚██████╔╝" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "╚═════╝░░╚═════╝░╚═════╝░░╚════╝░╚═╝░░╚═╝░╚═════╝░" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "Java Edition by Deacon Sham for CB ICS3U Summative" + ANSI_RESET);
    }
    public static void clear() { //clears console screen
        System.out.print("\033[H\033[2J"); //printing escape characters
        System.out.flush();
    }
    public static void enter() {//prompts user to press enter key and waits for user input
        System.out.println(ANSI_YELLOW + "\nPress the enter key to continue." + ANSI_RESET);
        ui.nextLine();
        clear();
    }
    public static void rules() {//display and print rules
        System.out.println(ANSI_YELLOW + "Welcome to Sudoku: Java Edition" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "In this version of Sudoku, you must input the value first, then input the next x y coordinates" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "After finishing the board, check your answer type \"finish\" to check if your solution is correct" + ANSI_RESET);
        System.out.println(ANSI_RED + "If you give up, you can use the Sudoku Solver program built into this game" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "\nSudoku Solver Instructions:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Input values of the unsolved Sudoku board row by row" + ANSI_RESET);
        System.out.println(ANSI_RED + "Missing values input as 0, and space out every column value" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "When finished, the solver will output your inputted board, and the solved board (if solvable)" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "\nThis project was created by Deacon Sham for the class ICS3U's summative project at Colonel By Secondary School." + ANSI_RESET);
        enter();
    }
    public static void solveGame() {//solving a user's inputted Sudoku board
        Solver solver = new Solver(); //calls Solver.java class, creates solver object
        //asks for user input:
        System.out.println(ANSI_YELLOW + "Insert the values of the Sudoku board in each respective row:\n" + ANSI_RESET);
        for (int row = 0; row < gridSize; row++) {
            int count = row + 1;
            System.out.println(ANSI_RED + "Input the values of row " + count + ":" + ANSI_RESET);
            for (int col = 0; col < gridSize; col++) {
                solver.board [row][col] = ui.nextInt();
            }
        }
        clear();
        System.out.println(ANSI_YELLOW + "User-inputed board:" + ANSI_RESET);
        printBoard(solver.board);
        ui.nextLine();
        if (Solver.solveBoard(solver.board)) { //calls solveBoard method from the Solve class
            System.out.println(ANSI_GREEN + "\nSolved successfully:" + ANSI_RESET);
            printBoard(solver.board); //if board is solvable, print solution
        }
        else {
            System.out.println(ANSI_RED + "\nUnsolvable board:" + ANSI_RESET);
            //if board is unsolvable, don't print impossible solution and board is unsolvable
        }
        enter();
    }
    public static void game() {
        Sudoku game; //calls Sudoku.java class and creates game object
        int k = 0; //k amount of missing squares
        System.out.println(ANSI_WHITE + "Select the difficulty of the board:" + ANSI_GREEN + "\n1. Easy" + ANSI_YELLOW + "\n2. Medium" + ANSI_RED + "\n3. Hard" + ANSI_RESET);
        int difficulty = Integer.parseInt(ui.nextLine());
        //sets difficulty to amount of missing squares
        if (difficulty == 1) {
            clear();
            k = 1;
            System.out.println(ANSI_GREEN + "Easy Board:" + ANSI_RESET);
        }
        else if (difficulty == 2) {
            clear();
            k = 20;
            System.out.println(ANSI_YELLOW + "Medium Board:" + ANSI_RESET);
        }
        else if (difficulty == 3) {
            clear();
            k = 30;
            System.out.println(ANSI_RED + "Hard Board:" + ANSI_RESET);
        }
        else {
            clear();
            System.out.println(ANSI_RED + "Sorry, but " + difficulty + " is not an avaliable option, so try something else." + ANSI_RESET);
            enter();
        }
        game = new Sudoku(gridSize, k); //inputting constructors for game
        game.fillValues();
        int [][] gameBoard = new int [gridSize][gridSize];
        final int [][] originalBoard = game.grid; //constant variable to hold original board
        //make a copy of the original board for this array to be manipulated by the user
        for (int col = 0; col < gridSize; col++){
            for(int row = 0; row < gridSize; row++){
                gameBoard[col][row] = originalBoard[col][row];
            }
        }
        printBoard(game.grid);
        boolean toSolve = false; 
        while (toSolve == false) { //while loop for inputting data into gameBoard
            System.out.println(ANSI_GREEN + "\nInput the value:" + ANSI_RESET);
            int value = ui.nextInt(); //input the number the user would like to manipulate
            System.out.println(ANSI_YELLOW + "\nInput the coordinate you want to change the value of: (row followed by col, indexes 1-9):" + ANSI_RESET);
            int x = ui.nextInt(); //user select the row they shall change
            int y = ui.nextInt(); //user select the col they shall change
            gameBoard[y-1][x-1] = value;
            System.out.println(ANSI_RED + "\nWould you like to continue or have you finished?:" + ANSI_GREEN + "\n1. Continue" + ANSI_YELLOW + "\n2. Finished" + ANSI_RESET);
            int finish = ui.nextInt(); //asks the user if they're finished
            if (finish == 1) { //continues loop if they're not finished
                toSolve = false;
                clear();
                System.out.println(ANSI_RED + "Original Board:" + ANSI_RESET);
                printBoard(originalBoard);
                System.out.println(ANSI_YELLOW + "\nUser-inputed Board: (refer to original board if numbers align)" + ANSI_RESET);
                printBoard(gameBoard);
            }
            else if (finish == 2) {//ends loop if finished
                toSolve = true;
                clear();
            }
        }
        Solver solveGame = new Solver(); //calls Solver.java creates solveGame object
        solveGame.board = originalBoard; //solveGame.board is the solution of the originalBoard
        System.out.println(ANSI_YELLOW + "User-inputed board:"+ ANSI_RESET);
        printBoard(gameBoard);
        if (Solver.solveBoard(solveGame.board)) {
            System.out.println(ANSI_GREEN + "\nSolved Board:" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + "\nError:" + ANSI_RESET);
        }
        printBoard(solveGame.board);
        if (checkSolution(gameBoard, solveGame.board)) { //uses checkSolution method to see if user-inputted board and solution board are the same
            System.out.println(ANSI_GREEN + "\nCongrats on finding the correct solution, you win!" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + "\nWrong solution, try again with a different board." + ANSI_RESET);
        }
        ui.nextLine();
        enter();
    }
    public static void printBoard(int[][] printBoard) { //print board
        for (int row = 0; row < gridSize; row++) {
            if (row % 3 == 0 && row != 0) { //after row 3, 6, it inputs a line dividing the boxes
                System.out.println((ANSI_RED + "-----------" + ANSI_RESET));
            }
            for (int col = 0; col < gridSize; col++) {
                if (col % 3 == 0 && col != 0) { //after col 3, 6
                    System.out.print(ANSI_RED + "|" + ANSI_RESET);
                }
                System.out.print(ANSI_WHITE + printBoard[row][col] + ANSI_RESET); //prints the value of the array of specific row then col
            }
            System.out.println();
        }
    }
    public static boolean checkSolution(int[][] user, int [][] solution) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) { 
                if (user[i][j] !=  solution[i][j]) { //checks every value and compares value between user and solution board
                    return false; //if one value is not equal to each other, return false
                }
            }
        }
        return true; //all values the same, return true
    }
}