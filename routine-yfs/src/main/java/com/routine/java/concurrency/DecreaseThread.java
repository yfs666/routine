package com.routine.java.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DecreaseThread extends Thread {
    private MyCounter myCounter;

    public DecreaseThread(MyCounter myCounter) {
        this.myCounter = myCounter;
    }


    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myCounter.decrease();
        }
    }
}
