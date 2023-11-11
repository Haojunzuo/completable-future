package cn.wolfcode._03_completablefuture_callback;

import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 需求：异步读取 filter_words.txt 文件中的内容，读取完成后，把内容转换成数组( 敏感词数组 )，异步任务返回敏感词数组
        CommonUtils.printThreadLog("main start");

        CompletableFuture<String[]> filterWordsFuture = CompletableFuture.supplyAsync(() -> {
            String filterWordsContent = CommonUtils.readFile("filter_words.txt");
            return filterWordsContent;
        }).thenApply(content -> {
            String[] filterWords = content.split(",");
            return filterWords;
        });

        CommonUtils.printThreadLog("main continue");
        String[] filterWords = filterWordsFuture.get();
        CommonUtils.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        CommonUtils.printThreadLog("main end");

    }
}
