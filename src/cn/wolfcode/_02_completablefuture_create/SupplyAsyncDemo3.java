package cn.wolfcode._02_completablefuture_create;

import cn.wolfcode.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 需求：指定线程池，开启异步任务读取 news.txt 文件中的新闻稿，并返回读取文件中内容
        CommonUtils.printThreadLog("main start");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        // 使用lambda表达式
        CompletableFuture<String> newsFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtils.printThreadLog("异步读取文件开始");
            String content = CommonUtils.readFile("news.txt");
            return content;
        },executor);

        CommonUtils.printThreadLog("here not blocked main continue");
        String news = newsFuture.get();
        System.out.println("news = " + news);
        // 关闭线程池
        executor.shutdown();
        CommonUtils.printThreadLog("main end");
    }
}
