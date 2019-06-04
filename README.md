



### SplashGlobally

[![Download](https://api.bintray.com/packages/quinnhuang/widget/SplashGlobally/images/download.svg?version=0.0.1) ](https://bintray.com/quinnhuang/widget/SplashGlobally/0.0.1/link) 





<img src="./img/simple_splash.gif" width="200px">



#### 一行代码实现如上注入开屏界面,开发者只要专注于开屏界面UI、数据逻辑即可。

### 前言

去年使用反射实现过一种[一行代码实现开屏广告](http://xwcc.fun/2018/05/09/Android-Hook-%E4%B8%80%E8%A1%8C%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0%E5%BC%80%E5%B1%8F%E5%B9%BF%E5%91%8A/)。由于是基于反射，如果各个系统版本Api、变量名、各机型ROM、有更改还得适配比较麻烦，存在不稳定性。因此再介绍一种基于原生API方式。

`InjectSplashGlobally`是一个全局注入`SplashActivity`界面框架，**一行代码使用**，内部使用原生API，各个版本之间无差异，稳定高效。
其原理是监听app生命周期，前后台切换，通过`application.registerActivityLifecycleCallbacks`即可。
通过监听其亮屏熄屏广播`startActivity(xxActivity.class)`跳转到`xxActivity.class`

### 支持

- 开屏界面
- 再进程存活的情况下从后台进入前台注入开屏界面
- 再进程存活的情况下锁屏再亮屏进入开屏界面



### Download

```
compile 'com.xwdz:SplashGlobally:$DownloadLastVersion'
```



### 使用

在应用程序的Application#onCreate初始化即可。

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 第二个参数指定为你自定义的开屏界面xxx.class
        SplashGlobally.inject(this, WelcomeActivity.class);
    }
}

```







 