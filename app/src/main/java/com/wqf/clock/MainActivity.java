package com.wqf.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //组件

    ImageView homeImage;
    TextView homeText;
    Button setPlanList;
    Button save;

    //需要用到的全局变量

    //保存的配置的目录
    protected final static String path="/data/data/com.wqf.clock/shared_prefs";
    //当前检测到的Plan
    protected static List<Plan> planList=new ArrayList<>();
    //当前检测到的模板
    protected static List<Mould> MouldList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeImage=findViewById(R.id.homeImage);
        homeText=findViewById(R.id.homeText);
        setPlanList=findViewById(R.id.setPlanList);
        save=findViewById(R.id.save);
        setPlanList.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==setPlanList){
            Intent intent=new Intent(MainActivity.this,PlanListActivity.class);
            startActivity(intent);
        }
        if (v==save){
//            保存所有配置
        }
    }
}