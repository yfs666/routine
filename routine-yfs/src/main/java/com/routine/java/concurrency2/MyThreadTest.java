package com.routine.java.concurrency2;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class MyThreadTest {
    public static void main(String[] args) {
        Runnable runnable = new MyThread();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}

class MyThread implements Runnable {
    /**
     * 有可修改成员变量，是有状态的
     */
    int x;

    @SneakyThrows
    @Override
    public void run() {
        x = 0;
        while (true) {
            System.out.println("result is " + x++);
            TimeUnit.SECONDS.sleep(1);
            if (x == 30) {
                break;
            }
        }
    }
}