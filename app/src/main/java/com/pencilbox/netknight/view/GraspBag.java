package com.pencilbox.netknight.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.pencilbox.netknight.R;


public class GraspBag extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn_graspleft;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.grasp_bag);
        init();
    }

    private void init() {

        btn_graspleft = (ImageButton) this.findViewById(R.id.btn_grasptopleft);

        btn_graspleft.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_grasptopleft:
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                    return;
                } else {
                    initmPopupWindowViewleft();
                    popupWindow.showAsDropDown(v, 0, 5);
                }
                break;
            case R.id.graspbtn_wall:
                Intent intent = new Intent();
                intent.setClass(this, MainTabbed.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.graspbtn_dariy:
                Intent intent1 = new Intent();
                intent1.setClass(this, DairyTabbed.class);
                startActivity(intent1);
                this.finish();
                break;
            default:
                break;
        }

    }

    private void initmPopupWindowViewleft() {
        View customView = getLayoutInflater().inflate(R.layout.graspleft_top,
                null, false);
        popupWindow = new PopupWindow(customView, 500, 600);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow.setAnimationStyle(R.style.ways);
        popupWindow.setOutsideTouchable(true);
        // 自定义view添加触摸事件

        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                return false;
            }
        });


        /** 在这里可以实现自定义视图的功能 */
        Button btn_wall = (Button) customView.findViewById(R.id.graspbtn_wall);
        Button btn_dariy = (Button) customView.findViewById(R.id.graspbtn_dariy);
        btn_wall.setOnClickListener(this);
        btn_dariy.setOnClickListener(this);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            startActivity(intent.setClass(this, MainTabbed.class));
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

