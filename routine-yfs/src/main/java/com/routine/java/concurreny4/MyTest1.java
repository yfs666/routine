package com.routine.java.concurreny4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于Lock与synchronized关键字在锁处理上的重要区别
 *
 * 1、锁的获取方式：Lock是根据程序代码的方式获取，由开发者手动操作，synchronized是通过jvm获取的，无需开发者干预
 * 2、具体实现方式：Lock是通过java代码方式实现，synchronized是通过JVM底层来实现（无需开发者关注）
 * 3、锁的释放方式：Lock务必通过unlock在finally块中手工释放，synchronized是通过jvm来释放
 * 4、锁的具体类型：Lock提供了多种，如公平锁，非公平锁，synchronized和Lock都提供了可重入锁
 */
public class MyTest1 {

    private Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
//            System.out.println("m1 get Lock");
        } finally {
            lock.unlock();
        }
    }


    public void m2() {
        boolean result = false;
        result = lock.tryLock();
        if (result) {
            try {
                lock.lock();
                System.out.println("m2 get Lock");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("m2 not get lock ,do something");
        }

//        try {
//            lock.lock();
//            System.out.println("m2 get Lock");
//        } finally {
//            lock.unlock();
//        }
    }

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest1.m1();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest1.m2();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread2.start();
    }

}
