package cn.wolfcode._02_completablefuture_create;

import cn.wolfcode.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;

public class RunAsyncDemo2 {
    public static void main(String[] args) {

        // runAsync 创建异步任务
        CommonUtils.printThreadLog("main start");

        // 使用lambda表达式
        CompletableFuture.runAsync(() -> {
            CommonUtils.printThreadLog("读取文件开始");
            CommonUtils.sleepSecond(3);
            CommonUtils.printThreadLog("读取文件结束");
        });

        CommonUtils.printThreadLog("here are not blocked,main continue");
        CommonUtils.sleepSecond(4); //  此处休眠为的是等待CompletableFuture背后的线程池执行完成。
        CommonUtils.printThreadLog("main end");


    }
}
