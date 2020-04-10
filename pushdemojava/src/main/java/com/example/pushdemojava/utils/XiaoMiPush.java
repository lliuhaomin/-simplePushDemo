package com.example.pushdemojava.utils;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 *  
 *  @ClassName：SendPushUtil
 *  @Description: 小米推送
 *  @Author: liuhm
 *  @Date: 2020/4/8 17:00
 */
@Component
public class XiaoMiPush {
    @Autowired
    XiaoMiConfig xiaoMiConfig;

    private static final String HYBRID_PATH = "path";

    public static void main(String[] args) throws Exception {
        // 发送通知栏消息
//        xiaoMiPush.sendNotificationMessageByRegId(1,"测试","c测试测试","/Detail",null);
//        xiaoMiPush.sendNotificationMessageToAlias(1,"测试","c测试测试","/Detail",null);

    }

    /**
     * 发送通知栏消息 id
     */
    public void sendNotificationMessageByRegId(Integer id,String title,String description,String path,List<String> reGidList)  {
//        List<String> list = new ArrayList<>();
//        list.add(REGID);

        SetNotificationMessage setNotificationMessage = new SetNotificationMessage(id, title, description, path).invoke();
        Sender sender = setNotificationMessage.getSender();
        Message message = setNotificationMessage.getMessage();
        Result result = null;
        try {
            result = sender.sendHybridMessageByRegId(message, reGidList, 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("发送通知栏消息 id"+result);
    }
    /**
     * 发送通知栏消息 别名  personidList传入aliaList
     */
    public void sendNotificationMessageToAlias(Integer id,String title,String description,String path,List<String> aliaList)  {

        SetNotificationMessage setNotificationMessage = new SetNotificationMessage(id, title, description, path).invoke();
        Sender sender = setNotificationMessage.getSender();
        Message message = setNotificationMessage.getMessage();
        Result result = null;
        try {
            result = sender.sendToAlias(message, aliaList, 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("发送通知栏消息 别名  personidList传入aliaList"+result);
    }
    /**
     * 发送通知栏消息 别名  personidList传入aliaList
     */
    public void sendNotificationMessageToAlias(Integer id,String title,String description,String path,String alia)  {

        SetNotificationMessage setNotificationMessage = new SetNotificationMessage(id, title, description, path).invoke();
        Sender sender = setNotificationMessage.getSender();
        Message message = setNotificationMessage.getMessage();
        Result result = null;
        try {
            result = sender.sendToAlias(message, alia, 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("发送通知栏消息 别名  personidList传入aliaList"+result);
    }

    private class SetNotificationMessage {
        private Integer id;
        private String title;
        private String description;
        private String path;
        private Sender sender;
        private Message message;

        public SetNotificationMessage(Integer id, String title, String description, String path) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.path = path;
        }

        public Sender getSender() {
            return sender;
        }

        public Message getMessage() {
            return message;
        }

        public SetNotificationMessage invoke() {
            sender = new Sender(xiaoMiConfig.getAppSecret());
            message = new Message.Builder()
                    .restrictedPackageName(xiaoMiConfig.getPackageName())
                    .passThrough(0)
                    .notifyType(2) // 通知栏消息通知类型 //必填，通知的效果类型（1-使用默认提示音提示；2-使用默认震动提示；4-使用默认led灯光提示；-1 -以上三种效果都有；0-以上三种效果都无）
                    .notifyId(id) // 通知栏消息的 notifyId，相同时消息内容会被替换
                    .title(title) // 通知栏消息的 title
                    .description(description) // 通知栏消息的 desc
                    .extra(HYBRID_PATH, path) // 通知栏消息的 desc
                    .extra(Constants.EXTRA_PARAM_NOTIFY_FOREGROUND, "1") // 1 开启、0 关闭app在前台时的通知弹出
//                    .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_ACTIVITY)
//                    .extra(Constants.EXTRA_PARAM_INTENT_URI, "intent:#Intent;component=com.evget.bjgdjs/.MainActivity;end")
                    // 打开网页
//              .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_WEB)
//              .extra(Constants.EXTRA_PARAM_WEB_URI, "http://www.xiaomi.com")
                    .build();
            return this;
        }
    }
}
