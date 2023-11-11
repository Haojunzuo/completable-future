package cn.wolfcode.utils;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;


public class CommonUtilsDemo {

    public static void main(String[] args) {
        // 测试 CommonUtils 工具类
        String content = CommonUtils.readFile("news.txt");
        CommonUtils.printThreadLog(content);
    }
    
}
