package com.routine.java.concurrent;

/**
 * 编译器对锁的优化措施，锁消除技术
 * JIT编译器(Just In Time编译器)可以在动态编译同步代码时，使用一种叫逃逸分析的技术，来通过该项技术判别程序中所使用的的锁对象是否只被一个线程所使用的，
 * 而没有散布到其他线程中；如果情况就是这样的话，那么JIT编译器在编译这个同步代码时就不会生成synchronized关键字所标识的锁申请与释放机器码，从而消除了
 * 锁的使用流程
 */
public class MyTest4 {


    public void method() {
        Object object = new Object();
        synchronized (object) {
            System.out.println(123);
        }
    }

}
