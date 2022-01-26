package com.zhaipz.study.datastructure.controller;

import com.zhaipz.study.datastructure.service.userinfo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaipz
 * @ClassName: ControllerTest
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2022/1/10 10:57
 */
@RestController
@Slf4j
public class ControllerTest {

    @Autowired
    UserInfo userInfo;

    @PostMapping(value = "/test")
    public String test(){
        userInfo.insertUser();
        return "success";
    }
}
