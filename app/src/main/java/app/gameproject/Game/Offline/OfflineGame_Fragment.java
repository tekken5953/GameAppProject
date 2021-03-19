package app.gameproject.Game.Offline;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project **/

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import app.gameproject.Game.GameRecyclerViewAdapter;
import app.gameproject.Game.GameRecyclerViewItem;
import app.gameproject.R;


public class OfflineGame_Fragment extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_game_frag);
    }
}