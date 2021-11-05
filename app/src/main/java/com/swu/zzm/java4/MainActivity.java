package com.swu.zzm.java4;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText psaaword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews(){
        user = findViewById(R.id.et_user);
        psaaword = findViewById(R.id.et_password);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            // 隐藏键盘
            // 1.获取系统输入的管理器
            InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            // 2.隐藏键盘
            inputManager.hideSoftInputFromWindow(user.getWindowToken(),0);
        }
        return true;
    }
}