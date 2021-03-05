package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RankingAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_acitivty);
    }

    public void press_cancel_iv(View view) {
        Intent intent = new Intent(RankingAcitivty.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}