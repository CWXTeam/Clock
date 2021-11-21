package com.wqf.clock;

public class IOUitils {

    public static void savePlan(Plan plan) {
        // 保存plan为plan.xml文件
        SpUtil sp = SpUtils.getInstance(plan.name);
        sp.save("object", "plan");  // 此项说明文件保存对象的类型为Plan
        sp.save("plan", plan.name);
        sp.save("description", plan.description);
        sp.save("workTime", plan.workTime);
        sp.save("breakTime", plan.breakTime);
        sp.save("beginTime", plan.beginTime);
        sp.save("finishTime", plan.finishTime);
        sp.save("mould", plan.mould.name);
    }

    public static void saveMould(Mould mould) {
        // 保存mould为mould.xml文件
        SpUtil sp = SpUtils.getInstance(mould.name);
        sp.save("object", "mould");  // 此项说明文件保存对象的类型为Mould
        sp.save("mould", mould.name);
    }

    public static Plan loadPlan(String name) {
        // 尝试从名字为name的文件中读取出一个类型为Plan的对象
        // 若文件储存的对象类型为Plan，则根据文件内容构造出plan并返回
        // 若文件储存对象类型不为Plan或不存在该文件则返回null
        String[] fileArray = SpUtil.getFilename();  // 获取所有文件名

        for (String file : fileArray) {  // 遍历所有文件名
            if (file.matches(name)) {  // 若文件名与name匹配
                SpUtil sp = SpUtils.getInstance(file);

                if (sp.getString("object", "").matches("plan")) {  // 若文件储存的对象类型为Plan
                    Plan plan = new Plan();

                    plan.name = sp.getString("name", "未知计划");
                    plan.descroption = sp.getString("description", "");
                    plan.workTime = sp.getLong("workTime", 0);
                    plan.breakTime = sp.getLong("breakTime", 0);
                    plan.beginTime = sp.getLong("beginTime", 0);
                    plan.finishTime = Sp.getLong("finishTime", plan.beginTime);
                    plan.mould = new Mould(sp.getString("mould", "新模块"));
                    plan.setClocks();

                    reurn plan;
                } else {
                    return null;
                }
            }
        }
        return null;
    }


    public static Plan loadMould(String name) {
        // 尝试从名字为name的文件中读取出一个类型为Mould的对象
        // 若文件储存的对象类型为Mould，则根据文件内容构造出mould并返回
        // 若文件储存对象类型不为Mould或不存在该文件则返回null
        String[] fileArray = SpUtil.getFilename();  // 获取所有文件名

        for (String file : fileArray) {  // 遍历所有文件名
            if (file.matches(name)) {  // 若文件名与name匹配
                SpUtil sp = SpUtils.getInstance(file);

                if (sp.getString("object", "").matches("mould")) {  // 若文件储存的对象类型为Mould
                    Mould mould = new Mould();
                    mould.name = sp.getString("name", "新模块");

                    reurn mould;
                } else {
                    return null;
                }
            }
        }
        return null;
    }


    public static ArrayList<Plan> loadAllPlan() {
        // 读取所有文件中的Plan对象到ArrayList中，返回这个ArrayList对象
        // 若没有读取到任何一个Plan对象，则返回的ArrayList对象的容器为空
        String[] fileArray = SpUtil.getFilename();
        ArrayList<Plan> plans = new ArrayList<Plan>();
        Plan plan = null;
        for (String file : fileArray) {
            if ((plan = IOUitils.loadPlan(file)) != null) {
                plans.add(plan);
            }
        }

        return plans;
    }


    public static ArrayList<Mould> loadAllMould() {
        // 读取所有文件中的Plan对象到ArrayList中，返回这个ArrayList对象
        // 若没有读取到任何一个Plan对象，则返回的ArrayList对象的容器为空
        String[] fileArray = SpUtil.getFilename();
        ArrayList<Mould> moulds = new ArrayList<Mould>();
        Mould mould = null;
        for (String file : fileArray) {
            if ((mould = IOUitils.loadMould(file)) != null) {
                plans.add(mould);
            }
        }

        return plans;
    }
}