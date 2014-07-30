package tictactoe.AI;

/**
 * Created by Alex on 24/07/14.
 */

//copied code from http://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html

public abstract class AIPlayer {
    protected int ROWS = 3;  // number of rows
    protected int COLS = 3;  // number of columns

    protected int[][] cells; // the board's ROWS-by-COLS array of Cells
    protected int mySeed;    // computer's seed
    protected int oppSeed;   // opponent's seed

    /** Constructor with reference to game board */
    public AIPlayer(int[][] board) {
        cells = board;
    }

    public void update(int [][] board) {
        cells = board;
    }

    /** Set/change the seed used by computer and opponent */
    public void setSeed(int seed) {
        this.mySeed = seed;
        oppSeed = -1 * seed;
    }

    /** Abstract method to get next move. Return int[2] of {row, col} */
    public abstract int[] move();  // to be implemented by subclasses

}
