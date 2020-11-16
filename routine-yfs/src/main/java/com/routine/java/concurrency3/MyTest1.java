package com.routine.java.concurrency3;

public class MyTest1 {

    private Object object = new Object();


    public void m1() {
        synchronized (object) {
            System.out.println(123);
        }
    }

}
