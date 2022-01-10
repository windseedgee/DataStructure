package com.zhaipz.study.datastructure.controller;

import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/test")
    public String test(){
        String str = "Hello World";
        log.info(str);
        return str;
    }
}
