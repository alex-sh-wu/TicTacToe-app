package gamemodeselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.tictactoe.app.R;
import gameboard.PVP;

/**
 * Created by Alex on 19/07/14.
 */
public class OpponentSelection extends Activity {
    boolean pvp_mode = true;
    boolean starting_symbol_o = true;
    Button O;
    Button X;
    Button pvp;
    Button pve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectionscreen);
        O = (Button) findViewById(R.id.O);
        X = (Button) findViewById(R.id.X);
        pvp = (Button) findViewById(R.id.pvp);
        pve = (Button) findViewById(R.id.pve);
    }

    public void PVP (View V) {
        pvp_mode = true;
        pvp.setBackgroundResource(R.drawable.pvp);
        pve.setBackgroundResource(R.drawable.pve_grey);
    }

    public void PVE (View V) {
        pvp_mode = false;
        pvp.setBackgroundResource(R.drawable.pvp_grey);
        pve.setBackgroundResource(R.drawable.pve);
    }

    public void O (View V) {
        starting_symbol_o = true;
        O.setBackgroundResource(R.drawable.pieceo);
        X.setBackgroundResource(R.drawable.piecex_grey);
    }

    public void X (View V) {
        starting_symbol_o = false;
        O.setBackgroundResource(R.drawable.pieceo_grey);
        X.setBackgroundResource(R.drawable.piecex);
    }

    public void start (View V) {
        Intent intent = new Intent(this, PVP.class);
        intent.putExtra("pvp_mode", pvp_mode);
        intent.putExtra("starting_symbol_o", starting_symbol_o);
        startActivity(intent);
    }
}