package gameboard;

import static java.lang.Math.abs;

/**
 * Created by Alex on 24/07/14.
 */

public class UtilityClass {
    //used to determine if someone has won in tic tac toe
    public static boolean checkWinningBoard(int[][] tictactoeboard) {
        for (int i = 0; i < 3; i++) {
            //checking rows
            if (abs(tictactoeboard[0][i] + tictactoeboard[1][i] + tictactoeboard[2][i]) == 3) {
                return true;
            }
            //checking cols
            if (abs(tictactoeboard[i][0] + tictactoeboard[i][1] + tictactoeboard[i][2]) == 3) {
                return true;
            }
        }
        //checking diagonal
        if (abs(tictactoeboard[0][0] + tictactoeboard[1][1] + tictactoeboard[2][2]) == 3) {
            return true;
        }
        //checking antidiagonal
        if (abs(tictactoeboard[2][0] + tictactoeboard[1][1] + tictactoeboard[0][2]) == 3) {
            return true;
        }
        return false;
    }
}
