package com.jy.modules.boot.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
 * Created by apple on 2019/5/13.
 */
public class ThreadPoolExecutorTest {
    //声明日志输出类
    private static Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);

    private static final String THREAD_NAMED_FORMAT ="ThreadPoolTest";
    /**
     * 线程测试
     */
    @Test
    public void testFixedThreadPool() {
        long startTime = System.currentTimeMillis();
        //定义线程工厂
        CustomThreadFactory threadFactory = new CustomThreadFactory(THREAD_NAMED_FORMAT);
        //声明线程池
        ExecutorService executorService = Executors.newFixedThreadPool(25,threadFactory);
        //定义集合大小为1000
        int totalCount = 1000;
        //定义计算器
        final CountDownLatch countDownLatch = new CountDownLatch(totalCount);
        for (int i = 1; i <= totalCount; i++) {
            final int task = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("开始运行的是第{}个任务:", task);
                    logger.info("{} is work,now loop to {} ", Thread.currentThread().getName(), task);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("线程中断异常", e);
        }
        long endTime = System.currentTimeMillis();
        logger.info("执行测试耗时:{}毫秒", endTime - startTime);
    }
}
