package com.routine.java.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo1 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        final MMT m = new MMT(lock);
        Thread tt = new Thread(() -> {
            System.out.println("线程一 开始执行。。。");
            try {
                m.update("张三");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断(锁释放)。。。");
            }
            System.out.println("线程一 结束执行。。。");
        }, "线程一");
        Thread tt2 = new Thread(() -> {
            System.out.println("线程二 开始执行。。。");
            try {
                m.update("李四");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断(锁释放)。。。");
            }
            System.out.println("线程二 结束执行。。。");
        }, "线程二");
        tt.start();
        tt2.start();
        //中断线程
        tt.interrupt();
        try {
            tt.join();
            tt2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class MMT {
    String name;
    Lock lock = null;

    public MMT(Lock lock) {
        this.lock = lock;
    }

    public void update(String name) throws InterruptedException {
//	   lock.lock();
//	   boolean tryLock = lock.tryLock();//尝试获取锁
        //中断只是在当前线程获取锁之前，或者当前线程获取锁的时候被阻塞
//	   lock.lockInterruptibly();
        lock.tryLock(3000, TimeUnit.SECONDS);
        try {
            setName(name);
            System.out.println(Thread.currentThread().getName() + " 变换后的姓名为" + name);
        } finally {
            lock.unlock();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}