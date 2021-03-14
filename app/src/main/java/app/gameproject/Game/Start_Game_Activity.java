package app.gameproject.Game;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import app.gameproject.MainFragment;
import app.gameproject.R;


public class Start_Game_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game_activity);
    }

    public void press_cancel_iv(View view) {
        Intent intent = new Intent(Start_Game_Activity.this, MainFragment.class);
        startActivity(intent);
        finish();
    }

    public void press_onlinegame_btn(View view) {
        Intent intent = new Intent(Start_Game_Activity.this, GameMain_Activity.class);
        startActivity(intent);
        finish();
    }

    public void press_offlinegame_btn(View view) {
        Intent intent = new Intent(Start_Game_Activity.this, GameMain_Activity.class);
        startActivity(intent);
        finish();
    }
}