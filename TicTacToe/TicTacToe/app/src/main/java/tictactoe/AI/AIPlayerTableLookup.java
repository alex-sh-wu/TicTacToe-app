package tictactoe.AI;

/**
 * Created by Alex on 24/07/14.
 */

//copied code from http://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html

public class AIPlayerTableLookup extends AIPlayer{

        // Moves {row, col} in order of preferences. {0, 0} at top-left corner
        private int[][] preferredMoves = {
                {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
                {0, 1}, {1, 0}, {1, 2}, {2, 1}};

        /** constructor */
        public AIPlayerTableLookup(int[][] board) {
            super(board);
        }

        /** Search for the first empty cell, according to the preferences
         *  Assume that next move is available, i.e., not gameover
         *  @return int[2] of {row, col}
         */
        @Override
        public int[] move() {
            for (int[] move : preferredMoves) {
                if (cells[move[0]][move[1]] == 0) {
                    return move;
                }
            }
            assert false : "No empty cell?!";
            return null;
        }
}
