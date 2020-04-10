package com.example.pushdemojava.controller;

import com.example.pushdemojava.utils.SendPushUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  
 *  @ClassName：DemoController
 *  @Description: TODO
 *  @Author: liuhm
 *  @Date: 2020/4/10 14:24
 */
@RestController
public class DemoController {
    @RequestMapping("/test/{alia}")
    public String test(@PathVariable(value = "alia") String alia){
        SendPushUtil.sendNotificationMessageToAlias(1,"测试","c测试测试","/test1",alia);
        return "success";
    }
}
