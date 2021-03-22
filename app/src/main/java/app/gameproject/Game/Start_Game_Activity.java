package app.gameproject.Game;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import app.gameproject.Game.Offline.OfflineGameListActivity;
import app.gameproject.Game.Online.OnlineGameListActivity;
import app.gameproject.MainActivity;
import app.gameproject.R;
import app.gameproject.ViewTouchListener;
import app.gameproject.databinding.StartGameActivityBinding;


public class Start_Game_Activity extends AppCompatActivity {

    StartGameActivityBinding binding;
    private static final String TAG_PRESSED = "pressed";


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StartGameActivityBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        ImageView cancel = binding.startGameCancelIv;

        cancel.setBackgroundResource(R.drawable.cancel_iv);
        cancel.setColorFilter(Color.parseColor("#474175"));
        Log.d(TAG_PRESSED,cancel.isPressed()+"");
        ViewTouchListener viewTouchListener = new ViewTouchListener();
        viewTouchListener.onPressImageView(cancel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Start_Game_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void press_cancel_iv(View view) {
        Intent intent = new Intent(Start_Game_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void press_onlinegame_btn(View view) {
        Intent intent = new Intent(Start_Game_Activity.this, OnlineGameListActivity.class);
        startActivity(intent);
        finish();
    }

    public void press_offlinegame_btn(View view) {
        Intent intent = new Intent(Start_Game_Activity.this, OfflineGameListActivity.class);
        startActivity(intent);
        finish();
    }
}