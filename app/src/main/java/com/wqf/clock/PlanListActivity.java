package com.wqf.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlanListActivity extends AppCompatActivity implements View.OnClickListener {

    Button setPlan;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        setPlan=findViewById(R.id.setPlan);
        back=findViewById(R.id.back);
        setPlan.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==setPlan){
            Intent intent=new Intent(PlanListActivity.this, ConfigActivity.class);
            startActivity(intent);
        }
        if (v==back){
            Intent intent=new Intent(PlanListActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}