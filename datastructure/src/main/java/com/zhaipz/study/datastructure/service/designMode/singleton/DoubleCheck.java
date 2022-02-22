package com.zhaipz.study.datastructure.service.designMode.singleton;

/**
 * @author zhaipz
 * @ClassName: DoubleCheck  单例模式核心：构造方法私有化、静态变量私有化
 * @Description: 双检锁单例   加锁双重校验
 * @date 2022/2/15 9:03
 */
public class DoubleCheck {
    private static volatile DoubleCheck instance;

    private DoubleCheck(){}

    public DoubleCheck getInstance(){
        if(instance == null){
            synchronized(DoubleCheck.class){
                if(instance == null){
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }
}
