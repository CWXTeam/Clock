package com.wqf.clock;

import static com.wqf.clock.MainActivity.mouldList;
import static com.wqf.clock.MainActivity.myDBHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLUtils {
    private static final SQLiteDatabase db = myDBHelper.getWritableDatabase();
    private static final String debug = "SQLUtils中的日志信息";

    //判断planlist表中当前有多少个元素
    public static int getCountOfPlanInDB() {
        Cursor cursor = db.rawQuery("SELECT * FROM planlist", null);
        return cursor.getCount();
    }

    //判断p是否已经存在于数据库中planlist表中
    private static boolean isInPlanList(Plan p) {
        //选出和p相同id的元组放入结果集中
        Cursor cursor = db.rawQuery("SELECT * FROM planlist WHERE planid = ?",
                new String[]{String.valueOf(p.planid)});
        //若结果集为空，则说明没有重复，反之则代表此plan已经在数据库中的planlist中
        if ((cursor.moveToFirst())) {
            Log.d(debug, "id为" + p.planid + "的plan已经在database中了");
            return true;
        }
        Log.d(debug, "id为" + p.planid + "的plan不在database中");
        return false;
    }

    //保存计划到TABLE planlist
    public static void savePlan(Plan p) {
        //如果计划p不在数据库中的planlist中，则需要插入这一条plan的信息
        if (!isInPlanList(p)) {
            Log.d(debug, "id为" + p.planid + "的plan不在planlist中，可以插入这一条plan的信息");
            db.execSQL("INSERT INTO planlist(planid,name,description,worktime,breaktime,begintime,finishtime,mouldname)" +
                    "values(?,?,?,?,?,?,?,?)", new Object[]{p.planid, p.name, p.description, p.workTime, p.breakTime, p.beginTime, p.finishTime, p.mould.name});
        }

    }

    //更新TABLE planlist中p的信息
    public static void updatePlan(Plan p) {
        //如果计划p已经在planlist表中了，才能够进行更新数据，否则无法更新
        if (isInPlanList(p)) {
            Log.d(debug, "id为" + p.planid + "的plan已在planlist表中，满足条件，可以更新");
            db.execSQL("UPDATE planList SET name = ?,description = ?,worktime=?,breaktime=?,begintime=?,finishtime=?,mouldname=? WHERE planid = ?",
                    new Object[]{p.name, p.description, p.workTime, p.breakTime, p.beginTime, p.finishTime, p.mould.name, p.planid});
        } else
            Log.d(debug, "id为" + p.planid + "的plan不在planlist表中，不满足条件，不可以更新");
    }

    //返回TABLE planlist中对应planid的plan
    public static Plan loadPlan(long id) throws ClockException {
        Cursor cursor = db.rawQuery("SELECT * FROM planlist WHERE planid = ?",
                new String[]{String.valueOf(id)});
        //存在数据才返回true
        if (cursor.moveToFirst()) {
            long planid = cursor.getLong(cursor.getColumnIndex("planid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            long worktime = cursor.getLong(cursor.getColumnIndex("worktime"));
            long breaktime = cursor.getLong(cursor.getColumnIndex("breaktime"));
            long begintime = cursor.getLong(cursor.getColumnIndex("begintime"));
            long finishtime = cursor.getLong(cursor.getColumnIndex("finishtime"));
            String mouldname = cursor.getString(cursor.getColumnIndex("mouldname"));

            //根据mouldname,在mouldList中查找有无叫这个的mould，如果有，则让tempMould等于它
            Mould tempMould = null;
            for (Mould element : mouldList
            ) {
                if (element.name == mouldname)
                    tempMould = element;
            }
            if (tempMould == null) {
                Log.d(debug, "没有找到对应的模板");
            }
            Plan tempPlan = new Plan(name, description, worktime, breaktime, begintime, finishtime, tempMould);
            //记得把id也对应上，否则plan的id为默认值
            tempPlan.planid = planid;
            tempPlan.setClocks();
            return tempPlan;
        }
        return null;

    }

    //返回planlist中所有的plan
    public static List<Plan> loadAllPlans() throws ClockException {
        Cursor cursor = db.rawQuery("SELECT * FROM planlist", null);
        Plan tempPlan = null;
        List<Plan> tempPlanList = new ArrayList<>();
        //只有当结果集不为空时才返回，否则返回null
        if (cursor.moveToFirst()) {
            do {
                long planid = cursor.getLong(cursor.getColumnIndex("planid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                long worktime = cursor.getLong(cursor.getColumnIndex("worktime"));
                long breaktime = cursor.getLong(cursor.getColumnIndex("breaktime"));
                long begintime = cursor.getLong(cursor.getColumnIndex("begintime"));
                long finishtime = cursor.getLong(cursor.getColumnIndex("finishtime"));
                String mouldname = cursor.getString(cursor.getColumnIndex("mouldname"));
                //根据mouldname,在mouldList中查找有无叫这个的mould，如果有，则让tempMould等于它
                Mould tempMould = null;
                for (Mould element : mouldList
                ) {
                    if (element.name == mouldname)
                        tempMould = element;
                }
                if (tempMould == null) {
                    Log.d(debug, "没有找到对应的模板");
                }
                tempPlan = new Plan(name, description, worktime, breaktime, begintime, finishtime, tempMould);
                //记得把id也对应上，否则plan的id为默认值
                tempPlan.planid = planid;
                tempPlan.setClocks();

                tempPlanList.add(tempPlan);
            } while (cursor.moveToNext());
            return tempPlanList;
        }
        return null;
    }
}
