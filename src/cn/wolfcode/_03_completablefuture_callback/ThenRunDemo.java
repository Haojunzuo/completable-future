package cn.wolfcode._03_completablefuture_callback;

import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ThenRunDemo {
    public static void main(String[] args) {
        // 演示案例：我们仅仅想知道敏感词汇的文件是否读取完成
        CommonUtils.printThreadLog("main start");

        CompletableFuture.supplyAsync(()->{
            CommonUtils.printThreadLog("开始读取filter_words.txt文件");
            String filterWordsContent = CommonUtils.readFile("filter_words.txt");
            return filterWordsContent;
        }).thenRun(()->{
            CommonUtils.printThreadLog("读取filter_words.txt文件完成");
        });

        CommonUtils.printThreadLog("main continue");
        CommonUtils.sleepSecond(4);
        CommonUtils.printThreadLog("main end");

        /**
         * thenRun(Runnable action);
         * 当异步任务完成后，只想得到一个完成的通知，不使用上一步异步任务的结果，就可以thenRun
         * 通过会把它用在链式操作的末端
         */


    }
}
