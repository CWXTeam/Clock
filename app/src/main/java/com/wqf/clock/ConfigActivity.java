package com.wqf.clock;

import static com.wqf.clock.MainActivity.planList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;

public class ConfigActivity extends AppCompatActivity {

    EditText config_name;
    EditText config_description;

    EditText begin_year;
    EditText begin_month;
    EditText begin_day;
    EditText begin_hour;
    EditText begin_minute;

    EditText finish_year;
    EditText finish_month;
    EditText finish_day;
    EditText finish_hour;
    EditText finish_minute;

    EditText work_time;
    EditText break_time;

    Button yes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        config_name=findViewById(R.id.config_name);
        config_description=findViewById(R.id.config_description);

        begin_year=findViewById(R.id.begin_year);
        begin_month=findViewById(R.id.begin_month);
        begin_day=findViewById(R.id.begin_day);
        begin_hour=findViewById(R.id.begin_hour);
        begin_minute=findViewById(R.id.begin_minute);

        finish_year=findViewById(R.id.finish_year);
        finish_month=findViewById(R.id.finish_month);
        finish_day=findViewById(R.id.finish_day);
        finish_hour=findViewById(R.id.finish_hour);
        finish_minute=findViewById(R.id.finish_minute);

        work_time=findViewById(R.id.work_time);
        break_time=findViewById(R.id.break_time);

        yes=findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //建立临时的plan
                Plan plan=new Plan();
                //plan的名称和简介直接录用
                plan.name=config_name.getText().toString();
                plan.description=config_description.getText().toString();

                //从editText中取得字符串
                String BeginYear=begin_year.getText().toString();
                String BeginMonth=begin_year.getText().toString();
                String BeginDay=begin_year.getText().toString();
                String BeginHour=begin_year.getText().toString();
                String BeginMinute=begin_year.getText().toString();

                //化为时间
                try {
                    plan.beginTime=TimeUtil.dateToStamp(BeginYear+"-"+BeginMonth+"-"+BeginDay+" "+BeginHour+":"+BeginMinute+":"+00);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String FinishYear=finish_year.getText().toString();
                String FinishMonth=finish_month.getText().toString();
                String FinishDay=finish_day.getText().toString();
                String FinishHour=finish_hour.getText().toString();
                String FinishMinute=finish_minute.getText().toString();

                try {
                    plan.finishTime=TimeUtil.dateToStamp(FinishYear+"-"+FinishMonth+"-"+FinishDay+" "+FinishHour+":"+FinishMinute+":"+00);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String WorkTime=work_time.getText().toString();
                plan.workTime = Long.parseLong(WorkTime)*60*1000;

                String BreakTime=break_time.getText().toString();
                plan.breakTime = Long.parseLong(BreakTime)*60*1000;

                //将plan录入planlist中
                planList.add(plan);

                //返回主页
                Intent intent = new Intent(ConfigActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}