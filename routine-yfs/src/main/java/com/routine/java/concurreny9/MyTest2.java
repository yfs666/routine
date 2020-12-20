package com.routine.java.concurreny9;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MyTest2 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        MyTask myTask = new MyTask(1,50);
        Integer result = forkJoinPool.invoke(myTask);
        System.out.println(result);
    }


}

class MyTask extends RecursiveTask<Integer> {

    private Integer limit = 4;

    private Integer firstIndex;

    private Integer lastIndex;

    public MyTask(Integer firstIndex,Integer lastIndex) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        int gap = lastIndex - firstIndex;
        boolean flag = gap <= limit;
        if (flag) {
            System.out.println(Thread.currentThread().getName());
            for (int i = firstIndex; i <= lastIndex; i++) {
                result += i;
            }
        } else {
            int middleIndex = (lastIndex + firstIndex) / 2;
            MyTask leftTask = new MyTask(firstIndex, middleIndex);
            MyTask rightTask = new MyTask(middleIndex + 1, lastIndex);
            invokeAll(leftTask,rightTask);
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }
}