package cn.wolfcode._04_completablefuture_arrange;

import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThenComposeDemo2 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // thenCompose()
        // 需求：异步读取 filter_words.txt 文件中的内容，读取完成后，转换成敏感词数组待用。
        CommonUtils.printThreadLog("main start");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String[]> filterWordsFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtils.printThreadLog("读取filter_words文件");
            String filterWordsContent = CommonUtils.readFile("filter_words.txt");
            return filterWordsContent;
        }).thenComposeAsync(content -> CompletableFuture.supplyAsync(() -> {
            CommonUtils.printThreadLog("把内容转换成敏感词数组");
            String[] filterWords = content.split(",");
            return filterWords;
        },executor));

        CommonUtils.printThreadLog("main continue");
        String[] filterWords = filterWordsFuture.get();
        CommonUtils.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        // 关闭业务线程池
        executor.shutdown();
        CommonUtils.printThreadLog("main end");

    }
}
