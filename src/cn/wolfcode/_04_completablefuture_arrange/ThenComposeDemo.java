package cn.wolfcode._04_completablefuture_arrange;

import cn.wolfcode._03_completablefuture_callback.ThenAcceptDemo;
import cn.wolfcode.utils.CommonUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenComposeDemo {

    public static CompletableFuture<String> readFileFuture(String fileName) {
        return CompletableFuture.supplyAsync(() -> {
            String filterWordsContent = CommonUtils.readFile(fileName);
            return filterWordsContent;
        });
    }

    public static CompletableFuture<String[]> splitFuture(String context) {
        return CompletableFuture.supplyAsync(() -> {
            String[] filterWords = context.split(",");
            return filterWords;
        });
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 编排2个依赖关系的异步任务 thenCompose()

        // 使用thenApply
        /*CompletableFuture<CompletableFuture<String[]>> future = readFileFuture("filter_words.txt").thenApply(content -> {
            return splitFuture(content);
        });*/

        // 使用thenCompose
        CompletableFuture<String[]> future = readFileFuture("filter_words.txt").thenCompose(content -> {
            return splitFuture(content);
        });

        String[] filerWords = future.get();
        CommonUtils.printThreadLog("filerWords = " + Arrays.toString(filerWords));

        /**
         * thenApply(Function<T,R>)
         * 重心在于对上一步异步任务的结果T进行应用转换，经Function回调转换后的结果R是一个简单的值
         *
         * thenCompose( Function<T,  CompletableFuture<R> > )
         * 重心在于对上一步异步任务的结果T进行应用，经Function回调转换后的结果是一个CompletableFuture对象
         * 结论:
         * 编排两个依赖关系的异步任务( CompletableFuture 对象 )  ,请使用 thenCompose() 方法
         */

    }
}
