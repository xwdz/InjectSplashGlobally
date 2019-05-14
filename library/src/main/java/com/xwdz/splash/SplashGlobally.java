package com.xwdz.splash;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


/**
 * @author xingwei.huang (xwdz9989@gmail.com)
 * @since v1.0.0
 */
public class SplashGlobally {

    private static final String TAG = SplashGlobally.class.getSimpleName();

    private int mForegroundCount;
    private int mIgnoreBufferCount;
    private Class<?> mClass;

    private AppScreenReceiver mAppScreenReceiver;
    private SimpleScreensListener mSimpleScreensListener;

    private final class AppScreenReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (Intent.ACTION_USER_PRESENT.equals(action)) {
                mSimpleScreensListener.onReceiverActionUserPresent(context, intent, mClass);
            } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
                mSimpleScreensListener.onReceiverActionON(context, intent, mClass);
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                mSimpleScreensListener.onReceiverActionOFF(context, intent, mClass);
            }
        }
    }


    public static void inject(Application application, final Class<?> splashClass) {
        new SplashGlobally().init(application, splashClass);
    }

    public static void inject(Application application, final Class<?> splashClass, SimpleScreensListener simpleScreensListener) {
        new SplashGlobally().init(application, splashClass, simpleScreensListener);
    }


    private void init(Application application, final Class<?> splashClazz) {
        try {
            init(application, splashClazz, new SimpleScreensListener());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void init(Application application, final Class<?> splashClazz, SimpleScreensListener simpleScreensListener) {
        this.mClass = splashClazz;
        this.mSimpleScreensListener = simpleScreensListener == null ? new SimpleScreensListener() : simpleScreensListener;

        application.registerActivityLifecycleCallbacks(new SimpleLifecycleListener() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                registerAppScreenReceiver(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (mForegroundCount <= 0) {
                    SimpleScreensListener.startSplashActivity(activity, splashClazz);
                }
                if (mIgnoreBufferCount < 0) {
                    mIgnoreBufferCount++;
                } else {
                    mForegroundCount++;
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (activity.isChangingConfigurations()) {
                    mIgnoreBufferCount--;
                } else {
                    mForegroundCount--;
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
                unregisterAppScreenReceiver(activity);
            }
        });
    }


    private void registerAppScreenReceiver(Context context) {
        if (mAppScreenReceiver == null) {
            mAppScreenReceiver = new AppScreenReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(mAppScreenReceiver, filter);
    }

    private void unregisterAppScreenReceiver(Context context) {
        if (mAppScreenReceiver == null) {
            return;
        }
        context.unregisterReceiver(mAppScreenReceiver);
    }

    private abstract static class SimpleLifecycleListener implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

}
