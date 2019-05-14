package com.xwdz.splash;

import android.content.Context;
import android.content.Intent;

/**
 * @author xingwei.huang (xwdz9989@gmail.com)
 * @since v1.0.0
 */
public class SimpleScreensListener {


    public SimpleScreensListener() {
    }

    public void onReceiverActionUserPresent(Context context, Intent intent, Class<?> splashClass) {
        startSplashActivity(context, splashClass);
    }

    public void onReceiverActionOFF(Context context, Intent intent, Class<?> splashClass) {

    }

    public void onReceiverActionON(Context context, Intent intent, Class<?> splashClass) {

    }


    static void startSplashActivity(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
