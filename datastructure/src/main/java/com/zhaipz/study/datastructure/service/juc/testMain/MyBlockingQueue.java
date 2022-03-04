package com.zhaipz.study.datastructure.service.juc.testMain;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaipz
 * @ClassName: MyBlockingQueue
 * @Description: 实现简单阻塞队列
 * @date 2022/3/4 14:58
 */
@Slf4j
public class MyBlockingQueue<E> {
    private final Object[] items;
    private int takeIndex;
    private int putIndex;
    private int count;

    final private ReentrantLock lock = new ReentrantLock();

    private Condition notEmpty;
    private Condition notFull;

    MyBlockingQueue(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Object[capacity];
        this.count = 0;
        this.takeIndex = 0;
        this.putIndex = 0;
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    private void enqueue(E e){
        final Object[] items = this.items;
        items[putIndex] = e;
        if(++putIndex == items.length){
            putIndex = 0;
        }
        count++;
        notEmpty.signal();
    }

    private E dequeue(){
        final Object[] items = this.items;
        E e = (E) items[takeIndex];
        items[takeIndex] = null;
        if(++takeIndex == items.length){
            takeIndex = 0;
        }
        count--;
        notFull.signal();
        return e;
    }

    public void put(E e) throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
           while(count == items.length){
               log.info("队列已满，等待取出操作");
               notFull.await();
           }
           enqueue(e);
        }finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while(count == 0){
                log.info("队列为空，等待插入操作");
                notEmpty.await();
            }
            return dequeue();
        }finally {
            lock.unlock();
        }
    }

}
