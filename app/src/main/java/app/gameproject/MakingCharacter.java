package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MakingCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.making_charcter_activity);
    }

    public void press_home(View view) {
        Intent intent = new Intent(MakingCharacter.this, MainFragment.class);
        startActivity(intent);
        finish();
    }
}