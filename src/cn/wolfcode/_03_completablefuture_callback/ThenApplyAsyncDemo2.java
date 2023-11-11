package cn.wolfcode._03_completablefuture_callback;

import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyAsyncDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 回顾:异步读取 filter_words.txt 文件中的内容，读取完成后，转换成敏感词数组，主线程获取异步任务结果打印输出这个数组
        CommonUtils.printThreadLog("main start");

        CompletableFuture<String[]> filterWordFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtils.printThreadLog("读取filter_words.txt文件");
            String filterWordsContent = CommonUtils.readFile("filter_words.txt");
            return filterWordsContent;
        }).thenApplyAsync(content -> {
            CommonUtils.printThreadLog("把文件内容转换成敏感词数组");
            String[] filterWords = content.split(",");
            return filterWords;
        });

        CommonUtils.printThreadLog("main continue");
        String[] filterWords = filterWordFuture.get();
        CommonUtils.printThreadLog("filterWords = " + Arrays.toString(filterWords));

        CommonUtils.printThreadLog("main end");
    }
}
