package com.zhaipz.study.datastructure.service.juc.testMain.DeadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaipz
 * @ClassName: DeadLcok
 * @Description: 死锁情况  两个在占有锁资源情况下 等待对方释放另一个锁
 * @date 2022/1/21 15:12
 */
public class DeadLcok {

    //ReentrantLock  死锁
    ReentrantLock lock1;
    ReentrantLock lock2;

    public DeadLcok(){
        lock1 = new ReentrantLock();
        lock2 = new ReentrantLock();
    }

    /*
        查看死锁情况
        1、jps获得当前Java虚拟机进程的pid
        2、jstack打印堆栈。jstack打印内容的最后其实已经报告发现了一个死锁
     */
    public void lock1Andlock2(){
        try {
            lock1.lock();
            System.out.println("lock1---");
            //获取一个锁先阻塞一段时间，防止太快直接获得两个锁
            Thread.sleep(20000);
            try {
                lock2.lock();
                System.out.println("lock2---");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock2.lock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock1.unlock();
        }
    }

    public void lock2Andlock1(){
        try {
            lock2.lock();
            System.out.println("lock2---");
            Thread.sleep(20000);
            try {
                lock1.lock();
                System.out.println("lock1---");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock1.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock2.unlock();
        }
    }

    //synchronized  死锁
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftAndRight() throws InterruptedException {

            synchronized(left){
                System.out.println("left---");
                //获取一个锁先阻塞一段时间，防止太快直接获得两个锁
                Thread.sleep(20000);
                synchronized (right){
                    System.out.println("right---");
                }
            }
    }

    public void rightAndLeft() throws InterruptedException {

        synchronized(right){
            System.out.println("right---");
            //获取一个锁先阻塞一段时间，防止太快直接获得两个锁
            Thread.sleep(20000);
            synchronized (left){
                System.out.println("left---");
            }
        }
    }


}
