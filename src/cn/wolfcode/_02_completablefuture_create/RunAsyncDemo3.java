package cn.wolfcode._02_completablefuture_create;

import cn.wolfcode.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;

public class RunAsyncDemo3 {
    public static void main(String[] args) {
        // 需求：使用CompletableFuture开启异步任务读取 news.txt 文件中的新闻稿，并打印输出。
        CommonUtils.printThreadLog("main start");

        CompletableFuture.runAsync(()->{
            CommonUtils.printThreadLog("读取文件");
            String content = CommonUtils.readFile("news.txt");
            System.out.println(content);
        });

        CommonUtils.printThreadLog("here not blocked main continue");
        CommonUtils.sleepSecond(4);
        CommonUtils.printThreadLog("main end");

        /**
         * 疑问: 异步任务是并发执行还是并行执行？
         * 如果是单核CPU，那么异常任务之间就是并发执行；如果是多核CPU(多CPU) 异步任务就是并行执行
         * 重点(敲黑板)
         * 作为开发者，我们只需要清楚如何开启异步任务，CPU硬件会把异步任务合理的分配给CPU上的核运行。
         */
    }
}
