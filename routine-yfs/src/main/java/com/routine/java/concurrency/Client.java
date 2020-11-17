package com.routine.java.concurrency;

public class Client {
    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();
        Thread increaseThread = new IncreaseThread(myCounter);
        Thread increaseThread2 = new IncreaseThread(myCounter);
        Thread decreaseThread = new DecreaseThread(myCounter);
        Thread decreaseThread2 = new DecreaseThread(myCounter);
        increaseThread.start();
        increaseThread2.start();
        decreaseThread.start();
        decreaseThread2.start();
    }
}
