package com.overdose.homeschooljoint.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.core.util.Preconditions.checkNotNull;


/**
 * 用于管理Fragment跳转
 */
public class ActivityUtils {

    //管理所有的Activity
    private static HashMap<String, AppCompatActivity> mActivityHashMap = new HashMap<>();
    private static List<String> payActList = new LinkedList<>();

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, String tag) {
        addFragmentToActivity(fragmentManager, fragment, frameId, tag, 0, 0);
    }

    private static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId, String tag, int animStar, int animEnd) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag)
                .commitAllowingStateLoss();
    }



    public static String onSkipBiaoShi(String mark, String... arrays) {
        String skip = "";
        for (String temp : arrays) {
            String s = temp.substring(temp.lastIndexOf(".") + 1, temp.length());
            if (s.equals(mark)) {
                skip = temp;
                break;
            }
        }
        return skip;
    }

    /**
     * 把要finish的Activity加到容器中
     * <p/>
     * Date: 2016/4/28
     * Time: 16:49
     */
    public static void addActivity(String name, AppCompatActivity activity) {
        mActivityHashMap.put(name, activity);
    }


    public static AppCompatActivity getActivity(String acString) {
        AppCompatActivity activity = null;
        if (!mActivityHashMap.isEmpty()) {
            activity = mActivityHashMap.get(acString);
        }
        return activity;
    }

    public static boolean removeActivity(String acString) {
        if (!mActivityHashMap.isEmpty()) {
            mActivityHashMap.remove(acString);
        }
        return true;
    }


    public static void removeAllActivity() {
        for (String key : mActivityHashMap.keySet()) {
            mActivityHashMap.get(key).finish();
        }
    }

    // 遍历所有Activity并finish
    public static void exit() {
        for (String key : mActivityHashMap.keySet()) {
            mActivityHashMap.get(key).finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static boolean isLoginLast() {
        return mActivityHashMap.size() == 1;
    }
}
