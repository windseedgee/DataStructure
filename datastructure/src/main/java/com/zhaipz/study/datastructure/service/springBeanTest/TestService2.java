package com.zhaipz.study.datastructure.service.springBeanTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaipz
 * @ClassName: TestService1
 * @Description: 测试循环依赖问题
 * @date 2022/3/17 8:45
 */
@Service
public class TestService2 {

    @Autowired
    TestService1 testService1;

    public void test2(){
        System.out.println("...");
    }
}
