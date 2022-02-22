package com.zhaipz.study.datastructure.service.designMode.singleton;

/**
 * @author zhaipz
 * @ClassName: SingletonMode  单例模式核心：构造方法私有化、静态变量私有化
 * @Description: 懒汉单例  线程不安全   在需要使用时才构建实例对象
 * @date 2022/2/15 8:59
 */
public class LazyMode {
    private static LazyMode instance;

    private LazyMode(){}

    public LazyMode getInstance(){
        if(instance == null){
            //有可能交出CPU使用权，造成线程不安全
            instance = new LazyMode();
        }
        return instance;
    }
}
