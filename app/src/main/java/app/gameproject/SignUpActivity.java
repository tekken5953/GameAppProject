package app.gameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Objects;
import app.gameproject.Retrofit.MyAPI;
import app.gameproject.Retrofit.UserItem;
import app.gameproject.databinding.SignUpActivityBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maes.tech.intentanim.CustomIntent.customType;

public class SignUpActivity extends AppCompatActivity {

    SignUpActivityBinding binding;
    MyAPI mMyAPI;
    private static final String Retrofit = "retrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignUpActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void press_sign_up_ok(View view) {
        EditText name = binding.signupNameEt;
        EditText user_id = binding.signupIdEt;
        EditText pwd = binding.signupPasswordEt;
        EditText repeat_pwd = binding.signupRepeatPasswordEt;

        if (name.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your name", Toast.LENGTH_SHORT).show();
            keyboardUp(name);
        } else if (user_id.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your Id", Toast.LENGTH_SHORT).show();
            keyboardUp(user_id);
        } else if (pwd.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your Password", Toast.LENGTH_SHORT).show();
            keyboardUp(pwd);
        } else if (repeat_pwd.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your Repeat Password", Toast.LENGTH_SHORT).show();
            keyboardUp(repeat_pwd);
        } else if (!pwd.getText().toString().equals(repeat_pwd.getText().toString())) {
            Toast.makeText(this, "Password and Repeat password are Different", Toast.LENGTH_SHORT).show();
        } else if (pwd.getText().toString().length() < 6) {
            Toast.makeText(this, "Password must be longer than '6'", Toast.LENGTH_SHORT).show();
            pwd.setText("");
            repeat_pwd.setText("");
            keyboardUp(pwd);
        } else {

            BaseDialog baseDialog = new BaseDialog(SignUpActivity.this, R.layout.progressdial);
            baseDialog.show();

            initMyAPI(); // initial connect server

            Call<List<UserItem>> get_user_login = mMyAPI.get_user_by_user_id(binding.signupNameEt.getText().toString());
            get_user_login.enqueue(new Callback<List<UserItem>>() {
                @Override
                public void onResponse(@NotNull Call<List<UserItem>> call, @NotNull Response<List<UserItem>> response) {
                    if (Objects.requireNonNull(response.body()).toString().equals("[]")) {
                        final UserItem item = new UserItem();
                        item.setName(name.getText().toString());
                        item.setUser_id(user_id.getText().toString());
                        item.setPwd(pwd.getText().toString());
                        item.setRepeat_pwd(repeat_pwd.getText().toString());
                        Call<UserItem> post_user = mMyAPI.post_user(item); // call api method
                        post_user.enqueue(new Callback<UserItem>() { // post here
                            @Override
                            public void onResponse(@NotNull Call<UserItem> call, @NotNull Response<UserItem> response) {
                                if (baseDialog.isShowing()) {
                                    baseDialog.dismiss();
                                }
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Complete create your Account", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    customType(SignUpActivity.this, "up-to-bottom");
                                    finish();
                                    assert response.body() != null;
                                    Log.d(Retrofit, response.body().toString());
                                }
                                Log.d(Retrofit, "Status Code : " + response.code());
                            }

                            @Override
                            public void onFailure(@NotNull Call<UserItem> call, @NotNull Throwable t) {
                                if (baseDialog.isShowing()) {
                                    baseDialog.dismiss();
                                }
                                Log.d(Retrofit, "Fail msg : " + t.getMessage());
                                Toast.makeText(getApplicationContext(), "Complete create your Account", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                customType(SignUpActivity.this, "up-to-bottom");
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(SignUpActivity.this, "This id is already exist.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<UserItem>> call, Throwable t) {
                    if (baseDialog.isShowing()) {
                        baseDialog.dismiss();
                    }
                    Toast.makeText(SignUpActivity.this, "Failed to sign up\nplease restart app", Toast.LENGTH_SHORT).show();
                    Log.e(Retrofit, "Fail msg : " + t.getMessage());
                }
            });
        }
    }

    public void press_sign_up_cancel(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        customType(SignUpActivity.this, "up-to-bottom");
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        customType(SignUpActivity.this, "up-to-bottom");
        finish();
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

    private void initMyAPI() {
        final String URL = "http://10.0.2.2:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }
}