package com.zhaipz.study.datastructure.service.juc.testMain;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaipz
 * @ClassName: TestThreadPool
 * @Description: 线程池的基本使用
 * @date 2022/1/7 15:21
 */
@Slf4j
public class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for(int i = 1;i < 100;i++){
            int finalI = i;
            fixedThreadPool.execute(new Thread(()-> log.info("ThreadName: {}, param: {} res: {} ",Thread.currentThread().getName(),finalI,finalI),"----Thread"));
        }

        fixedThreadPool.shutdown();

        ExecutorService executorService1 = new ThreadPoolExecutor(5,5,30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        TestThread testThread = new TestThread();
        Thread thread = new Thread(testThread);
        thread.isInterrupted();
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
