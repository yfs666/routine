package com.routine.java.concurreny8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于AQS与synchronized关键字的关系 ：
 * 1、synchronized关键字在底层是C++实现的，存在两个重要的数据结构：WaitSet EntryList
 * 2、WaitSet中存放的是调用了Object的wait方法的线程对象（被封装成了C++的Node对象）
 * 3、EntryList中存放的是陷入到阻塞状态、需要获取monitor的那些线程对象
 * 4、当一个线程被notify后，它就会从waitSet中移动到EntryList中
 * 5、进入到entryList后，该线程依然需要与其他线程争抢monitor对象
 * 6、如果正抢到，就表示该线程获取到了对象的锁，它就可以以排他的方式执行对应的代码块
 *
 * 1、AQS中存在两种队列，分别是Condition对象上的条件队列，以及AQS本身的阻塞队列
 * 2、这两个队列的每一个对象都是Node实例（里面封装了线程对象）
 * 3、当位于condition条件队列中的线程被其他线程signal后，该线程就会从条件队列移动到AQS的阻塞队列中
 * 4、位于AQS阻塞队列中的Node对象本质上都是一个由双向链表构成的
 * 5、在获取AQS锁时，这些进入到阻塞队列中的线程会按照在队列中的排序先后尝试获取
 * 6、当AQS阻塞队列中的线程获取到锁后，就表示该线程已经可以正常执行了
 * 7、陷入到阻塞状态的线程，依然需要进入到操作系统的内核态，进入阻塞（park方法实现）
 */
public class MyTest1 {
    private Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" m1 ");
        } finally {
            lock.unlock();
        }
    }
}
