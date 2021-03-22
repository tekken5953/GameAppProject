package app.gameproject.Game.Offline;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import app.gameproject.Game.GameRecyclerViewAdapter;
import app.gameproject.Game.GameRecyclerViewItem;
import app.gameproject.Game.Start_Game_Activity;
import app.gameproject.Game.ViewPagerTransForm;
import app.gameproject.R;
import app.gameproject.ViewTouchListener;
import app.gameproject.databinding.OfflineGameListActivityBinding;

public class OfflineGameListActivity extends AppCompatActivity {

    OfflineGameListActivityBinding binding;
    ViewPager2 ViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OfflineGameListActivityBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        ViewTouchListener viewTouchListener = new ViewTouchListener();
        viewTouchListener.onPressImageView(binding.offlineListBackIv);

        ViewPager2 = findViewById(R.id.offline_list_viewPager);
        ArrayList<GameRecyclerViewItem> list = new ArrayList<>();

        list.add(new GameRecyclerViewItem("1","first", ResourcesCompat.getDrawable(getResources(),R.drawable.page1,null)));
        list.add(new GameRecyclerViewItem("2","second",ResourcesCompat.getDrawable(getResources(),R.drawable.page2,null)));
        list.add(new GameRecyclerViewItem("3","third",ResourcesCompat.getDrawable(getResources(),R.drawable.page3,null)));
        list.add(new GameRecyclerViewItem("4","fourth",ResourcesCompat.getDrawable(getResources(),R.drawable.page4,null)));


        ViewPager2.setAdapter(new GameRecyclerViewAdapter(list));
        ViewPager2.setOrientation(androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL);

        ViewPager2.setPageTransformer(new ViewPagerTransForm());
    }

    public void press_back(View view) {
        Intent intent = new Intent(OfflineGameListActivity.this, Start_Game_Activity.class);
        startActivity(intent);
        finish();
    }
}