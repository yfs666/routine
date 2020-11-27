package com.routine.java.concurreny4;

/**
 * java内存模（JMM Java Memory Model）型以及happen-before
 *
 * 1、变量的原子性问题
 * 2、变量的可见性问题
 * 3、变量的时序性问题
 *
 * happen-before
 *
 * 1、顺序执行规则（限定在单个线程上）：该线程的每个动作都happen-before它后面的动作
 * 2、隐式锁（monitor）规则：unlock一定happen-before 第二个线程对同一把锁的lock，之前线程对同步代码块的所有执行结果对后续获取锁的线程来说都是可见的
 * 3、对于同一个volatile变量的写操作一定happen-before下一个线程对该变量的读操作
 * 4、多线程的启动规则：Thread对象的start操作，一定happen-before该线程的run方法中的任何一个动作，包括其中可能会启动的子线程
 * 5、多线程的终止规则：一个线程启动了一个子线程，并且调用了子线程的join方法等待其结果，那么当子线程结束后，父线程接下来的所有操作都可以看到子线程方法run中的所有执行的结果
 * 6、线程的中断规则：可以通过调用interrupt方法中断线程，线程中断后，其他线程一定会检测到该线程的中断结果
 */
public class MyTest4 {

}
