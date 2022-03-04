package com.zhaipz.study.datastructure.service.juc.testMain;

import com.zhaipz.study.datastructure.service.juc.testMain.DeadLock.DeadLcok;
import com.zhaipz.study.datastructure.service.juc.testMain.DeadLock.DeadLockThreadO;
import com.zhaipz.study.datastructure.service.juc.testMain.DeadLock.DeadLockThreadT;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zhaipz
 * @ClassName: TestMain
 * @Description: JUC测试类
 * @date 2021/8/26 17:39
 */
@Slf4j
public class TestMain {
    public static void main(String[] args) {
        blockQueueTest();
    }

    public static void deadTest(){
        //死锁测试
        DeadLcok deadLcok = new DeadLcok();
        DeadLockThreadO deadLockThreadO = new DeadLockThreadO(deadLcok);
        DeadLockThreadT deadLockThreadT = new DeadLockThreadT(deadLcok);
        Thread thread1 = new Thread(deadLockThreadO,"deadLockThreadO");
        Thread thread2 = new Thread(deadLockThreadT,"deadLockThreadT");
        thread1.start();
        thread2.start();
    }

    public static void saleTest(){
        Ticket ticket = new Ticket(30);
        new Thread(() -> {
            for(int i = 0;i< 100;i++){
                ticket.saleTicket();
            }
        },"A").start();
        new Thread(()->{
            for(int i = 0;i< 100;i++){
                ticket.saleTicket();
            }
        },"B").start();
    }

    public static void blockQueueTest(){
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        new Thread(() -> {
            for(int i = 1;i< 21;i++){
                try {
                    log.info("放入队列 元素：" + i);
                    myBlockingQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for(int i = 1;i< 21;i++){
                try {
                    Integer x = myBlockingQueue.take();
                    log.info("取出队列 元素：" + x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }

}
