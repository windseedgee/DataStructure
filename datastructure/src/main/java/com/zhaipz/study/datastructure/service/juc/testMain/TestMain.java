package com.zhaipz.study.datastructure.service.juc.testMain;

import com.zhaipz.study.datastructure.service.juc.testMain.DeadLock.DeadLcok;
import com.zhaipz.study.datastructure.service.juc.testMain.DeadLock.DeadLockThreadO;
import com.zhaipz.study.datastructure.service.juc.testMain.DeadLock.DeadLockThreadT;

/**
 * @author zhaipz
 * @ClassName: TestMain
 * @Description: JUC测试类
 * @date 2021/8/26 17:39
 */
public class TestMain {
    public static void main(String[] args) {
//        Ticket ticket = new Ticket(30);
//        new Thread(() -> {
//            for(int i = 0;i< 100;i++){
//                ticket.saleTicket();
//            }
//        },"A").start();
//        new Thread(()->{
//            for(int i = 0;i< 100;i++){
//                ticket.saleTicket();
//            }
//        },"B").start();
        DeadLcok deadLcok = new DeadLcok();
        DeadLockThreadO deadLockThreadO = new DeadLockThreadO(deadLcok);
        DeadLockThreadT deadLockThreadT = new DeadLockThreadT(deadLcok);
        Thread thread1 = new Thread(deadLockThreadO,"deadLockThreadO");
        Thread thread2 = new Thread(deadLockThreadT,"deadLockThreadT");
        thread1.start();
        thread2.start();

    }
}
