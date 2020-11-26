package com.routine.java.concurreny4;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 传统上，我们可以通过synchronized关键字、wait、notify* 来实现多个线程之间的协调和通信，整个过程都是jvm来帮助实现的，开发者无需也无法关注细节
 * 从jdk5开始，并发包提供了Lock Condition(await/signal*)来实现多个线程之间的协调通信，整个过程都是由开发者来控制的，而且相比于传统方式更加灵活，更加强大
 * Thread.sleep与wait、await本质区别：sleep本质上不会释放锁，而await会释放锁，在signal之后会重新获取到锁
 */
public class MyTest2 {
    public static void main(String[] args) {
        BoundedContainer boundedContainer = new BoundedContainer();

        IntStream.range(0, 20).forEach(i -> new Thread(() -> {
            try {
                boundedContainer.put("a" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 22).forEach(i -> new Thread(() -> {
            try {
                boundedContainer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());


    }

}


class BoundedContainer {

    private String[] elements = new String[10];

    private Lock lock = new ReentrantLock();

    private Condition notEmptyCondition = lock.newCondition();

    private Condition notFullCondition = lock.newCondition();

    private int elementCount;

    private int putIndex;

    private int takeIndex;

    public void put(String element) throws InterruptedException {
        lock.lock();
        try {
            while (elementCount == elements.length) {
                notFullCondition.await();
            }
            elements[putIndex] = element;
            elementCount++;
            if (++putIndex == elements.length) {
                putIndex = 0;
            }
            System.out.println("put method, result is " + Arrays.toString(elements));
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    public String take() throws InterruptedException {
        lock.lock();
        String element;
        try {
            while (elementCount == 0) {
                notEmptyCondition.await();
            }
            System.out.println("take index " + takeIndex);
            elementCount--;
            element = elements[takeIndex];
            elements[takeIndex] = null;
            if (++takeIndex == elements.length) {
                takeIndex = 0;
            }
            System.out.println("get method, result is " + Arrays.toString(elements));
            notFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return element;
    }

}