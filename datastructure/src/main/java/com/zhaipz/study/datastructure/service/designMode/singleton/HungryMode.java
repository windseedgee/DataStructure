package com.zhaipz.study.datastructure.service.designMode.singleton;

/**
 * @author zhaipz
 * @ClassName: HungryMode 单例模式核心：构造方法私有化、静态变量私有化
 * @Description: 饿汉单例  在类初始化时构建好实例对象
 * @date 2022/2/15 9:03
 */
public class HungryMode {
    private static HungryMode instance = new HungryMode();

    private HungryMode(){}

    public HungryMode getInstance(){
        return instance;
    }
}
