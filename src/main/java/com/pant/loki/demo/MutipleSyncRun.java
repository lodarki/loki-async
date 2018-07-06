package com.pant.loki.demo;

import com.pant.loki.utils.AsyncUtil;

import java.util.ArrayList;
import java.util.List;

public class MutipleSyncRun {

    private volatile static List<String> handleList = new ArrayList<>();

    public static void main(String[] args) {

        List<String> originList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            originList.add(String.valueOf(i));
        }

        AsyncUtil.runAsync(() -> {
            int j = 0;
            long beginMillis = System.currentTimeMillis();
            while (1000 > j) {
                synchronized (handleList) {
                    if (handleList.size() >= 20) {
                        List<String> targetList = new ArrayList<>();
                        targetList.addAll(handleList);
                        handleList.clear();
                        j += targetList.size();
                        System.out.println(j);
                        System.out.println(targetList);
                    }
                }
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long endMillis = System.currentTimeMillis();
            System.out.println("总共处理用时: " + (endMillis - beginMillis));
        });

        originList.parallelStream().forEach(item -> {

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (handleList) {
                handleList.add(item);
            }
        });
    }
}
