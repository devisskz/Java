/**
 * Sudoku.java
 * 
 * Implementation of a class that represents a Sudoku puzzle and solves
 * it using recursive backtracking.
 * 
 * Computer Science 112, Boston University
 * 
 * your name: Batyr Issabekov
 */

import java.io.*;
import java.util.Scanner;

public class Sudoku {
    public static PrintWriter out;
    // The current contents of the cells of the puzzle.
    private int[][] grid;
    /*
     * Indicates whether the value in a given cell is fixed
     * (i.e., part of the initial configuration).
     * valIsFixed[r][c] is true if the value in the cell
     * at row r, column c is fixed, and false otherwise.
     */
    private boolean[][] valIsFixed;
    /*
     * This 3-D array allows us to determine if a given subgrid (i.e.,
     * a given 3x3 region of the puzzle) already contains a given
     * value.  We use 2 indices to identify a given subgrid:
     *
     *    (0,0)   (0,1)   (0,2)
     *
     *    (1,0)   (1,1)   (1,2)
     *
     *    (2,0)   (2,1)   (2,2)
     *
     * For example, subgridHasVal[0][2][5] will be true if the subgrid
     * in the upper right-hand corner already has a 5 in it, and false
     * otherwise.
     */
    private boolean[][][] subgridHasVal;
    /*** ADD YOUR ADDITIONAL FIELDS HERE. ***/
    private boolean[][] rowHasVal; //indicates whether the row has a value in it
    private boolean[][] colHasVal; //indicates whether the column has a value in it


    /*
     * Constructs a new Puzzle object, which initially
     * has all empty cells.
     */
    public Sudoku() {
        this.grid = new int[9][9];
        this.valIsFixed = new boolean[9][9];

        /*
         * Note that the third dimension of the following array is 10,
         * because we need to be able to use the possible values
         * (1 through 9) as indices.
         */
        this.subgridHasVal = new boolean[3][3][10];

        /*** INITIALIZE YOUR ADDITIONAL FIELDS HERE. ***/

        this.rowHasVal = new boolean[9][10];
        this.colHasVal = new boolean[9][10];

    }




    /*
     * Place the specified value in the cell with the specified
     * coordinates, and update the state of the puzzle accordingly.
     */
    public void placeVal(int val, int row, int col) {
        this.grid[row][col] = val;
        this.subgridHasVal[row / 3][col / 3][val] = true;

        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
        this.rowHasVal[row][val] = true;
        this.colHasVal[col][val] = true;
    }

    /*
     * remove the specified value from the cell with the specified
     * coordinates, and update the state of the puzzle accordingly.
     */
    public void removeVal(int val, int row, int col) {
        this.grid[row][col] = 0;
        this.subgridHasVal[row / 3][col / 3][val] = false;

        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
        this.rowHasVal[row][val] = false;
        this.colHasVal[col][val] = false;
    }

