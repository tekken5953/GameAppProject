package app.gameproject;

/** Since 2021-03-05 By LeeJaeYoung
  Board Game Project **/


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import app.gameproject.Game.Start_Game_Activity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void press_ranking_btn(View view) {
        Intent intent = new Intent(MainActivity.this, RankingAcitivty.class);
        startActivity(intent);
        finish();
    }

    public void press_community_btn(View view) {
        Intent intent = new Intent(MainActivity.this, CommunityActivity.class);
        startActivity(intent);
        finish();
    }

    public void press_store_btn(View view) {
        Intent intent = new Intent(MainActivity.this, StoreAcitivity.class);
        startActivity(intent);
        finish();
    }

    public void press_game_start_btn(View view) {
        Intent intent = new Intent(MainActivity.this, Start_Game_Activity.class);
        startActivity(intent);
        finish();
    }
}