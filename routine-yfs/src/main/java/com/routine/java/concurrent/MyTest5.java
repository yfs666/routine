package com.routine.java.concurrent;

/**
 * 锁粗化
 * JIT编译器在执行动态编译时，若发现前后相邻的synchronized块使用的是同一个锁对象，那么它就会把这几个synchronized块合并为一个较大的同步块，这样做的好处就在于
 * 线程在执行这些代码时，就无须频繁申请释放锁了，从而达到申请与释放一次锁，就可以完全执行同步代码块，从而提升了性能。
 */
public class MyTest5 {
    final Object object = new Object();

    public void method() {

        synchronized (object) {
            System.out.println(123);
        }

        synchronized (object) {
            System.out.println(456);
        }

        synchronized (object) {
            System.out.println(789);
        }
    }
}
