package com.zhaipz.study.datastructure.service.springBeanTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zhaipz
 * @ClassName: TestService1
 * @Description: 测试循环依赖问题  @Async 二级缓存生成的代理对象不一致/多例注入单例，单例又循环注入多例/@DependsOn指定加载顺序存在循环
 * @date 2022/3/17 8:45
 */
@Service
public class TestService1 {

    @Autowired
    TestService2 testService2;
    @Async
    public void test1(){
        System.out.println("...");
    }
}
