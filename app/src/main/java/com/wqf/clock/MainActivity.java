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

        //测试：
        Plan plan1=new Plan();
        plan1.name="学习英语";
        plan1.description="学习英语非常重要，所以我要努力";
        plan1.beginTime=1637473520000L;
        plan1.finishTime=1637480720231L;

        Plan plan2=new Plan();
        plan2.name="学习数学";
        plan2.description="学习数学不重要，所以我不要努力";
        plan2.beginTime=1637473520000L;
        plan2.finishTime=1637480790231L;

        planList.add(plan1);
        planList.add(plan2);
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