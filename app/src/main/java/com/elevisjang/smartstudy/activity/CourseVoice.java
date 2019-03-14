package com.elevisjang.smartstudy.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.elevisjang.smartstudy.R;
import com.elevisjang.smartstudy.asrfinishjson.AsrFinishJsonData;
import com.elevisjang.smartstudy.asrpartialjson.AsrPartialJsonData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CourseVoice extends AppCompatActivity implements EventListener {

    private static final String TAG = "CourseVoice";

    private ImageButton start,stop;
    private Chronometer chronometer;
    private TextView tvParseResult;

    private EventManager asr;
    private boolean logTime = true;
    private String final_result;

    protected boolean enableOffline = false; // 测试离线命令词，需要改成true


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_voice);

        initView();
        initPermission();
        asr = EventManagerFactory.create(this,"asr");
        asr.registerListener(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60);
                chronometer.setFormat("0" + String.valueOf(hour) + ":%s");
                chronometer.start();
                startRec();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());//清空
                stopRec();
            }
        });

    }

    private void stopRec() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }

    private void startRec() {
        start.setEnabled(false);
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        String event = null;
        event = SpeechConstant.ASR_START;

        if(enableOffline){
            params.put(SpeechConstant.DECODER,2);
        }

        params.put(SpeechConstant.PID, 1536); // 默认1536
        params.put(SpeechConstant.DECODER, 0); // 纯在线(默认)
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN); // 语音活动检测
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 2000); // 不开启长语音。开启VAD尾点检测，即静音判断的毫秒数。建议设置800ms-3000ms
        params.put(SpeechConstant.ACCEPT_AUDIO_DATA, false);// 是否需要语音音频数据回调
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);// 是否需要语音音量数据回调

        String json = null;
        json = new JSONObject(params).toString();// 这里可以替换成你需要测试的json
        asr.send(event, json, null, 0, 0);
        //printResult("输入参数：" + json);
    }

    private void printResult(String result) {
        if(logTime){
            //result += " ;time = " + System.currentTimeMillis();
        }
        result += "\n";
        //Log.i(getClass().getName(),result);
        tvParseResult.append(result + "\n");
    }

    //动态权限申请
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for(String perm : permissions){
            if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this,perm)){
                toApplyList.add(perm);
                //进入这里代表没有权限
            }
        }

        String tmpList[] = new String[toApplyList.size()];
        if(!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(this,toApplyList.toArray(tmpList),123);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e("---------","被允许");
                }
                else{
                    Log.e("---------","被拒绝");
                }
                return;
        }
    }


    //初始化控件
    private void initView() {
        tvParseResult = (TextView) findViewById(R.id.tvParseResult);
        chronometer = (Chronometer) findViewById(R.id.chronometer1);
        start = (ImageButton) findViewById(R.id.start_bt);
        stop = (ImageButton) findViewById(R.id.stop_bt);
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        String result = "";

        if (length > 0 && data.length > 0) {
            result += ", 语义解析结果：" + new String(data, offset, length);
        }

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            // 引擎准备就绪，可以开始说话
            result += "引擎准备就绪，可以开始说话";

        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN)) {
            // 检测到用户的已经开始说话
            result += "检测到用户的已经开始说话";

        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)) {
            // 检测到用户的已经停止说话
            result += "检测到用户的已经停止说话";
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // 临时识别结果, 长语音模式需要从此消息中取出结果
            result += "识别临时识别结果";
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
//            Log.d(TAG, "Temp Params:"+params);
            parseAsrPartialJsonData(params);
        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
            // 识别结束， 最终识别结果或可能的错误
            result += "识别结束";
            asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
            Log.d(TAG, "Result Params:"+params);
            parseAsrFinishJsonData(params);
        }
        //printResult(result);
    }

    private void parseAsrFinishJsonData(String data) {
        Log.d(TAG, "parseAsrFinishJsonData data:"+data);
        Gson gson = new Gson();
        AsrFinishJsonData jsonData = gson.fromJson(data, AsrFinishJsonData.class);
        String desc = jsonData.getDesc();
        if(desc !=null && desc.equals("Speech Recognize success.")){
            tvParseResult.setText(final_result);
        }else{
            String errorCode = "\n错误码:" + jsonData.getError();
            String errorSubCode = "\n错误子码:"+ jsonData.getSub_error();
            String errorResult = errorCode + errorSubCode;
            tvParseResult.setText("解析错误,原因是:" + desc + "\n" + errorResult);
        }
    }


    private void parseAsrPartialJsonData(String data) {
        Log.d(TAG, "parseAsrPartialJsonData data:"+data);
        Gson gson = new Gson();
        AsrPartialJsonData jsonData = gson.fromJson(data, AsrPartialJsonData.class);
        String resultType = jsonData.getResult_type();
        Log.d(TAG, "resultType:"+resultType);
        if(resultType != null && resultType.equals("final_result")){
            final_result = jsonData.getBest_result();
//            tvParseResult.setText("解析结果：" + final_result);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        asr.send(SpeechConstant.ASR_CANCEL,"{}",null,0,0);
        if(enableOffline){
            asr.send(SpeechConstant.ASR_KWS_UNLOAD_ENGINE, null, null, 0, 0);
        }
        asr.unregisterListener(this);
    }


}
