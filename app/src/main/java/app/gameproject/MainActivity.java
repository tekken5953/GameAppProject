package app.gameproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.gameproject.Game.GameMain_Activity;
import app.gameproject.Game.Start_Game_Activity;
import app.gameproject.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    MainActivityBinding binding;
    private final MainFragment mainFragment = new MainFragment();
    private final UserFragment userFragment = new UserFragment();
    private final RankingFragment rankingFragment = new RankingFragment();
    private final StoreFragment storeFragment = new StoreFragment();
    private Boolean isExitFlag = false;
    private static final String TAG_BackPress = "back_press";

    //홈화면에서 뒤로가기 클릭 시 종료확인 메시지 출력
    //홈화면이 아닌 다른 화면에서 뒤로가기 클릭 시 홈화면으로 이동
    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment.isVisible() && fragment.equals(mainFragment)) {
                    if (isExitFlag) {
                        Log.e(TAG_BackPress,"isExitFlag is true");
                        finish();
                    } else {
                        Log.e(TAG_BackPress,"called by main fragment");
                        isExitFlag = true;
                        Toast.makeText(this, "뒤로가기를 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isExitFlag = false;
                            }
                        }, 2000);
                }
            } else {
                Log.e(TAG_BackPress,"called not by main fragment");
                binding.bottomnavigationView.setSelectedItemId(R.id.bottom_home);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mainFragment)
                        .commitAllowingStateLoss();
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        //fab icon menu 등록
        BottomNavigationView bottomNavigationView = binding.bottomnavigationView;
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        // 첫화면에 띄워야 할 것들 지정해주기
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mainFragment).commitAllowingStateLoss();
        //바텀 네비게이션뷰 안의 아이템들 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home :
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mainFragment)
                            .commitAllowingStateLoss();
                    break;
                case R.id.bottom_user :
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, userFragment)
                            .commitAllowingStateLoss();
                    break;
                case R.id.bottom_store :
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, storeFragment)
                            .commitAllowingStateLoss();
                    break;
                case R.id.bottom_rank:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, rankingFragment)
                            .commitAllowingStateLoss();
                    break;
            }
            return true;
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Start_Game_Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}