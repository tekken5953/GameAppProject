package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StoreAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_acitivity);
    }

    public void press_cancel_iv(View view) {
        Intent intent = new Intent(StoreAcitivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}