package com.zhaipz.study.datastructure.service.juc.testMain;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

@Slf4j
public class TestVirtualThread {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("jdk.virtualThreadScheduler.parallelism", "4");
//        System.out.println(Runtime.getRuntime().availableProcessors());

        testVirtualThread();
//        testVirtualThread2();

        //1000
//        testVirtualThreadExecutor3();
//        testFixedThreadExecutor5();

        //10000
//        testVirtualThreadExecutor2();
//        testFixedThreadExecutor4();
    }

    private static void testVirtualThread() throws InterruptedException {

        log.info("thread started--------{}", Thread.currentThread());
        log.info("thread started--------{}", Thread.currentThread().isVirtual());
        log.info("thread started--------{}", Thread.currentThread().isDaemon());

//        Thread.ofVirtual().name("virtual thread").start(()-> log.info("virtual thread started--------{}",Thread.currentThread()));
        Thread thread = Thread.startVirtualThread(() -> {
            log.info("virtual thread test:{}", Thread.currentThread().isDaemon());
            log.info("virtual thread test:{}", Thread.currentThread().isVirtual());
            log.info("virtual thread started--------{}", Thread.currentThread());
        });
//        thread.setDaemon(false);
        Thread.sleep(500);
//        thread.setName("virtual thread");
//        thread.join();

    }

    private static void testVirtualThread2() throws InterruptedException {

//        log.info("thread started--------{}",Thread.currentThread());
//        log.info("thread started--------{}",Thread.currentThread().isVirtual());
//        log.info("thread started--------{}",Thread.currentThread().isDaemon());

        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            Thread.startVirtualThread(() -> {
                log.info("virtual thread:{}, number:{}", Thread.currentThread(), finalI);
            });
        }

        Thread.sleep(1000);


    }

//    private static void testContinuation(){
//        ContinuationScope scope = new ContinuationScope("scope");
//        Continuation continuation = new Continuation(scope, () -> {
//            System.out.println("Running before yield");
//            Continuation.yield(scope);
//            System.out.println("Running after yield");
//        });
//        System.out.println("First run");
//        // 第一次执行Continuation.run
//        continuation.run();
//        System.out.println("Second run");
//        // 第二次执行Continuation.run
//        continuation.run();
//        System.out.println("Done");
//    }

    private static void testVirtualThreadExecutor() {
        var virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.range(0, 1000).forEach(i -> {
            virtualThreadPerTaskExecutor.submit(() -> {
                log.info("virtual thread started{}--------{}", Thread.currentThread(), i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        stopWatch.stop();

        log.info("virtual thread cost:{}", stopWatch.getTotalTimeMillis());
    }

    private static void testVirtualThreadExecutor2() {
        ExecutorService virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();
        CompletableFuture<Void>[] futures = new CompletableFuture[10000];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long s = System.currentTimeMillis();

        // 提交1000个任务，并创建对应的CompletableFuture对象
        IntStream.range(0, 10000).forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                log.info("virtual thread started{}-------{}", Thread.currentThread(), i);
//                try {
//                    log.info("virtual thread task--------{}", Thread.currentThread().threadId());
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }, virtualThreadPerTaskExecutor);
            futures[i] = future;
        });

        // 使用CompletableFuture.allOf等待所有任务执行完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        allOf.join(); // 等待所有任务执行完成

        stopWatch.stop();
        long end = System.currentTimeMillis();
        log.info("All tasks completed. Total time: {} ms", stopWatch.getTotalTimeMillis());

        virtualThreadPerTaskExecutor.shutdown();
        try {
            virtualThreadPerTaskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Executor service shutdown interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private static void testVirtualThreadExecutor3() {
        ExecutorService virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();
        CompletableFuture<Void>[] futures = new CompletableFuture[1000];
        long startTime = System.currentTimeMillis();

        // 提交1000个任务，并创建对应的CompletableFuture对象
        IntStream.range(0, 1000).forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                log.info("virtual thread started{}--------{}", Thread.currentThread(), i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, virtualThreadPerTaskExecutor);
            futures[i] = future;
        });

        // 使用CompletableFuture.allOf等待所有任务执行完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        allOf.join(); // 等待所有任务执行完成

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("All tasks completed. Total time: {} ms", totalTime);

        virtualThreadPerTaskExecutor.shutdown();
        try {
            virtualThreadPerTaskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Executor service shutdown interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private static void testFixedThreadExecutor4() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(200);
        CompletableFuture<Void>[] futures = new CompletableFuture[10000];
        long startTime = System.currentTimeMillis();

        // 提交1000个任务，并创建对应的CompletableFuture对象
        IntStream.range(0, 10000).forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                log.info("virtual thread started{}--------{}", Thread.currentThread(), i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }, fixedThreadPool);
            futures[i] = future;
        });

        // 使用CompletableFuture.allOf等待所有任务执行完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        allOf.join(); // 等待所有任务执行完成

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("All tasks completed. Total time: {} ms", totalTime);

        fixedThreadPool.shutdown();
        try {
            fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Executor service shutdown interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private static void testFixedThreadExecutor5() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(200);
        CompletableFuture<Void>[] futures = new CompletableFuture[1000];
        long startTime = System.currentTimeMillis();

        // 提交1000个任务，并创建对应的CompletableFuture对象
        IntStream.range(0, 1000).forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                log.info("virtual thread started{}--------{}", Thread.currentThread(), i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, fixedThreadPool);
            futures[i] = future;
        });

        // 使用CompletableFuture.allOf等待所有任务执行完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        allOf.join(); // 等待所有任务执行完成

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("All tasks completed. Total time: {} ms", totalTime);

        fixedThreadPool.shutdown();
        try {
            fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Executor service shutdown interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

}
