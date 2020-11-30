package com.routine.java.concurreny5;

import java.util.concurrent.CyclicBarrier;

/**
 * 关于CyclicBarrier的底层执行流程
 * 1、初始化CyclicBarrier中的各种成员变量
 * 2、当调用await方法时，底层会先检查计数器是否已经归零，如果是的话，就首先执行可选的Runnable，接下来开始下一个generation
 * 3、在下一个分代中，将会充值count为parties，并重新创建Generation实例
 * 4、同时会调用Condition的signalAll方法，唤醒所有在屏障前面等待的线程，在屏障前等待
 * 5、如果计数器没有归零，那么当前的调用线程将会通过Condition的await方法，在屏障前进行等待
 * 6、以上所有的执行流程均在lock锁的控制范围内，不会出现并发情况
 */
public class MyTest1 {
    public static void main(String[] args) {

    }
}
