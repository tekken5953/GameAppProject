package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void press_login(View view) {
        Intent intent = new Intent(LoginActivity.this, MakingCharacter.class);
        startActivity(intent);
        finish();
    }
}