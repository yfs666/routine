package com.routine.java.completablefuture;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long aLong = getStr(1).thenCompose(CompletableFutureTest::getLong).get();
        System.out.println(aLong);
//        com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Single.
    }



    public static CompletableFuture<String> getStr(Integer i) {
        return CompletableFuture.completedFuture(String.valueOf(i));
    }
    public static CompletableFuture<Long> getLong(String i) {
        return CompletableFuture.completedFuture(Long.parseLong(i));
    }
}
