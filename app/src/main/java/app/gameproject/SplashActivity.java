package app.gameproject;
/**
 * Since 2021-03-05 By LeeJaeYoung
 * Board Game Project
 **/


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        } else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }

        //Splash TextView Animation
        TextAnimation(binding.splashTv1, 100);
        TextAnimation(binding.splashTv2, 220);
        TextAnimation(binding.splashTv3, 420);
        TextAnimation(binding.splashTv4, 120);
        TextAnimation(binding.splashTv5, 520);
        TextAnimation(binding.splashTv6, 270);
        TextAnimation(binding.splashTv7, 870);
        TextAnimation(binding.splashTv8, 370);
        TextAnimation(binding.splashTv9, 620);
        TextAnimation(binding.splashTv10, 920);
        TextAnimation(binding.splashTv11, 670);
        TextAnimation(binding.splashTv12, 720);
        TextAnimation(binding.splashTv13, 770);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000);

    }

    private void TextAnimation(TextView tv, int delay) {

        PropertyValuesHolder translation_up = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, 100f);
        PropertyValuesHolder translation_down = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, -100f);

        ObjectAnimator up = ObjectAnimator.ofPropertyValuesHolder(tv, translation_up);
        ObjectAnimator down = ObjectAnimator.ofPropertyValuesHolder(tv, translation_down);
        up.setRepeatCount(ValueAnimator.INFINITE);
        down.setRepeatCount(ValueAnimator.INFINITE);
        up.setStartDelay(delay);
        down.setStartDelay(delay);

        AnimatorSet set = new AnimatorSet();
        set.play(up).after(down);
        set.setDuration(1000);
        set.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.anticipate_overshoot));
        set.start();

    }

}