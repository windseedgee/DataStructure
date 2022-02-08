package com.zhaipz.study.datastructure.service.juc.testMain.DeadLock;

/**
 * @author zhaipz
 * @ClassName: DeadLockThreadO
 * @Description: 死锁测试类
 * @date 2022/1/21 15:11
 */
public class DeadLockThreadO implements Runnable{

    DeadLcok deadLcok;

    public DeadLockThreadO(DeadLcok deadLcok){
        this.deadLcok = deadLcok;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //deadLcok.lock1Andlock2();
        try {
            deadLcok.leftAndRight();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
