package com.wqf.clock;

import static com.wqf.clock.MainActivity.planList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlanListActivity extends AppCompatActivity implements View.OnClickListener {

    Button setPlan;//点击这个按钮新增一个计划
    Button back;//点击这个按钮返回主页
    ListView listView;
    PlanAdapter arrayAdapter;
    List<Plan> planList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);

        setPlan = findViewById(R.id.setPlan);
        back = findViewById(R.id.back);

        setPlan.setOnClickListener(this);
        back.setOnClickListener(this);


        planList.addAll(MainActivity.planList);
        arrayAdapter = new PlanAdapter(PlanListActivity.this, R.layout.plan_items, planList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plan plan = planList.get(position);
                //当这个list被选中之后要干嘛
                Toast.makeText(PlanListActivity.this, plan.name, Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("debug","PlanListActivity中onCreate()方法已调用");
    }

    @Override
    protected void onStart() {
        super.onStart();
        planList.clear();
        planList.addAll(MainActivity.planList);
        arrayAdapter.notifyDataSetChanged();
        Log.d("debug","PlanListActivity中onStart()方法已调用");
    }

    @Override
    public void onClick(View v) {
        if (v == setPlan) {
            Intent intent = new Intent(PlanListActivity.this, NewConfigActivity.class);
            startActivity(intent);
        }
        if (v == back) {
//            Intent intent = new Intent(PlanListActivity.this, MainActivity.class);
//            startActivity(intent);
            PlanListActivity.this.finish();
        }
    }
}