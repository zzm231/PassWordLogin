package com.swu.zzm.java4;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText user;
    private EditText password;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews(){
        user = findViewById(R.id.et_user);
        password = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.bt_login);

        // 监听内容改变 按钮是否可以点击
        user.addTextChangedListener(this);
        password.addTextChangedListener(this);

        // 监听EditTexte的焦点变化 控制是否需要捂住眼睛
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true){
                    // 捂住眼睛
                    Toast.makeText(getApplicationContext(),"捂住眼睛",Toast.LENGTH_SHORT).show();
                }else {
                    // 放开眼睛
                    Toast.makeText(getApplicationContext(),"打开",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            // 隐藏键盘
            // 1.获取系统输入的管理器
            InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            // 2.隐藏键盘
            inputManager.hideSoftInputFromWindow(user.getWindowToken(),0);

            // 3.取消焦点
            View focusView = getCurrentFocus();
            if (focusView == null){
                getCurrentFocus().clearFocus();
            }
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        // 判断两个输入框是否都有内容
        if (user.getText().toString().length() > 0 && password.getText().toString().length() > 0){
            // 按钮可以点击了
            loginBtn.setEnabled(true);
        }else {
            // 按钮不能点击
            loginBtn.setEnabled(false);
        }
    }
}