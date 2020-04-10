
## 安装环境
如果采用cordova打包，需安装Android环境，可参考 http://www.ctrlands.com/2019/09/02/Cordova+Vue%E5%9F%BA%E7%A1%80%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/
安装安装Android Studio问题解决：http://www.ctrlands.com/2019/07/16/React-Native%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/
Android Studio环境安装：https://reactnative.cn/docs/getting-started/    中的Android 开发环境

## 使用框架
前端 vue 
后端 springboot 小米推送
使用插件 cordova-plugin-mipush
打包 cordova

推送插件
https://github.com/wenin819/cordova-plugin-mipush/blob/master/doc/api.md#%E6%B3%A8%E5%86%8C%E6%88%90%E5%8A%9F%E6%97%B6%E8%8E%B7%E5%8F%96%E7%9A%84%E5%86%85%E5%AE%B9


## 初始化vue3的项目
vue create vue3demo

安装 vue-router
npm install vue-router -d
做个简单的可以跳转的界面
做vue打包之前的配置 不然打包后是白板
https://blog.csdn.net/qq_25000935/article/details/84103271





大多数情况下是不需要的, 如果出现白屏, 还是多检查检查代码吧






## 初始化vue2的项目
vue create vue3demo
## vue文件修改
index.html 中必须添加
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="msapplication-tap-highlight" content="no">

index.html 添加
<meta http-equiv="Content-Security-Policy" content="default-src 'self' data: gap: https://ssl.gstatic.com 'unsafe-eval'; style-src 'self' 'unsafe-inline'; media-src *; img-src 'self' data: content:;">
的时候可能导致页面样式改变，如果改变则不加，否则还是建议加上。这段主要是防止跨站脚本攻击。
 <!-- 非cordova打包时，需将下一行代码删掉, 使用cordova打包时，需将下一行代码取消注释 -->
    <script src="./cordova.js" type="text/javascript" charset="utf-8"></script>
部分项目出现非代码问题白屏情况,可以考虑修改main.js
document.addEventListener(
  "deviceready",
  function() {
    window.Vue = new Vue({
      router,
      render: h => h(App)
    }).$mount("#app");
  },
  false
);

const router = new VueRouter({
// cordova 打包需要注释
//   mode: "history",
  routes: routers,
});


## Cordvoa+Vue基础环境搭建
安装Cordova
npm i -g cordova

初始化Cordova项目
cordova create projectFileName packageName appName
cd cordova
设置平台(已Android为例)
 cordova platform add android

## 小米官方注册一个账号 
[小米推送](https://dev.mi.com/console/doc/detail?pId=230)
得到
主包名  com.demoliu 
AppID  2882303761518360815
AppKey   5141836012815
AppSecret    looyvxUgpnp0sgrnlbApsg==


## 安装插件
消息通知栏
https://github.com/wenin819/cordova-plugin-mipush
  cordova plugin add C:\Users\liuhm\Downloads\cordova-plugin-mipush-master  --variable MI_PUSH_APP_KEY=5141836012815 --variable MI_PUSH_APP_ID=2882303761518360815 --variable MI_PUSH_APP_IOS_ID=your_mipush_appid --variable MI_PUSH_APP_IOS_KEY=your_mipush_appkey

悬浮窗
cordova-plugin-x-toast
https://www.npmjs.com/package/cordova-plugin-x-toast/v/2.7.2




## 后端  
引入 commons-codec-1.12.jar json-simple-1.1.jar  MiPush_SDK_Server_Http2_1.0.8-SNAPSHOT.jar
放入 libs 目录

maven

```bash
  <!--推送-->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.12</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/src/main/libs/commons-codec-1.12.jar</systemPath>
        </dependency>
        <dependency>
            <groupId>com.xiaomi.miliao</groupId>
            <artifactId>xmpush-server-api-http2</artifactId>
            <version>1.0.8-SNAPSHOT</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/src/main/libs/MiPush_SDK_Server_Http2_1.0.8-SNAPSHOT.jar</systemPath>
        </dependency>
        <dependency>
            <groupId>json-simple</groupId>
            <artifactId>json-simplec</artifactId>
            <version>1.1</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/src/main/libs/json-simple-1.1.jar</systemPath>
        </dependency>
```

 

```bash
 private static final String APP_SECRET = "looyvxUgpnp0sgrnlbApsg==";
    private static final String PACKAGE_NAME = "com.demoliu";
    private static final String alias = "113";
    private static final String HYBRID_PATH = "path";

    /**
     * 发送通知栏消息
     */
    public static void testSendNotificationMessage() throws Exception {
        List<String> list = new ArrayList<>();
        list.add(alias);

       sender = new Sender(xiaoMiConfig.getAppSecret());
                  message = new Message.Builder()
                          .restrictedPackageName(xiaoMiConfig.getPackageName())
                          .passThrough(0)
                          .notifyType(2) // 通知栏消息通知类型 //必填，通知的效果类型（1-使用默认提示音提示；2-使用默认震动提示；4-使用默认led灯光提示；-1 -以上三种效果都有；0-以上三种效果都无）
                          .notifyId(id) // 通知栏消息的 notifyId，相同时消息内容会被替换
                          .title(title) // 通知栏消息的 title
                          .description(description) // 通知栏消息的 desc
                          .extra(HYBRID_PATH, path) // 跳转vue里面的页面
                          .extra(Constants.EXTRA_PARAM_NOTIFY_FOREGROUND, "1") // 1 开启、0 关闭app在前台时的通知弹出
      //                    .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_ACTIVITY)
      //                    .extra(Constants.EXTRA_PARAM_INTENT_URI, "intent:#Intent;component=com.evget.bjgdjs/.MainActivity;end")
                          // 打开网页
      //              .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_WEB)
      //              .extra(Constants.EXTRA_PARAM_WEB_URI, "http://www.xiaomi.com")
                          .build();
        Result result = sender.sendToAlias(message, list, 0);
        System.out.println(result);
    }
 
```

   



## 调试 打开佛跳墙
https://www.globetechnews.com/cn/?a=ez
谷歌浏览器种输入chrome://inspect/#devices

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200410162819906.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTIwMzQy,size_16,color_FFFFFF,t_70)


[git 地址](https://gitee.com/liuhaomin/simplePushDemo.git)

欢迎指教






## cordova 项目中
cordova plugin add cordova-plugin-x-toast
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200410160632867.png)
## vue项目中引入 
引入Toast.js

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200410160822823.png)

index.html 中
  <!-- 消息悬浮窗 -->
    <script type="text/javascript" src="Toast.js"></script>
    ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200410161222174.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTIwMzQy,size_16,color_FFFFFF,t_70)
## vue项目中使用

```
 window.plugins.toast.showWithOptions(
      {
        message: message,
        duration: "2000", // which is 2000 ms. "long" is 4000. Or specify the nr of ms yourself.
        position: "top",
        addPixelsY: 0 // added a negative value to move it up a bit (default 0)
      },
      function(args) {
        console.log("toast args: ", args.event);
        //This will print 'hide'
      },
      function(error) {
        console.error("toast error: ", error);
      }
    );
```
## 打包使用
效果图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200410162642118.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTIwMzQy,size_16,color_FFFFFF,t_70)