package com.routine.java.concurreny8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
