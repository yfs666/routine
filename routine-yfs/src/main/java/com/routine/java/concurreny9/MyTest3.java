package com.routine.java.concurreny9;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MyTest3 {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(4, 10, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(20), new ThreadPoolExecutor.AbortPolicy());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        IntStream.range(1, 10).forEach(i -> {
            completionService.submit(() -> {
                return i * i;
            });
        });
        Integer integer = completionService.take().get();
    }
}
