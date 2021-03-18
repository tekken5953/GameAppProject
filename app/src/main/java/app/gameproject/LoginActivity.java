package app.gameproject;
/**
 * Since 2021-03-05 By LeeJaeYoung
 * Board Game Project
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import static maes.tech.intentanim.CustomIntent.customType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Objects;

import app.gameproject.Admin.GameManager;
import app.gameproject.Retrofit.MyAPI;
import app.gameproject.Retrofit.NullOnEmptyConverterFactory;
import app.gameproject.Retrofit.UserItem;
import app.gameproject.databinding.LoginActivityBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    LoginActivityBinding binding;
    MyAPI mMyAPI;

    private String show_pwd = "not showing";
    private static final String TAG = "show_pwd";
    public static final String Retrofit = "retrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Log.d(TAG, show_pwd);
        Log.d(TAG, binding.loginEditPwd.getInputType() + "");

        binding.loginShowPwdIv.setOnClickListener(view1 -> {

            if (show_pwd.equals("not showing")) {
                binding.loginShowPwdIv.setImageResource(R.drawable.show_pwd);
                binding.loginEditPwd.setInputType(144); // Invisible Text Password
                show_pwd = "showing";
                Log.d(TAG, show_pwd);
                Log.d(TAG, binding.loginEditPwd.getInputType() + "");

            } else if (show_pwd.equals("showing")) {
                binding.loginShowPwdIv.setImageResource(R.drawable.noshow_pwd);
                binding.loginEditPwd.setInputType(129); // Visible Text Password
                show_pwd = "not showing";
                Log.d(TAG, show_pwd);
                Log.d(TAG, binding.loginEditPwd.getInputType() + "");
            }
        });
    }

    public void press_login(View view) {
        initMyAPI();
        if (binding.loginEditId.getText().toString().equals("admin") && binding.loginEditPwd.getText().toString().equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, GameManager.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Login as an admin", Toast.LENGTH_SHORT).show();
        } else if (binding.loginEditId.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your ID", Toast.LENGTH_SHORT).show();
            keyboardUp(binding.loginEditId);
        } else if (binding.loginEditPwd.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your Password", Toast.LENGTH_SHORT).show();
            keyboardUp(binding.loginEditPwd);
        } else if (binding.loginEditPwd.getText().toString().length() < 6) {
            Toast.makeText(this, "Check Your Password", Toast.LENGTH_SHORT).show();
            keyboardUp(binding.loginEditPwd);
        } else {
            ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.layout.progressdial);
            progressDialog.show();

            Call<List<UserItem>> get_user_login = mMyAPI.get_user_by_user_id(binding.loginEditId.getText().toString());
            get_user_login.enqueue(new Callback<List<UserItem>>() {
                @Override
                public void onResponse(@NotNull Call<List<UserItem>> call, @NotNull Response<List<UserItem>> response) {
                    if (!Objects.requireNonNull(response.body()).toString().equals("[]")) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.d(Retrofit, response.body().toString());
                        Toast.makeText(LoginActivity.this, "Success to login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        customType(LoginActivity.this, "fadein-to-fadeout");
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "You've never signed up. Just do it.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<UserItem>> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(LoginActivity.this, "Failed to login\nServer is died", Toast.LENGTH_SHORT).show();
                    Log.e(Retrofit, "Fail msg : " + t.getMessage());
                }
            });

        }
    }

    public void press_sign_up(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        customType(LoginActivity.this, "bottom-to-up");
        finish();
    }

    public void press_edit_pwd(View view) {
        keyboardUp(binding.loginEditPwd);
    }

    public void press_edit_id(View view) {
        keyboardUp(binding.loginEditId);
    }

    //KeyBoard Up Inner Class
    public void keyboardUp(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //focus 후 키보드 올리기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void press_kakao_login(View view) {
        //TODO Kakao login service
        Toast.makeText(this, "Kakao Login Service", Toast.LENGTH_SHORT).show();
    }

    public void press_missing_data(View view) {
        Toast.makeText(this, "find user data", Toast.LENGTH_SHORT).show();
    }

    public void press_visit_website(View view) {
        Toast.makeText(this, "Linked our website", Toast.LENGTH_SHORT).show();
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