package cn.wolfcode._02_completablefuture_create;

import cn.wolfcode.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class SupplyAsyncDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 需求：开启异步任务读取 news.txt 文件中的新闻稿，返回文件中内容并在主线程打印输出
        CommonUtils.printThreadLog("main start");

        CompletableFuture<String> newsFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                String content = CommonUtils.readFile("news.txt");
                return content;
            }
        });

        CommonUtils.printThreadLog("here not blocked main continue");
        String news = newsFuture.get();
        System.out.println("news = " + news);
        CommonUtils.printThreadLog("main end");

        /**
         * 疑问：get方法阻塞的，会不会影响程序性能？
         * 回调函数
         */

    }
}
