package app.gameproject.Game.Offline;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.gameproject.Game.GameRecyclerViewAdapter;
import app.gameproject.Game.GameRecyclerViewItem;
import app.gameproject.Game.Online.OnlineGameListActivity;
import app.gameproject.Game.Start_Game_Activity;
import app.gameproject.Game.ViewPagerTransForm;
import app.gameproject.R;
import app.gameproject.Retrofit.AdminItem;
import app.gameproject.Retrofit.MyAPI;
import app.gameproject.Retrofit.NullOnEmptyConverterFactory;
import app.gameproject.ViewTouchListener;
import app.gameproject.databinding.OfflineGameListActivityBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfflineGameListActivity extends AppCompatActivity {

    OfflineGameListActivityBinding binding;
    ViewPager2 ViewPager2;
    MyAPI mMyAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OfflineGameListActivityBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        initMyAPI();

        ViewTouchListener viewTouchListener = new ViewTouchListener();
        viewTouchListener.onPressImageView(binding.offlineListBackIv);

        ViewPager2 = findViewById(R.id.offline_list_viewPager);
        ArrayList<GameRecyclerViewItem> list = new ArrayList<>();

        //add game
        Call<List<AdminItem>> get_game = mMyAPI.get_game_by_on_off("off");
        get_game.enqueue(new Callback<List<AdminItem>>() {
            @Override
            public void onResponse(Call<List<AdminItem>> call, Response<List<AdminItem>> response) {
                List<AdminItem> body = response.body();
                for (AdminItem item : body) {
                    list.add(new GameRecyclerViewItem(item.getId(), item.getGame_name(), ResourcesCompat.getDrawable(getResources(), R.drawable.page3, null)));
                    Objects.requireNonNull(ViewPager2.getAdapter()).notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<AdminItem>> call, Throwable t) {
                Toast.makeText(OfflineGameListActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        ViewPager2.setAdapter(new GameRecyclerViewAdapter(list));
        ViewPager2.setOrientation(androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL);

        ViewPager2.setPageTransformer(new ViewPagerTransForm());
    }

    public void press_back(View view) {
        Intent intent = new Intent(OfflineGameListActivity.this, Start_Game_Activity.class);
        startActivity(intent);
        finish();
    }

    private void initMyAPI() {
        final String URL = "http://10.0.2.2:8080/"; //AVD
//        final String URL = "http://127.0.0.1:8080/"; //Phone
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }
}