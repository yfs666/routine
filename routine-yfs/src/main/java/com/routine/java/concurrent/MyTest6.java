package com.routine.java.concurrent;

/**
 * 死锁：线程1等待线程2互斥持有的资源，而线程2也在等待线程1互斥持有的资源，两个线程都无法继续执行
 * 活锁：线程持续重试一个失败的操作，导致无法继续执行
 * 饿死：线程一直被调度器延迟访问其赖以执行的资源，也许是调度器先于优先级的线程而执行高优先级的线程，同时总是会有一个高优先级的线程可以执行，饿死也叫无限延迟
 */
public class MyTest6 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void m1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("m1");
            }
        }
    }

    public void m2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("m1");
            }
        }
    }
}
