package com.example.pushdemojava.utils;


import java.util.List;

/**
 *  
 *  @ClassName：SennPushUtil
 *  @Description: 推送工具类
 *  @Author: liuhm
 *  @Date: 2020/4/9 11:11
 */
public class SendPushUtil {
    /** 
      * @description: 通过别名发送 
      * @params:  
      * @return:  
      * @author: liuhm
      * @Date: 11:13 2020/4/9 
      */

    public static void sendNotificationMessageToAlias(Integer id, String title, String description, String path, List<String> aliaList)  {
        XiaoMiPush xiaoMiPush= SpringContextUtil.getBean(XiaoMiPush.class);
        xiaoMiPush.sendNotificationMessageToAlias(id,title,  description,  path,  aliaList);
    }
    /** 
      * @description: 通过别名发送 
      * @params:  
      * @return:  
      * @author: liuhm
      * @Date: 11:13 2020/4/9 
      */

    public static void sendNotificationMessageToAlias(Integer id, String title, String description, String path, String alia)  {
        XiaoMiPush xiaoMiPush= SpringContextUtil.getBean(XiaoMiPush.class);
        xiaoMiPush.sendNotificationMessageToAlias(id,title,  description,  path,  alia);
    }

    /** 
      * @description: 通过id发送 
      * @params:  
      * @return:  
      * @author: liuhm
      * @Date: 11:13 2020/4/9 
      */
    public static void sendNotificationMessageByRegId(Integer id, String title, String description, String path, List<String> reGidList)  {
        XiaoMiPush xiaoMiPush= SpringContextUtil.getBean(XiaoMiPush.class);
        xiaoMiPush.sendNotificationMessageByRegId(id,title,  description,  path,  reGidList);
    }
}
