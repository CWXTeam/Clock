package com.wqf.clock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class PlanAdapter extends ArrayAdapter<Plan> {
    private final int resourceId;

    public PlanAdapter(Context context, int textViewResourceId, List<Plan> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plan plan = getItem(position); //获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView planName = (TextView) view.findViewById(R.id.plan_name);
        TextView planDescription = (TextView) view.findViewById(R.id.plan_description);
        TextView planStartTime = (TextView) view.findViewById(R.id.plan_start_time);
        TextView planEndTime = (TextView) view.findViewById(R.id.plan_end_time);

        planName.setText(plan.name);
        planDescription.setText(plan.description);
        //时间不能用时间戳，要用正儿八经的人类时间。
        planStartTime.setText(TimeUtil.getStringTime(plan.beginTime));
        planEndTime.setText(TimeUtil.getStringTime(plan.finishTime));
        return view;
    }
}
