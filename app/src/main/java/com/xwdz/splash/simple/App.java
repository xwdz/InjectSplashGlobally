package com.xwdz.splash.simple;

import android.app.Application;

import com.xwdz.splash.InjectSplashGlobally;


/**
 * @author xingwei.huang (xwdz9989@gmail.com)
 * @since v1.0.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InjectSplashGlobally.inject(this, WelcomeActivity.class);

    }
}
