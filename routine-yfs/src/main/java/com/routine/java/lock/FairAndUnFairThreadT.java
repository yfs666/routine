package com.routine.java.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁测试
 */
public class FairAndUnFairThreadT {
 
 
    public static void main(String[] args) throws InterruptedException {
        //默认非公平锁
        final Lock lock = new ReentrantLock(false);
        final MM m = new MM(lock);
        for (int i=1;i<=20 ;i++){
            String name = "线程"+i;
            Thread tt = new Thread(new Runnable() {
                @Override
                public void run() {
                   for(int i=0;i<2;i++){
                       m.testReentrant();
                   }
                }
            },name);
            tt.start();
        }
 
    }
}
class MM {
    Lock lock = null;
    MM(Lock lock){
        this.lock = lock;
    }
 
    public void testReentrant(){
        lock.lock();
        try{
            Thread.sleep(120);
            System.out.println(Thread.currentThread().getName() );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
 
    public synchronized  void testSync(){
        System.out.println(Thread.currentThread().getName());
    }
 
}
