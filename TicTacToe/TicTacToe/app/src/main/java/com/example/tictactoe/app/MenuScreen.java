package com.example.tictactoe.app;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import gamemodeselect.OpponentSelection;

/**
 * Created by Alex on 19/07/14.
 */

public class MenuScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuscreen);
    }

    public void chooseGame (View view) {
        Intent intent = new Intent(this, OpponentSelection.class);
        startActivity(intent);
    }
}
