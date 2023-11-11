package cn.wolfcode._03_completablefuture_callback;

import cn.wolfcode.utils.CommonUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ThenAcceptDemo {
    public static void main(String[] args) {
        // 需求：异步读取 filter_words.txt 文件中的内容，读取完成后，转换成敏感词数组，然后打印敏感词数组

        CommonUtils.printThreadLog("main start");

        CompletableFuture.supplyAsync(() -> {
            CommonUtils.printThreadLog("读取filter_words.txt文件");
            String filterWordsContent = CommonUtils.readFile("filter_words.txt");
            return filterWordsContent;
        }).thenApply(content -> {
            CommonUtils.printThreadLog("把文件内容转换成敏感词数组");
            String[] filterWords = content.split(",");
            return filterWords;
        }).thenAccept(filterWords -> {
            CommonUtils.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        });

        CommonUtils.printThreadLog("main continue");
        CommonUtils.sleepSecond(4);
        CommonUtils.printThreadLog("main end");

        /**
         * 总结
         * thenAccept(Consumer<T> c) 可以对异步任务的结果进行消费使用
         * 返回返回一个不带结果的CompletableFuture对象
         */

    }
}
