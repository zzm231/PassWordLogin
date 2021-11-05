package com.swu.zzm.java4;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText user;
    private EditText password;
    private Button loginBtn;
    private ImageView leftArm;
    private ImageView rightArm;
    private ImageView leftHand;
    private ImageView rightHand;

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
        leftArm = findViewById(R.id.iv_left_arm);
        rightArm = findViewById(R.id.iv_right_arm);
        leftHand = findViewById(R.id.iv_left_hand);
        rightHand = findViewById(R.id.iv_right_hand);

        // 监听内容改变 按钮是否可以点击
        user.addTextChangedListener(this);
        password.addTextChangedListener(this);

        // 监听EditText的焦点变化 控制是否需要捂住眼睛
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true){
                    // 捂住眼睛
                    close();
                }else {
                    // 放开眼睛
                    open();
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

    // 闭眼
    public void close(){
        // 旋转翅膀 左边
        RotateAnimation rAnim = new RotateAnimation(0,150,
                leftArm.getWidth(),0f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        leftArm.startAnimation(rAnim);

        // 旋转翅膀 右边
        RotateAnimation lrAnim = new RotateAnimation(0,-150,
                0f,0f);
        lrAnim.setDuration(500);
        lrAnim.setFillAfter(true);

        rightArm.startAnimation(lrAnim);

        TranslateAnimation down = (TranslateAnimation) AnimationUtils.loadAnimation(this,R.anim.hand_down_anim);
        leftHand.startAnimation(down);
        rightHand.startAnimation(down);
    }

    // 睁眼
    public void open(){
        // 旋转翅膀 左边
        RotateAnimation rAnim = new RotateAnimation(160,0,
                leftArm.getWidth(),0f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        leftArm.startAnimation(rAnim);

        // 旋转翅膀 右边
        RotateAnimation lrAnim = new RotateAnimation(-160,0,
                0f,0f);
        lrAnim.setDuration(500);
        lrAnim.setFillAfter(true);

        rightArm.startAnimation(lrAnim);

        TranslateAnimation up = (TranslateAnimation) AnimationUtils.loadAnimation(this,R.anim.hand_up_anim);
        leftHand.startAnimation(up);
        rightHand.startAnimation(up);
    }
}