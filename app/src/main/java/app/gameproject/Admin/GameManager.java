package app.gameproject.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import app.gameproject.LoginActivity;
import app.gameproject.R;
import app.gameproject.Retrofit.AdminItem;
import app.gameproject.Retrofit.MyAPI;
import app.gameproject.Retrofit.NullOnEmptyConverterFactory;
import app.gameproject.Retrofit.UserItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameManager extends AppCompatActivity {

    MyAPI mMyAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_manager);

        initMyAPI();
    }

    // 게임 생성
    public void press_admin_game_create(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View v = LayoutInflater.from(view.getContext()).inflate(R.layout.admin_game_create_dialog, null, false);
        builder.setView(v);
        final AlertDialog alertDialog = builder.create();
        final EditText game_id = v.findViewById(R.id.game_create_id_et);
        final EditText game_name = v.findViewById(R.id.game_create_name_et);
        final EditText game_on_off = v.findViewById(R.id.game_create_on_off_et);
        final Button ok_btn = v.findViewById(R.id.game_create_add_btn);
        final Button cancel_btn = v.findViewById(R.id.game_create_cancel_btn);

        TextView result_tx = findViewById(R.id.admin_game_content_tx);

        //Apply Button Click
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminItem item = new AdminItem();
                item.setId(game_id.getText().toString());
                item.setGame_name(game_name.getText().toString());
                item.setOn_off(game_on_off.getText().toString());
                Call<AdminItem> post_game = mMyAPI.post_game(item);
                post_game.enqueue(new Callback<AdminItem>() {
                    @Override
                    public void onResponse(Call<AdminItem> call, Response<AdminItem> response) {
                        result_tx.setText("게임 생성 완료!\n");
                    }

                    @Override
                    public void onFailure(Call<AdminItem> call, Throwable t) {
                        result_tx.setText("게임 생성 실패\n" + t.getMessage());
                    }
                });
                alertDialog.dismiss();
            }
        });

        //Cancel Button Click
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    // 게임 불러오기
    public void press_admin_game_read(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View v = LayoutInflater.from(view.getContext()).inflate(R.layout.admin_game_read_dialog, null, false);
        builder.setView(v);
        final AlertDialog alertDialog = builder.create();
        final Spinner spinner = v.findViewById(R.id.game_read_spinner);
        final EditText et = v.findViewById(R.id.game_read_et);
        final Button ok = v.findViewById(R.id.game_read_ok_btn);
        final Button cancel = v.findViewById(R.id.game_read_cancel_btn);

        TextView result_tx = findViewById(R.id.admin_game_content_tx);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_dropdown_item_1line, list);
        list.add("id");
        list.add("game_name");
        list.add("on/off");
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItemId() == 0) {
                    Call<List<AdminItem>> get_game_by_id = mMyAPI.get_game_by_id(et.getText().toString());
                    get_game_by_id.enqueue(new Callback<List<AdminItem>>() {
                        @Override
                        public void onResponse(Call<List<AdminItem>> call, Response<List<AdminItem>> response) {
                            List<AdminItem> body = response.body();
                            StringBuilder a = new StringBuilder();
                            for (AdminItem item : body) {
                                a.append("id : ").append(item.getId()).append(" ")
                                        .append("name : ").append(item.getGame_name()).append(" ")
                                        .append("on/off : ").append(item.getOn_off()).append(" ")
                                        .append("\n");
                            }
                            result_tx.setText(a.toString());
                        }

                        @Override
                        public void onFailure(Call<List<AdminItem>> call, Throwable t) {
                            result_tx.setText("Error " + t.getMessage());
                        }
                    });
                } else if (spinner.getSelectedItemId() == 1) {
                    Call<List<AdminItem>> get_game_by_name = mMyAPI.get_game_by_game_name(et.getText().toString());
                    get_game_by_name.enqueue(new Callback<List<AdminItem>>() {
                        @Override
                        public void onResponse(Call<List<AdminItem>> call, Response<List<AdminItem>> response) {
                            List<AdminItem> body = response.body();
                            StringBuilder a = new StringBuilder();
                            for (AdminItem item : body) {
                                a.append("id : ").append(item.getId()).append(" ")
                                        .append("name : ").append(item.getGame_name()).append(" ")
                                        .append("on/off : ").append(item.getOn_off()).append(" ")
                                        .append("\n");
                            }
                            result_tx.setText(a.toString());
                        }

                        @Override
                        public void onFailure(Call<List<AdminItem>> call, Throwable t) {
                            result_tx.setText("Error " + t.getMessage());
                        }
                    });
                } else if (spinner.getSelectedItemId() == 2) {
                    Call<List<AdminItem>> get_game_by_on_off = mMyAPI.get_game_by_on_off(et.getText().toString());
                    get_game_by_on_off.enqueue(new Callback<List<AdminItem>>() {
                        @Override
                        public void onResponse(Call<List<AdminItem>> call, Response<List<AdminItem>> response) {
                            List<AdminItem> body = response.body();
                            StringBuilder a = new StringBuilder();
                            for (AdminItem item : body) {
                                a.append("id : ").append(item.getId()).append(" ")
                                        .append("name : ").append(item.getGame_name()).append(" ")
                                        .append("on/off : ").append(item.getOn_off()).append(" ")
                                        .append("\n");
                            }
                            result_tx.setText(a.toString());
                        }

                        @Override
                        public void onFailure(Call<List<AdminItem>> call, Throwable t) {
                            result_tx.setText("Error " + t.getMessage());
                        }
                    });
                }
                alertDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    // 게임 수정
    public void press_admin_game_update(View view) {
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
    }

    // 게임 삭제
    public void press_admin_game_delete(View view) {
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
    }

    // 취소 버튼
    public void press_admin_game_cancel(View view) {
        Intent intent = new Intent(GameManager.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initMyAPI() {
        final String URL = "http://10.0.2.2:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }


}