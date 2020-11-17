package com.routine.java.concurrency2;

import java.util.concurrent.TimeUnit;

public class MyThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        MyClass myClass = new MyClass();
        new HelloThread(myClass).start();
        TimeUnit.SECONDS.sleep(1);
        new WorldThread(myClass).start();
    }
}

class MyClass {
    public synchronized void hello() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}

class HelloThread extends Thread {
    private MyClass myClass;

    public HelloThread(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}


class WorldThread extends Thread {
    private MyClass myClass;

    public WorldThread(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.world();
    }
}
