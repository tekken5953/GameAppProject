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
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextAnimation(binding.splashTv1,100);
        TextAnimation(binding.splashTv2,400);
        TextAnimation(binding.splashTv3,600);
        TextAnimation(binding.splashTv4,200);
        TextAnimation(binding.splashTv5,700);
        TextAnimation(binding.splashTv6,300);
        TextAnimation(binding.splashTv7,1100);
        TextAnimation(binding.splashTv8,500);
        TextAnimation(binding.splashTv9,800);
        TextAnimation(binding.splashTv10,1200);
        TextAnimation(binding.splashTv11,900);
        TextAnimation(binding.splashTv12,900);
        TextAnimation(binding.splashTv13,900);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000);

    }

    private void TextAnimation(TextView tv,int delay) {

        PropertyValuesHolder translation_up = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, 120f);
        PropertyValuesHolder translation_down = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, -120f);

        ObjectAnimator up = ObjectAnimator.ofPropertyValuesHolder(tv,translation_up);
        ObjectAnimator down = ObjectAnimator.ofPropertyValuesHolder(tv,translation_down);
        up.setRepeatCount(ValueAnimator.INFINITE);
        down.setRepeatCount(ValueAnimator.INFINITE);
        up.setStartDelay(delay);
        down.setStartDelay(delay);

        AnimatorSet set = new AnimatorSet();
        set.play(up).after(down);
        set.setDuration(1300);
        set.start();

    }

}