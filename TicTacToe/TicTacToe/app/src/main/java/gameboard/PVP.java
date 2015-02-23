package gameboard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import tictactoe.AI.AIPlayer; //used for calculating AI moves
import tictactoe.AI.AIPlayerMinimax; //used for calculating AI moves
import com.example.tictactoe.app.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 19/07/14.
 */

public class PVP extends Activity {
    int [] tictactoeboard = new int [9];
    int [][] tictactoeboard_2d = new int [3][3]; //used for AI calculations
    boolean pvp = true; //boolean for pvp or pve
    boolean player_one_turn = true; //is it player one's turn?
    boolean game_over = false;
    int number_of_moves = 1; //counter
    int player_one_symbol = 0; //the symbol for player 1; X or O
    int player_two_symbol = 0; //the symbol for player 2; X or O
    int player_one_score = 0; //player one's score
    int player_two_score = 0; //player two's score

    //xml related variables
    TextView winning_message;
    TextView player_one_score_message;
    TextView player_two_score_message;
    List<Button> buttons;
    Button button_0;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_restart;

    //the AI - used for PVE
    AIPlayer make_AI_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pvp);
        pvp = getIntent().getBooleanExtra("pvp_mode", true);

        //choosing the starting symbol
        if (getIntent().getBooleanExtra("starting_symbol_o", true)) {
            player_one_symbol = R.drawable.pieceo_greybackground;
            player_two_symbol = R.drawable.piecex_greybackground;
        }
        else {
            player_one_symbol = R.drawable.piecex_greybackground;
            player_two_symbol = R.drawable.pieceo_greybackground;
        }

        //initializing boards
        for (int i = 0; i < 9; i++) {
            tictactoeboard[i] = 0;
        }
        for (int i = 0; i < 3; i++) {
            tictactoeboard_2d[i][0] = 0;
            tictactoeboard_2d[i][1] = 0;
            tictactoeboard_2d[i][2] = 0;
        }

        //setting up TextView's and Button's
        winning_message = (TextView) findViewById(R.id.winning_message);
        player_one_score_message = (TextView) findViewById(R.id.player_one_score);
        player_two_score_message = (TextView) findViewById(R.id.player_two_score);
        buttons = new ArrayList<Button>();
        button_0 = (Button) findViewById(R.id.button_0);
        buttons.add(button_0);
        button_1 = (Button) findViewById(R.id.button_1);
        buttons.add(button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        buttons.add(button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        buttons.add(button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        buttons.add(button_4);
        button_5 = (Button) findViewById(R.id.button_5);
        buttons.add(button_5);
        button_6 = (Button) findViewById(R.id.button_6);
        buttons.add(button_6);
        button_7 = (Button) findViewById(R.id.button_7);
        buttons.add(button_7);
        button_8 = (Button) findViewById(R.id.button_8);
        buttons.add(button_8);
        button_restart = (Button) findViewById(R.id.button_restart);

        //setting up AI
        make_AI_move = new AIPlayerMinimax(tictactoeboard_2d);
        make_AI_move.setSeed(-1);

        button_0.setBackgroundResource(R.drawable.button_grey);
        button_1.setBackgroundResource(R.drawable.button_grey);
        button_2.setBackgroundResource(R.drawable.button_grey);
        button_3.setBackgroundResource(R.drawable.button_grey);
        button_4.setBackgroundResource(R.drawable.button_grey);
        button_5.setBackgroundResource(R.drawable.button_grey);
        button_6.setBackgroundResource(R.drawable.button_grey);
        button_7.setBackgroundResource(R.drawable.button_grey);
        button_8.setBackgroundResource(R.drawable.button_grey);
    }

    //converts tictactoeboard to tictactoeboard_2d (a cartesian board)
    void convertBoard(int position, int player) {
        switch (position) {
            case 0:
                tictactoeboard_2d[0][0] = player;
                break;
            case 1:
                tictactoeboard_2d[1][0] = player;
                break;
            case 2:
                tictactoeboard_2d[2][0] = player;
                break;
            case 3:
                tictactoeboard_2d[0][1] = player;
                break;
            case 4:
                tictactoeboard_2d[1][1] = player;
                break;
            case 5:
                tictactoeboard_2d[2][1] = player;
                break;
            case 6:
                tictactoeboard_2d[0][2] = player;
                break;
            case 7:
                tictactoeboard_2d[1][2] = player;
                break;
            case 8:
                tictactoeboard_2d[2][2] = player;
                break;
        }
    }

    //converts tictactoeboard_2d (a cartesian board) to tictactoeboard
    int cartesiantoboard(int row, int col) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0:
                        return 0;
                    case 1:
                        return 3;
                    case 2:
                        return 6;
                }
                break;
            case 1:
                switch (col) {
                    case 0:
                        return 1;
                    case 1:
                        return 4;
                    case 2:
                        return 7;
                }
                break;
            case 2:
                switch (col) {
                    case 0:
                        return 2;
                    case 1:
                        return 5;
                    case 2:
                        return 8;
                }
                break;
        }
        return -1; //didn't find anything
    }

    //checks if anyone has won
    public void checkWinningBoard () {
        if (UtilityClass.checkWinningBoard(tictactoeboard_2d)) {
            game_over = true;
            if (player_one_turn) {
                player_one_score++;
                winning_message.setText("Player 1 \n Wins!!!");
                player_one_score_message.setText("Player 1: " + player_one_score);
            }
            else {
                player_two_score++;
                winning_message.setText("Player 2 \n Wins!!!");
                player_two_score_message.setText("Player 2: " + player_two_score);
            }
            button_restart.setVisibility(View.VISIBLE);
        }
        else if (number_of_moves == 9) {
            game_over = true;
            winning_message.setText("Draw!!!");
            button_restart.setVisibility(View.VISIBLE);
        }
        else {
            player_one_turn = !player_one_turn;
            number_of_moves++;
        }
    }

    public void log_board () {
        Log.d("PVP", "START");
        Log.d("PVP", tictactoeboard[0] + " " + tictactoeboard[1] + " " + tictactoeboard[2]);
        Log.d("PVP", tictactoeboard[3] + " " + tictactoeboard[4] + " " + tictactoeboard[5]);
        Log.d("PVP", tictactoeboard[6] + " " + tictactoeboard[7] + " " + tictactoeboard[8]);
        Log.d("PVP", "//////////////////////////");
        Log.d("PVP", tictactoeboard_2d[0][0] + " " + tictactoeboard_2d[1][0] + " " + tictactoeboard_2d[2][0]);
        Log.d("PVP", tictactoeboard_2d[0][1] + " " + tictactoeboard_2d[1][1] + " " + tictactoeboard_2d[2][1]);
        Log.d("PVP", tictactoeboard_2d[0][2] + " " + tictactoeboard_2d[1][2] + " " + tictactoeboard_2d[2][2]);
        Log.d("PVP", "//////////////////////////");
    }

    public void update_player(View V, int index) {
        if (player_one_turn) {
            V.setBackgroundResource(player_one_symbol);
            tictactoeboard[index] = 1;
            convertBoard(index, 1);
        }
        else {
            V.setBackgroundResource(player_two_symbol);
            tictactoeboard[index] = -1;
            convertBoard(index, -1);
        }
    }

    public void click_button (View V) {
        if (!game_over) {
            String tag = (String) V.getTag();
            if (tictactoeboard[Integer.parseInt(tag)] == 0){ //if the button has not been clicked before
                if (pvp) { //PVP mode
                    update_player(V, Integer.parseInt(tag));
                    log_board();
                    checkWinningBoard();
                }
                else { //PVE mode
                    //Player makes move
                    update_player(V, Integer.parseInt(tag));
                    log_board();
                    checkWinningBoard();

                    if (!game_over) {
                        if ((number_of_moves == 2) && (tictactoeboard[4] == 0)) {
                            Log.e("PVP", "hardcoded case");
                            tictactoeboard[4] = -1;
                            convertBoard(4, -1);
                            buttons.get(4).setBackgroundResource(player_two_symbol);
                            log_board();
                            checkWinningBoard();
                        }
                        else {
                            int[] coordinates = make_AI_move.move();
                            make_AI_move.update(tictactoeboard_2d);
                            //Log.e("PVP", "" + coordinates[0] + ", " + coordinates[1]);
                            int index = cartesiantoboard(coordinates[0], coordinates[1]);
                            if (index == -1) {
                                Log.e("PVP", "you got invalid coordinates! oh no what happened?");
                            }
                            tictactoeboard[index] = -1;
                            convertBoard(index, -1);
                            buttons.get(index).setBackgroundResource(player_two_symbol);
                            log_board();
                            checkWinningBoard();
                        }
                    }
                }
            }
        }
    }

    //restart button
    public void restart (View V) {
        for (int i = 0; i < 3; i++) {
            tictactoeboard_2d[i][0] = 0;
            tictactoeboard_2d[i][1] = 0;
            tictactoeboard_2d[i][2] = 0;
        }
        for (int i = 0; i < 9; i++) {
            tictactoeboard[i] = 0;
        }
        number_of_moves = 1;
        player_one_turn = true;
        game_over = false;
        button_restart.setVisibility(View.INVISIBLE);
        winning_message.setText("");
        button_0.setBackgroundResource(R.drawable.button_grey);
        button_1.setBackgroundResource(R.drawable.button_grey);
        button_2.setBackgroundResource(R.drawable.button_grey);
        button_3.setBackgroundResource(R.drawable.button_grey);
        button_4.setBackgroundResource(R.drawable.button_grey);
        button_5.setBackgroundResource(R.drawable.button_grey);
        button_6.setBackgroundResource(R.drawable.button_grey);
        button_7.setBackgroundResource(R.drawable.button_grey);
        button_8.setBackgroundResource(R.drawable.button_grey);
    }
}
