package cn.wolfcode._02_completablefuture_create;

import cn.wolfcode.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;

public class RunAsyncDemo {

    public static void main(String[] args) {

        // runAsync 创建异步任务
        CommonUtils.printThreadLog("main start");
        // 使用Runnable匿名内部类
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                CommonUtils.printThreadLog("读取文件开始");
                CommonUtils.sleepSecond(3);
                CommonUtils.printThreadLog("读取文件结束");
            }
        });

        CommonUtils.printThreadLog("here are not blocked,main continue");
        CommonUtils.sleepSecond(4); //  此处休眠为的是等待CompletableFuture背后的线程池执行完成。
        CommonUtils.printThreadLog("main end");

        /**
         * CompletableFuture 中的异步任务底层通过开启线程的方式完成的
         */
    }
}