    /*
     * read in the initial configuration of the puzzle from the specified
     * Scanner, and use that config to initialize the state of the puzzle.
     * The configuration should consist of one line for each row, with the
     * values in the row specified as integers separated by spaces.
     * A value of 0 should be used to indicate an empty cell.
     *
     * You should not change this method.
     */
    public void readConfig(Scanner input) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = input.nextInt();
                this.placeVal(val, r, c);
                if (val != 0) {
                    this.valIsFixed[r][c] = true;
                }
            }
            input.nextLine();
        }
    }

    /*
     * Displays the current state of the puzzle.
     * You should not change this method.
     */
    public void printGrid() {
        for (int r = 0; r < 9; r++) {
            this.printRowSeparator();
            for (int c = 0; c < 9; c++) {
                System.out.print("|");
                if (this.grid[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + this.grid[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        this.printRowSeparator();
    }
    
    // A private helper method used by display()
    // to print a line separating two rows of the puzzle.
    private static void printRowSeparator() {
        for (int i = 0; i < 9; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    /*** ADD ANY ADDITIONAL METHODS HERE. ***/
    //method allowed checks if there is no value already in row and column.


    //draft code
  /*  //this method returns true if the number is already contained in the row, false otherwise
    private boolean numInRow(int row, int num) {
        for (int i = 0; i < 9; i++) { //loops through each row and
            if (grid[row][i] == num) { //checks if the number is in
                return true;
            }
        }
        return false;
    }

    //this method returns true if the number is already contained in column, false otherwise
    private boolean numInCol(int col, int num) {
        for (int i = 0; i < 9; i++) { //iterates through column and
            if (grid[i][col] == num) { //checks if the num is in column
                return true;
            }
        }
        return false;
    }

    //this method returns true if the number is already in the box (3x3)
    private boolean numInBox(int row, int col, int num) {
        int r = row - row % 3; //computes the exact row which is inputted
        int c = col - col % 3; //computes the exact col which is inputted
        for (int i = r; i < r + 3; i++) { //iterates through each row
            for (int j = c; j < c + 3; j++) { //also, iterates through each column
                if (grid[i][j] == num) { //checks if the input is equal to num
                    return true;
                }
            }
        }
        return false;
    }
*/
    //this method checks if the number is placeable in the designated row and col
    private boolean placeable(int row, int col, int num) {

        return !(rowHasVal[row][num] || colHasVal[col][num] || subgridHasVal[row / 3][col / 3][num]);
        //if there is nothing that can contradict the placement of the number, then returns true

        //draft code - not used
        /*if (!numInRow(row, num) && numInCol(col, num) && numInBox(row, col, num)) {
            return true;
        }
        else {
            return false;
        }*/
    }

    /*
     * This is the key recursive-backtracking method.  Returns true if
     * a solution has already been found, and false otherwise.
     *
     * Each invocation of the method is responsible for finding the
     * value of a single cell of the puzzle. The parameter n
     * is the number of the cell that a given invocation of the method
     * is responsible for. We recommend that you consider the cells
     * one row at a time, from top to bottom and left to right,
     * which means that they would be numbered as follows:
     *
     *     0  1  2  3  4  5  6  7  8
     *     9 10 11 12 13 14 15 16 17
     *    18 ...
     */
    private boolean solveRB(int n) {

        /*
         * The following return statement allows the initial code to
         * compile.  Replace it with your full implementation of the
         * recursive-backtracking method.
         */

        int r = n / 9; //divided into by 9 because of row number
        int c = n % 9; 

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) { //loops through each row and column

                if (valIsFixed[row][col] || grid[row][col] > 0) { //if the value is fixed or there is a number in row
                    continue; //doesnt enter the loop
                }

                for (int num = 1; num <= 9; num++) {
                    if (placeable(row, col, num)) { //if the number is 'placeable' 
                        placeVal(num, row, col); //places that value in the row and column
                        if (solveRB((row * 9 + col) + 1)) { //recursive call and iteration +1 for next num
                            return true;
                        }
                        removeVal(num, row, col); //otherwise, removes the value
                    }
                }

                return false;
            }
        }
        return isValidGrid(); //calls the valid grid method
    }

    private boolean isValidGrid() {

        for (int i = 0; i < 9; i++) { //loops through each row
            for (int j = 0; j < 9; j++) { //loops through each column
                if (grid[i][j] == 0)return false; // if any number is a 0 in the grid, returns false
            }
        }

        return true; //otherwise, returns true
    }
    /*
     * public "wrapper" method for solveRB().
     * Makes the initial call to solveRB, and returns whatever it returns.
     */
    public boolean solve() {
        boolean foundSol = this.solveRB(0);
        return foundSol;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Sudoku puzzle = new Sudoku();
        
        System.out.print("Enter the name of the puzzle file: ");
        String filename = scan.nextLine();
        
        try {
            Scanner input = new Scanner(new File(filename));
            puzzle.readConfig(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + filename);
            System.out.println(e);
            System.exit(1);
        }
        
        System.out.println();
        System.out.println("Here is the initial puzzle: ");
        puzzle.printGrid();
        System.out.println();
        
        if (puzzle.solve()) {
            System.out.println("Here is the solution: ");
        } else {
            System.out.println("No solution could be found.");
            System.out.println("Here is the current state of the puzzle:");
        }
        puzzle.printGrid();  
    }    
}
