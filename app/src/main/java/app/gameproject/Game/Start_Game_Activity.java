package app.gameproject.Game;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import app.gameproject.MainActivity;
import app.gameproject.R;


public class Start_Game_Activitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game_activity);
    }

    public void press_cancel_iv(View view) {
        Intent intent = new Intent(Start_Game_Activitiy.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}