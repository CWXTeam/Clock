package com.wqf.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView diana;
    TextView textView;
    static String path="/data/data/com.wqf.clock/shared_prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.diana_said);
        SpUtils sp=SpUtils.getInstance("user");
        sp.save("name","xiaoming");
        sp.save("age",24);
        String str=sp.getString("name","");
        textView.setText(str);
        List<String> arrayList=MyFileUtils.getFilesAllName(path);
        textView.setText(arrayList.get(0));
    }
}