package com.routine.java.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestReactor {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Flux.fromIterable(list)
                .subscribeOn(Schedulers.elastic())
                .buffer(10)
                .flatMap(ids -> Mono.fromSupplier(() -> getNames(ids)).subscribeOn(Schedulers.elastic()))
                .flatMapIterable(names -> names)
                .collectList()
                .subscribe();
        System.out.println("执行结束");
        TimeUnit.SECONDS.sleep(50L);
    }


    public static List<String> getNames(List<Integer> ids) {
        try {
            System.out.println(LocalDateTime.now());
            System.out.println(ids);
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ids.stream().map(String::valueOf).collect(Collectors.toList());
    }

}




