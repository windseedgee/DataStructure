package com.zhaipz.study.datastructure.service.juc.testMain;

/**
 * @author zhaipz
 * @ClassName: TestMain
 * @Description: JUC测试类
 * @date 2021/8/26 17:39
 */
public class TestMain {
    public static void main(String[] args) {
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
}
