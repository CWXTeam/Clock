package com.wqf.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //组件

    ImageView homeImage;
    TextView homeText;
    Button setPlanList;
    Button save;
    Button test;
    //需要用到的全局变量

    protected static MyDBOpenHelper myDBHelper;
    //保存的配置的目录
    protected final static String path = "/data/data/com.wqf.clock/shared_prefs";
    //当前检测到的Plan
    protected static List<Plan> planList = new ArrayList<>();
    //当前检测到的模板
    protected static List<Mould> mouldList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定组件，并设置监听器
        bindView();
        //第一次启动活动时就链接好数据库
        myDBHelper = new MyDBOpenHelper(MainActivity.this, "my.db", null, 1);
    }

    private void bindView() {
        homeImage = findViewById(R.id.homeImage);
        homeText = findViewById(R.id.homeText);

        setPlanList = findViewById(R.id.setPlanList);
        save = findViewById(R.id.save);
        test = findViewById(R.id.test);

        setPlanList.setOnClickListener(this);
        save.setOnClickListener(this);
        test.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (Plan plan : planList
        ) {
            SQLUtils.savePlan(plan);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == setPlanList) {
            Intent intent = new Intent(MainActivity.this, PlanListActivity.class);
            startActivity(intent);
        }
        if (v == save) {
//            保存所有配置

        }
        if (v == test) {
            try {
                planList = SQLUtils.loadAllPlans();
            } catch (ClockException e) {
                e.printStackTrace();
            }

            homeText.setText("haole");
        }
    }
}