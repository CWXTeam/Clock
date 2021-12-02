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
    private static boolean isInPlanList(long planid) {
        //选出和p相同id的元组放入结果集中
        Cursor cursor = db.rawQuery("SELECT * FROM planlist WHERE planid = ?",
                new String[]{String.valueOf(planid)});
        //若结果集为空，则说明没有重复，反之则代表此plan已经在数据库中的planlist中
        if ((cursor.moveToFirst())) {
            Log.d(debug, "id为" + planid + "的plan已经在database中了");
            return true;
        }
        Log.d(debug, "id为" + planid + "的plan不在database中");
        return false;
    }

    //判断m是否已经存在于数据库中mouldlist表中
    private static boolean isInMouldList(String name) {
        //选出和p相同id的元组放入结果集中
        Cursor cursor = db.rawQuery("SELECT * FROM mouldlist WHERE name = ?",
                new String[]{name});
        //若结果集为空，则说明没有重复，反之则代表此mould已经在数据库中的mouldlist中
        if ((cursor.moveToFirst())) {
            Log.d(debug, "name为" + name + "的mould已经在database中了");
            return true;
        }
        Log.d(debug, "name为" + name + "的mould不在database中");
        return false;
    }

    //保存计划到TABLE planlist
    public static void savePlan(Plan p) {
        //如果计划p不在数据库中的planlist中，则需要插入这一条plan的信息
        if (!isInPlanList(p.planid)) {
            Log.d(debug, "id为" + p.planid + "的plan不在planlist中，可以插入这一条plan的信息");
            db.execSQL("INSERT INTO planlist(planid,name,description,worktime,breaktime,begintime,finishtime,mouldname)" +
                    "values(?,?,?,?,?,?,?,?)", new Object[]{p.planid, p.name, p.description, p.workTime, p.breakTime, p.beginTime, p.finishTime, p.mould.name});
        }

    }


    // 删除TABLE planlist 中的指定计划p
    public static void deletePlan(Plan p){
        if(!isInPlanList(p.planid)){
            Log.d(debug, "id为" + p.planid + "的plan不在planlist中，该次删除操作被跳过");
        }
        else{
            db.execSQL("DELETE FROM planlist WHERE planid = ?", new Object[]{p.planid});
            Log.d(debug, "id为" + p.planid + "的plan已删除");
        }
    }


    // 删除TABLE planlist 中所有计划p
    public static void deleteAllPlans(){
        db.execSQL("DELETE FROM planlist");
        Log.d(debug, "TABLE planlist 中所有的元组已被删除");
    }


    //保存mould到TABLE mouldlist
    public static void saveMould(Mould m) {
        //如果m不在数据库中的mouldlist中，则需要插入这一条plan的信息
        if (!isInMouldList(m.name)) {
            Log.d(debug, "name为" + m.name + "的mould不在mouldlist中，可以插入这一条mould的信息");
            db.execSQL("INSERT INTO mouldlist(name,breakclkpath,workclkpath)" +
                    "values(?,?,?)", new Object[]{m.name, m.breakClkPath, m.workClkPath});
        }
    }

    //更新TABLE planlist中p的信息
    public static void updatePlan(Plan p) {
        //如果计划p已经在planlist表中了，才能够进行更新数据，否则无法更新
        if (isInPlanList(p.planid)) {
            Log.d(debug, "id为" + p.planid + "的plan已在planlist表中，满足条件，可以更新");
            db.execSQL("UPDATE planList SET name = ?,description = ?,worktime=?,breaktime=?,begintime=?,finishtime=?,mouldname=? WHERE planid = ?",
                    new Object[]{p.name, p.description, p.workTime, p.breakTime, p.beginTime, p.finishTime, p.mould.name, p.planid});
        } else
            Log.d(debug, "id为" + p.planid + "的plan不在planlist表中，不满足条件，不可以更新");
    }

    //更新TABLE mouldlist中m的信息
    public static void updateMould(Mould m) {
        //如果m已经在mouldlist表中了，才能够进行更新数据，否则无法更新
        if (isInMouldList(m.name)) {
            Log.d(debug, "name为" + m.name + "的mould已在mouldlist表中，满足条件，可以更新");
            db.execSQL("UPDATE planList SET name = ?,breakclkpath = ?,workclkpath = ?",
                    new Object[]{m.name, m.breakClkPath, m.workClkPath});
        } else
            Log.d(debug, "name为" + m.name + "的mould不在mouldlist表中，不满足条件，不可以更新");
    }

    // 删除TABLE mouldlist中的指定模板m
    public static void deleteMoule(Mould m){
        if (isInMouldList(m.name)) {
            db.execSQL("DELETE FROM mouldlist WHERE name = ?", new Object[]{m.name});
            Log.d(debug, "name为" + m.name + "的mould已从mouldlist表中删除");
        } else
            Log.d(debug, "name为" + m.name + "的mould不在mouldlist表中，不满足条件，不可以删除");
    }


    // 删除TABLE mouldlist中的所有元组
    public static void deleteAllMoules(){
        db.execSQL("DELETE FROM mouldlist");
        Log.d(debug, "TABLE mouldlist 中所有的元组已被删除");
    }

    //返回TABLE planlist中对应planid的plan
    public static Plan loadPlan(long id) throws ClockException {
        Cursor cursor = db.rawQuery("SELECT * FROM planlist WHERE planid = ?",
                new String[]{String.valueOf(id)});
        //存在数据才返回true
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            long worktime = cursor.getLong(cursor.getColumnIndex("worktime"));
            long breaktime = cursor.getLong(cursor.getColumnIndex("breaktime"));
            long begintime = cursor.getLong(cursor.getColumnIndex("begintime"));
            long finishtime = cursor.getLong(cursor.getColumnIndex("finishtime"));
            String mouldname = cursor.getString(cursor.getColumnIndex("mouldname"));

            //根据mouldname,在mouldList中查找有无叫这个的mould，如果有，则让tempMould等于它
            Mould tempMould = null;
            if (isInMouldList(mouldname)) {
                tempMould = loadMould(mouldname);
            }
            Plan tempPlan = new Plan(name, description, worktime, breaktime, begintime, finishtime, tempMould);
            //记得把id也对应上，否则plan的id为默认值
            tempPlan.planid = id;
            tempPlan.setClocks();
            return tempPlan;
        }
        return null;

    }

    //返回对应名字的mould
    public static Mould loadMould(String name) {
        Cursor cursor = db.rawQuery("SELECT * FROM mouldlist WHERE name = ?",
                new String[]{name});
        if (cursor.moveToFirst()) {
            String workClkPath = cursor.getString(cursor.getColumnIndex("workclkpath"));
            String breakClkPath = cursor.getString(cursor.getColumnIndex("breakclkpath"));
            return new Mould(name, breakClkPath, workClkPath);
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
                if (isInMouldList(mouldname)) {
                    tempMould = loadMould(mouldname);
                }
                tempPlan = new Plan(name, description, worktime, breaktime, begintime, finishtime, tempMould);
                //记得把id也对应上，否则plan的id为默认值
                tempPlan.planid = planid;
                tempPlan.setClocks();

                tempPlanList.add(tempPlan);
            } while (cursor.moveToNext());
            return tempPlanList;
        }
        return tempPlanList;
    }

    //返回mouldlist中所有mould
    public static List<Mould> loadAllMoulds() {
        Mould tempMould = null;
        List<Mould> tempMouldList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM mouldlist", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String workClkPath = cursor.getString(cursor.getColumnIndex("workclkpath"));
                String breakClkPath = cursor.getString(cursor.getColumnIndex("breakclkpath"));
                tempMould = new Mould(name, breakClkPath, workClkPath);
                tempMouldList.add(tempMould);
            } while (cursor.moveToNext());
            return tempMouldList;
        }
        return tempMouldList;
    }
}
