package app.gameproject;

/**
 * Since 2021-03-05 By LeeJaeYoung
 * Board Game Project
 **/


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.gameproject.Game.Start_Game_Activity;
import app.gameproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        BottomNavigationView bottomNavigationView = binding.bottomnavigationView;
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_community :
                        Intent intent_community = new Intent(MainActivity.this, CommunityActivity.class);
                        startActivity(intent_community);
                        finish();
                        break;
                    case R.id.bottom_store :
                        Intent intent_store = new Intent(MainActivity.this, StoreAcitivity.class);
                        startActivity(intent_store);
                        finish();
                        break;
                    case R.id.bottom_rank:
                        Intent intent_ranking = new Intent(MainActivity.this, RankingAcitivty.class);
                        startActivity(intent_ranking);
                        finish();
                        break;
                }
                return true;
            }
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