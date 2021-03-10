package app.gameproject;
/** Since 2021-03-05 By LeeJaeYoung
 Board Game Project */

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;

import app.gameproject.databinding.LoginActivityBinding;

public class LoginActivity extends AppCompatActivity {

    LoginActivityBinding binding;

    private String show_pwd = "not showing";
    private static final String TAG = "show_pwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText pwd = binding.loginEditPwd;
        Log.e(TAG,show_pwd);
        Log.e(TAG,pwd.getInputType()+"");

        binding.loginShowPwdIv.setOnClickListener(view1 -> {
            if (show_pwd.equals("not showing")) {
                binding.loginShowPwdIv.setImageResource(R.drawable.show_pwd);
                pwd.setInputType(144); // Invisible Text Password
                show_pwd = "showing";
                Log.e(TAG,show_pwd);
                Log.e(TAG,pwd.getInputType()+"");
            }else if (show_pwd.equals("showing")){
                binding.loginShowPwdIv.setImageResource(R.drawable.noshow_pwd);
                pwd.setInputType(129); // Visible Text Password
                show_pwd = "not showing";
                Log.e(TAG,show_pwd);
                Log.e(TAG,pwd.getInputType()+"");
            }
        });
    }

    public void press_login(View view) {
        if (binding.loginEditId.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your ID", Toast.LENGTH_SHORT).show();
            keyboardUp(binding.loginEditId);
        }else if (binding.loginEditPwd.getText().toString().equals("")) {
            Toast.makeText(this, "Input Your Password", Toast.LENGTH_SHORT).show();
            keyboardUp(binding.loginEditPwd);
        }else {
            Intent intent = new Intent(LoginActivity.this, MakingCharacter.class);
            startActivity(intent);
            finish();
        }
    }

    public void press_sign_up(View view) {
        //TODO Create SignUp Dialog
    }

    public void press_eidt_pwd(View view) {
        binding.loginEditPwd.setText("");
        keyboardUp(binding.loginEditPwd);
    }

    public void press_edit_id(View view) {
        binding.loginEditId.setText("");
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
}