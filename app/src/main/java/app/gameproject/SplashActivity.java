package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import app.gameproject.databinding.SplashActivityBinding;

public class SplashActivity extends AppCompatActivity {

    SplashActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Set Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000);

    }
}