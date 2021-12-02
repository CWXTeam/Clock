package com.wqf.clock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class ClockActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int sourceID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Intent intent = getIntent();
        String mouldName = intent.getStringExtra("mouldName");
        String mode = intent.getStringExtra("mode");
        Log.d("debug:mouldName",mouldName);
        Log.d("debug:mode",mode);

        if (mouldName.equals("hanser")) {
            if (mode.equals("WORK")) {
                sourceID = R.raw.hanserdio;
            } else {
                sourceID = R.raw.hanserjojo;
            }
        } else if(mouldName.equals("duoduo")){
            if (mode.equals("WORK")){
                sourceID=R.raw.duoduowork;
            } else {
                sourceID=R.raw.duoduobreak;
            }
        }
        mediaPlayer = mediaPlayer.create(this,sourceID);
        mediaPlayer.start();
        //创建一个闹钟提醒的对话框,点击确定关闭铃声与页面

        String toWorkMessage = "要开始工作啦！";
        String toBreakMessage = "可以休息啦！";
        String message = null;
        if(mode.equals("WORK")){
            message = toWorkMessage;
        } else {
            message = toBreakMessage;
        }

        new AlertDialog.Builder(ClockActivity.this).setTitle("闹钟").setMessage(message)
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        ClockActivity.this.finish();
                    }
                }).show();
    }
}