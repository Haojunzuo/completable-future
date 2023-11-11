package cn.wolfcode._03_completablefuture_callback;

import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class ThenApplyDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 需求：异步读取 filter_words.txt 文件中的内容，读取完成后，把内容转换成数组( 敏感词数组 )，异步任务返回敏感词数组
        CommonUtils.printThreadLog("main start");

        CompletableFuture<String> readFileFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtils.printThreadLog("读取filter_words文件");
            String filterWordContent = CommonUtils.readFile("filter_words.txt");
            return filterWordContent;
        });

        CompletableFuture<String[]> filterWordsFuture = readFileFuture.thenApply(content -> {
            CommonUtils.printThreadLog("把文件内容转换成敏感词数组");
            String[] filterWords = content.split(",");
            return filterWords;
        });

        CommonUtils.printThreadLog("main continue");
        String[] filterWords = filterWordsFuture.get();
        CommonUtils.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        CommonUtils.printThreadLog("main end");

        /**
         * 总结
         * thenApply(Function<T,R>) 可以对异步任务的结果进一步应用Function转换
         * 转换后的结果可以在主线程获取，也可以进行下一步的转换。
         */

    }
}
