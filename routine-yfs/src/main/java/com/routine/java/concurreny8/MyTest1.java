package com.routine.java.concurreny8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 对于ReentrantLock来说：
 * 1、尝试获取对象的锁，如果获取不到（意味着已经有其他线程持有了锁，并且尚未释放），那么他就会进入AQS的阻塞队列中
 * 2、如果获取到，那么根据锁是公平锁还是非公平锁做不同处理
 * 2.1公平锁：线程会直接放入AQS的队列末尾，直到获取到锁
 * 2.2非公平锁：先通过CAS尝试一次获取锁
 * 3、当锁被释放时（unlock），那么底层会调用release方法对state成员变量减1，如果不为0，那么release操作就执行完完毕，如果为0.则调用LockSupport的unpark方法唤醒该线程后的等待队列瞻仰红的第一个后继线程，将其唤醒，使之能够获取到锁；state在release之后可能大于0的原因是，锁是可重入的
 *
 * 上锁就是对AQS的成员变量的state操作
 *
 *
 * 关于ReentrantReadWriteLock的操作逻辑：
 * 读锁：
 * 1、在获取读锁时，会尝试判断当前对象是否已经有了写锁，如果有直接失败
 * 2、如果没有写锁，就表示当前对象没有排他锁，则当前线程会尝试给对象加锁
 * 3、如果当前线程已经持有了该对象的锁，那么就直接将读锁数量加1
 *
 * 写锁：
 * 1、在获取写锁时，会尝试判断当前对象是否拥有了锁（读或者写），如果已经用了且持有的线程并非当期那线程，直接失败
 * 2、如果当前对象没有加锁，那么写锁就会为当前对象加锁，并且将写锁数量加1
 * 3、将当前对象的拍他所线程持有者设为自己
 *
 *
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
