package com.men.myxposed;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Class<?> aClass = loadPackageParam.classLoader.loadClass("android.telephony.TelephonyManager");
        Log.d("aClass", "aClass:" + aClass.getName());
        XposedBridge.log("HAI_app:" + loadPackageParam.packageName);

        XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getDeviceId", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult("11111111111");
                    }
                }
        );

//        getMyLoginState(loadPackageParam);
        getAnotherDex(loadPackageParam);
//        getPlayerInfo1(loadPackageParam);
//        getLockStr(loadPackageParam);
//        hookOpenShare(loadPackageParam);
//        hideCommentActivity(loadPackageParam);
//        getFirstTime(loadPackageParam);
//        getGirlPrice(loadPackageParam);
//        getHHResult(loadPackageParam);

        //测试查询的包名都有什么
//            hookMuti(loadPackageParam);

//            getHookPackageNames(loadPackageParam);
    }


    private void getHookPackageNames(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        Log.d("packages:", loadPackageParam.packageName);


    }

    private void hookMuti(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (!loadPackageParam.packageName.equals("com.men.hhapplication")) return;

        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        ClassLoader cl = ((Context) param.args[0]).getClassLoader();
                        Class<?> hookclass = null;
                        try {
                            hookclass = cl.loadClass("com.men.hhapplication.SplashActivity");
                        } catch (Exception e) {
                            Log.e("MutiDex", "寻找XXX.XXX.ClassName失败", e);
                            return;
                        }
                        Log.e("MutiDex", "寻找XXX.XXX.ClassName成功");

                        XposedHelpers.findAndHookMethod(
                                hookclass,
                                "getResult",
                                new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        Log.d("zidingyi", "自定义");
                                    }

                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        Log.d("afterHookedMethod", "getResult:" + param.getResult());
                                    }
                                }
                        );

                    }
                }
        );
    }


    private void getHHResult(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("com.men.hhapplication.SplashActivity", loadPackageParam.classLoader, "getResult", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        Log.d("SplashActivity.param", param.args[0].toString());
                        Log.d("SplashActivity", param.getResult().toString());
                    }
                }
        );
    }


    private void getLockStr(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        if ("com.ss.android.ugc.aweme".equals(loadPackageParam.packageName)) {
            Log.d("begingetLockStr", "getLockStr");
        }

        XposedHelpers.findAndHookMethod(
                "com.hfdcxy.android.by.test.a",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "a",                                   //要hook的方法名
                String.class,                          //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        Log.i("Tiger_test", "a方法的第一个参数为:" + param.args[0].toString());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数
                        Log.i("Tiger_test", "a方法的返回值为:" + param.getResult());//方法的返回值只能放在afterHookedMethod中获取
                        //以下为修改
                        //param.args[0] = "235wtwerteq"  //如果要修改第一个参数可以直接这样写
                        //param.setResult("7f769c0f91efd402a23d63627f48f03e");   //param.setResult修改方法的返回值
                    }
                }
        );
    }

    private void hookOpenShare(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        if ("com.ss.android.ugc.aweme".equals(loadPackageParam.packageName)) {
            Log.d("hookOpenShare", "hookOpenShare");

        }

        XposedHelpers.findAndHookMethod(
                "android.widget.TextView",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "setText",                                   //要hook的方法名
                CharSequence.class,                          //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("beforeHookedMethod", "beforeHookedMethod:  putInt");
                        param.args[0] = 3000000;
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {

                        Log.d("afterHookedMethod", "afterHookedMethod:  putInt");

                        Log.d("args[1]", param.args[1] + "");
                        param.args[1] = 1000000;
                    }
                }
        );
    }


    private void getGirlPrice(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        if ("com.jimmy.beauty.pick".equals(loadPackageParam.packageName)) {
            Log.d("getGirlPrice", "getGirlPrice");
        }

        XposedHelpers.findAndHookMethod(
                "com.jimmy.beauty.pick.Util",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "getSharedPreferencesInt",                                   //要hook的方法名
                Context.class,                          //方法的参数类型 这里为String类
                String.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {

                        Log.i("getMoney", "金币金额::" + param.getResult().toString());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数
                        param.setResult(1200000);
                        //以下为修改
                        //param.args[0] = "235wtwerteq"  //如果要修改第一个参数可以直接这样写
                        //param.setResult("7f769c0f91efd402a23d63627f48f03e");   //param.setResult修改方法的返回值
                    }
                }
        );
    }


    private void getFirstTime(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        if ("com.jimmy.beauty.pick".equals(loadPackageParam.packageName)) {
            Log.d("getFirstTime", "getFirstTime");
        }

        XposedHelpers.findAndHookMethod(
                "com.jimmy.beauty.pick.Util",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "getFirstTime",                                   //要hook的方法名
                Context.class,                          //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        Log.i("getFirstTime", "getFirstTime: 方法进入了" + param.getResult());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数

                        param.setResult(true);
                        Log.i("getFirstTime", "getFirstTime: 方法进入了hook后" + param.getResult());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数

                        //以下为修改
                        //param.args[0] = "235wtwerteq"  //如果要修改第一个参数可以直接这样写
                        //param.setResult("7f769c0f91efd402a23d63627f48f03e");   //param.setResult修改方法的返回值
                    }
                }
        );
    }

    private void hideCommentActivity(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        if ("com.jimmy.beauty.pick".equals(loadPackageParam.packageName)) {
            Log.d("getFirstTime", "getFirstTime");
        }

        XposedHelpers.findAndHookMethod(
                "com.jimmy.beauty.pick.Util",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "getFirstTime",                                   //要hook的方法名
                Context.class,                          //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        Log.i("getFirstTime", "getFirstTime: 方法进入了" + param.getResult());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数

                        param.setResult(true);
                        Log.i("getFirstTime", "getFirstTime: 方法进入了hook后" + param.getResult());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数

                        //以下为修改
                        //param.args[0] = "235wtwerteq"  //如果要修改第一个参数可以直接这样写
                        //param.setResult("7f769c0f91efd402a23d63627f48f03e");   //param.setResult修改方法的返回值
                    }
                }
        );
    }

    private void getAnotherDex(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ClassLoader cl = ((Context) param.args[0]).getClassLoader();
                       final Class<?> hookclass ;//内部类
//                        Class<?> hookclass ;
                            try {
                                hookclass = XposedHelpers.findClass("com.sdbean.werewolf.model.SocketGetInfoAllBean$MyBean", cl);
//                                hookclass = cl.loadClass(" com.men.hhapplication.SplashActivity$GameTypeBean");
                            } catch (Exception e) {
                                Log.e("MutiDex", "寻找com.sdbean.werewolf.viewmodel.cz失败", e);
                                return;
                            }
                            Log.e("MutiDex", "寻找com.sdbean.werewolf.viewmodel.cz成功");
                            getPlayerInfo(hookclass);
                    }
                }
        );

    }

    private void getPlayerInfo(Class hookclass) {
        Log.d("getPlayerInfo", "getPlayerInfo_geti2:方法刚刚进入了 ");
        XposedHelpers.findAndHookMethod(
                hookclass,
                "getRole",
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("aparm.getResult", "getResult:"+param.getResult());

//                        param.setResult(1);

//                        XposedHelpers.setIntField(param.thisObject, "role", 1);
//                        param.setResult("1");
//                        Log.d("aparm.args[0]", "ageti2:"+param.args[0]);
                        Log.d("before.getResult", "getResult:"+param.getResult());
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("aparm.getResult", "getResult:"+param.getResult());
//                        param.setResult("1");
//                        Log.d("aparm.args[0]", "ageti2:"+param.args[0]);
                        Log.d(" after.getResult", "getResult:"+param.getResult());
                    }
                }
        );

    }


    private void getPlayerInfo1(XC_LoadPackage.LoadPackageParam loadPackageParam) {


//C4696b
        if ("com.sdbean.werewolf".equals(loadPackageParam.packageName)) {
            Log.d("getPlayerInfo", "getPlayerInfo方法进入了");
        }

        XposedHelpers.findAndHookMethod(
                "com.sdbean.werewolf.viewmodel.cz",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "c",                                   //要hook的方法名
                int.class,                       //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("bparm.args[0]", "beforeHookedMethod: i2:"+param.args[0]);
                        param.args[0]=1;
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        param.args[0]=1;
                        Log.d("aparm.args[0]", "afterHookedMethod: i2:"+param.args[0]);
                    }
                }
        );
    }

    private void getTtlrsFree(XC_LoadPackage.LoadPackageParam loadPackageParam) {


//C4696b
        if ("com.sdbean.werewolf".equals(loadPackageParam.packageName)) {
            Log.d("getTtlrsFree", "getTtlrsFree方法进入了");
        }

        XposedHelpers.findAndHookMethod(
                " com.sdbean.werewolf.view.LuckBoxFragment",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "b",                                   //要hook的方法名
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Intent intent = new Intent();
                        intent.getExtras();
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {

                    }
                }
        );
    }


    private void getMyLoginState(XC_LoadPackage.LoadPackageParam loadPackageParam) {


//C4696b
        if ("com.men.hhapplication".equals(loadPackageParam.packageName)) {
            Log.d("getMyLoginState", "getMyLoginState");
        } else {
            return;
        }

        XposedHelpers.findAndHookMethod(
                "com.men.hhapplication.SplashActivity$GameTypeBean",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "getType",                                   //要hook的方法名
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedHelpers.setIntField(param.thisObject,"role",1);
                        Log.d("ddd", "beforeHookedMethodgetMyLoginState: ");
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        Log.d("ddd", "afterHookedMethodMethodgetMyLoginState: ");
                    }
                }
        );
    }

}
