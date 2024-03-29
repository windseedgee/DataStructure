package com.zhaipz.study.datastructure.service.juc.testMain;

/**
 * @author zhaipz
 * @ClassName: TestThread
 * @Description:
 * @date 2022/1/21 10:11
 */
public class TestThread implements Runnable{
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
        System.out.println("ceshi");
    }
}
