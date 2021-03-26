package app.gameproject.Game.Online;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import app.gameproject.Game.Offline.OfflineGameListActivity;
import app.gameproject.Game.Start_Game_Activity;
import app.gameproject.Game.ViewPagerTransForm;
import app.gameproject.R;
import app.gameproject.Retrofit.AdminItem;
import app.gameproject.Retrofit.MyAPI;
import app.gameproject.Retrofit.NullOnEmptyConverterFactory;
import app.gameproject.ViewTouchListener;
import app.gameproject.databinding.OnlineGameListActivityBinding;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;


public class OnlineGameListActivity extends AppCompatActivity {

    ViewPager2 ViewPager2;
    OnlineGameListActivityBinding binding;
    MyAPI mMyAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OnlineGameListActivityBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        initMyAPI();

        ViewTouchListener viewTouchListener = new ViewTouchListener();
        viewTouchListener.onPressImageView(binding.onlineListBackIv);

        ViewPager2 = findViewById(R.id.online_list_viewPager);
        ArrayList<GameRecyclerViewItem> list = new ArrayList<>();

        ViewPager2.setAdapter(new GameRecyclerViewAdapter(list));
        ViewPager2.setOrientation(androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL);

        ViewPager2.setPageTransformer(new ViewPagerTransForm());

        //add game
        Call<List<AdminItem>> get_game = mMyAPI.get_game_by_on_off("on");
        get_game.enqueue(new Callback<List<AdminItem>>() {
            @Override
            public void onResponse(Call<List<AdminItem>> call, Response<List<AdminItem>> response) {
                List<AdminItem> body = response.body();
                assert body != null;
                for (AdminItem item : body) {
                   list.add(new GameRecyclerViewItem(item.getId(), item.getGame_name(), ResourcesCompat.getDrawable(getResources(), R.drawable.page3, null)));
                   Log.d("retrofit", item.getId() + ", " + item.getGame_name() + "\n");
                   Objects.requireNonNull(ViewPager2.getAdapter()).notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<AdminItem>> call, Throwable t) {
                Toast.makeText(OnlineGameListActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void press_back(View view) {
        Intent intent = new Intent(OnlineGameListActivity.this, Start_Game_Activity.class);
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