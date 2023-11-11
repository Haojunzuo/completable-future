package cn.wolfcode._03_completablefuture_callback;

import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyAsyncDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 回顾:异步读取 filter_words.txt 文件中的内容，读取完成后，转换成敏感词数组，主线程获取异步任务结果打印输出这个数组
        CommonUtils.printThreadLog("main start");

        CompletableFuture<String[]> filterWordFuture = CompletableFuture.supplyAsync(() -> {
            /*CommonUtils.printThreadLog("读取filter_words.txt文件");
            String filterWordsContent = CommonUtils.readFile("filter_words.txt");
            return filterWordsContent;*/

            return "尼玛,NB,tmd";
        }).thenApply(content -> {
            CommonUtils.printThreadLog("把文件内容转换成敏感词数组");
            String[] filterWords = content.split(",");
            return filterWords;
        });

        CommonUtils.printThreadLog("main continue");
        String[] filterWords = filterWordFuture.get();
        CommonUtils.printThreadLog("filterWords = " + Arrays.toString(filterWords));

        CommonUtils.printThreadLog("main end");

        /**
         * 总结
         * 一般而言，commonPool为了提高性能
         * thenApply中回调任务和supplyAsync中的异步任务使用的是同一个线程
         * 特殊情况:
         * 如果supplyAsync中的任务是立即返回结果( 不是耗时的任务 )，thenApply回调任务也会在主线程执行。
         */
    }
}
