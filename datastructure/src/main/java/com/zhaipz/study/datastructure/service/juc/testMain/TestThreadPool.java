package com.zhaipz.study.datastructure.service.juc.testMain;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhaipz
 * @ClassName: TestThreadPool
 * @Description: 线程池的基本使用
 * @date 2022/1/7 15:21
 */
@Slf4j
public class TestThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 1;i < 100;i++){
            int finalI = i;
            executorService.execute(new Thread(()->{
                log.info("ThreadName: {}, param: {} res: {} ",Thread.currentThread().getName(),finalI,finalI);
            },"----Thread"));
        }

        executorService.shutdown();

        ExecutorService executorService1 = new ThreadPoolExecutor(5,5,30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

    }
}
