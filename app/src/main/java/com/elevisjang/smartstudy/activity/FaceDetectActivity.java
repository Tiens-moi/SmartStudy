package com.elevisjang.smartstudy.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.elevisjang.smartstudy.MainActivity;
import com.elevisjang.smartstudy.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class FaceDetectActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "hahaha";
    private ImageButton imageButtonBack;
    private RoundedImageView faceRoundedImageView;
    private Button cameraButton;
    private TextView tvDetectPercent;
    private TextView tvDetectOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);
        initView();
    }

    private void initView(){
        imageButtonBack = (ImageButton) findViewById(R.id.ib_face_return);
        faceRoundedImageView = (RoundedImageView) findViewById(R.id.riv_face_bg);
        cameraButton = (Button) findViewById(R.id.bt_face_camera);
        tvDetectPercent = (TextView) findViewById(R.id.tv_sign_detect_percent);
        tvDetectOver = (TextView) findViewById(R.id.tv_sign_detect_over);


        imageButtonBack.setOnClickListener(this);
        cameraButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_face_return:
                Intent intent = new Intent(FaceDetectActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_face_camera:
                Log.i(TAG, "onClick: "+"拍照成功");
                showSuccessDialog();
                break;
        }
    }

    //签到成功提示
    private void showSuccessDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.sign_sucess_dialog,null,false);
        final AlertDialog successDialog = new AlertDialog.Builder(this).setView(view).create();
        successDialog.getWindow().setBackgroundDrawableResource(R.color.transparent); //设置dialog背景为透明

        ImageButton successImageButton = view.findViewById(R.id.ib_sign_success_cancel);
        successImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //签到成功，关闭对话框
                successDialog.dismiss();
            }
        });

        successDialog.show(); //弹出对话框
    }

    //签到失败提示
    private void showFailedDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.sign_fail_dialog,null,false);
        final AlertDialog failedDialog = new AlertDialog.Builder(this).setView(view).create();
        failedDialog.getWindow().setBackgroundDrawableResource(R.color.transparent); //设置dialog背景为透明

        ImageButton successImageButton = view.findViewById(R.id.ib_sign_fail_cancel);
        successImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //签到成功，关闭对话框
                failedDialog.dismiss();
            }
        });
        failedDialog.show(); //弹出对话框
    }
}
