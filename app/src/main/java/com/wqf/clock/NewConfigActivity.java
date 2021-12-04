package com.wqf.clock;

import static com.wqf.clock.MainActivity.planList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewConfigActivity extends AppCompatActivity implements View.OnClickListener {

    Button name;
    Button description;
    Button beginTime;
    Button finishTime;
    Button workTime;
    Button breakTime;
    Button chooseMould;
    Button yes;

    Plan plan = new Plan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_config);
        bindview();
    }

    private void bindview() {

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        beginTime = findViewById(R.id.beginTime);
        finishTime = findViewById(R.id.finishTime);
        workTime = findViewById(R.id.workTime);
        breakTime = findViewById(R.id.breakTime);
        chooseMould = findViewById(R.id.chooseMould);
        yes = findViewById(R.id.yes);


        name.setOnClickListener(this);
        description.setOnClickListener(this);
        beginTime.setOnClickListener(this);
        finishTime.setOnClickListener(this);
        workTime.setOnClickListener(this);
        breakTime.setOnClickListener(this);
        chooseMould.setOnClickListener(this);
        yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == name) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.name_edit, null);

            EditText edit_name = view.findViewById(R.id.edit_name);

            new AlertDialog.Builder(this).setView(view).setTitle("计划名称").setMessage("输入计划的名称吧！")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            name.setText(edit_name.getText().toString());
                            plan.name = edit_name.getText().toString();
                        }
                    }).show();
        }

        if (v == description) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.description_edit, null);

            EditText edit_description = view.findViewById(R.id.edit_description);

            new AlertDialog.Builder(this).setView(view).setTitle("计划描述").setMessage("输入计划的描述吧！")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            description.setText(edit_description.getText().toString());
                            plan.description = edit_description.getText().toString();
                        }
                    }).show();
        }

        if (v == beginTime) {

            Calendar currentTime = Calendar.getInstance();
            new TimePickerDialog(this, 0,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view,
                                              int hourOfDay, int minute) {
                            //设置当前时间
                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(System.currentTimeMillis());
                            // 根据用户选择的时间来设置Calendar对象
                            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            c.set(Calendar.MINUTE, minute);
                            beginTime.setText(TimeUtil.getStringTime(c.getTimeInMillis()));
                            plan.beginTime = c.getTimeInMillis();
                        }
                    }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                    .get(Calendar.MINUTE), true).show();
        }

        if (v == finishTime) {

            Calendar currentTime = Calendar.getInstance();
            new TimePickerDialog(this, 0,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view,
                                              int hourOfDay, int minute) {
                            //设置当前时间
                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(System.currentTimeMillis());
                            // 根据用户选择的时间来设置Calendar对象
                            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            c.set(Calendar.MINUTE, minute);
                            finishTime.setText(TimeUtil.getStringTime(c.getTimeInMillis()));
                            plan.finishTime = c.getTimeInMillis();
                        }
                    }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                    .get(Calendar.MINUTE), true).show();
        }

        if (v == workTime) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.worktime_edit, null);

            EditText edit_workTime = view.findViewById(R.id.edit_workTime);

            new AlertDialog.Builder(this).setView(view).setTitle("单次工作/学习时间").setMessage("时间以分钟为单位哦~")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            workTime.setText(edit_workTime.getText().toString());
                            plan.workTime = Long.parseLong(edit_workTime.getText().toString()) * 60 * 1000;
                        }
                    }).show();
        }

        if (v == breakTime) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.breaktime_edit, null);

            EditText edit_breakTime = view.findViewById(R.id.edit_breakTime);

            new AlertDialog.Builder(this).setView(view).setTitle("单次休息时间").setMessage("时间以分钟为单位哦~")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            breakTime.setText(edit_breakTime.getText().toString());
                            plan.breakTime = Long.parseLong(edit_breakTime.getText().toString()) * 60 * 1000;
                            Log.d("debug", String.valueOf(plan.breakTime));
                        }
                    }).show();
        }

        if (v == chooseMould) {
            final int[] index = {0};
            String[] items = new String[]{"hanser", "多多poi"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请选择闹铃模板:");
            builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    index[0] = which;
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (index[0] == 0)
                        plan.mould.name = "hanser";
                    else
                        plan.mould.name = "duoduo";
                    chooseMould.setText(plan.mould.name);
                    Log.d("debug", plan.mould.name);
                }
            });
            builder.show();
        }

        if (v == yes) {
            boolean isLegal = true;
            //判断得到的plan是否合法
            //首先输出日志信息到控制台方便直接看出有无错误
            Log.d("debug:检查生成的plan是否合法-name", plan.name);
            Log.d("debug:检查生成的plan是否合法-description", plan.description);
            Log.d("debug:检查生成的plan是否合法-begintime", TimeUtil.getStringTime(plan.beginTime));
            Log.d("debug:检查生成的plan是否合法-finishtime", TimeUtil.getStringTime(plan.finishTime));
            Log.d("debug:检查生成的plan是否合法-worktime", plan.workTime / 1000 / 60 + "");
            Log.d("debug:检查生成的plan是否合法-breaktime", plan.breakTime / 1000 / 60 + "");
            Log.d("debug:检查生成的plan是否合法-mouldname", plan.mould.name);
            //第一道检测工序：检测plan本身的各属性是否合法
            if (plan.workTime <= 0 || plan.breakTime <= 0 || plan.finishTime <= plan.beginTime || plan.workTime > (plan.finishTime - plan.beginTime) || plan.breakTime > (plan.finishTime = plan.beginTime))
                isLegal = false;
            //判断生成的plan是否跟planList中任意一个plan存在交集，只要与其中任意一个存在交集，则不满足条件
            for (Plan p : planList
            ) {
                if (plan.haveIntersectionWith(p)) {
                    isLegal = false;
                    break;
                }
            }
            //如果合法则将plan存入planlist并存入数据库保存
            if (isLegal) {
                //首先还是得setClocks，尽管数据库里不存放计划对应的闹钟
                //因为如果不得出闹钟，那么用户设置的这个计划就不能被实施，只能重新打开app，在主页加载所有plan之后才能生效
                try {
                    plan.setClocks();
                } catch (ClockException e) {
                    e.printStackTrace();
                }
                planList.add(plan);
                SQLUtils.savePlan(plan);

                this.finish();
            } else {
                Toast.makeText(this, "请检查您设置的时间是否合法", Toast.LENGTH_SHORT).show();
            }
        }
    }
}