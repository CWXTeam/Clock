package com.wqf.clock;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtils {
    public static List<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            String absolutePath=files[i].getAbsolutePath();
            String Path=absolutePath.replace("/data/data/com.wqf.clock/shared_prefs/","");
            Path=Path.replace(".xml","");
            s.add(Path);
        }
        return s;
    }
}
