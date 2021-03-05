package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity);
    }

    public void press_cancel_iv(View view) {
        Intent intent = new Intent(CommunityActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}