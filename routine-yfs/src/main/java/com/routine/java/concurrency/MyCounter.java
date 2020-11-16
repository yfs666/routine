package com.routine.java.concurrency;

public class MyCounter {

    private int counter = 0;


    public synchronized void increase() {
        while (counter != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        System.out.println(counter);
        this.notify();
    }


    public synchronized void decrease() {
        while (counter != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.println(counter);
        this.notify();
    }


}
