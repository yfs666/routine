package lambdasinaction.reactor;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.Exceptions;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import sun.plugin2.main.client.LiveConnectSupport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo01 {

    @Test
    public void test1() {
        Flux<Integer> ints = Flux.range(1, 3);
        System.out.println(123);
        // i是流中的单个数据
        ints.subscribe(i -> {
            System.out.print("a");
            System.out.println(i);
        });
    }

    @Test
    public void test2() {
        Flux<Integer> flux = Flux.range(1, 4)
                .map(i -> {
                    if (i < 3) {
                        System.out.println(i);
                        return i;
                    } else {
                        throw new RuntimeException("异常");
                    }
                });

        flux.subscribe(System.out::println, System.err::println);
    }

    @Test
    public void test3() {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(i -> {
                    if (i / 3 == 0) {
                        return i;
                    } else {
                        throw new RuntimeException("异常");
                    }
                });
        flux.subscribe(System.out::println, System.err::println, () -> System.out.println("done"));
    }

    @Test
    public void test4() {
        BaseSubscriber<Integer> ss = new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("订阅成功");
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("onNext,value is " + value);
                request(1);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("cancel");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("error");
                super.hookOnError(throwable);
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("finally");
                super.hookFinally(type);
            }
        };

        Flux<Integer> flux = Flux.range(1, 4)
                .map(i -> {
                    if (i < 3) {
                        System.out.println(i);
                        return i * 10;
                    } else {
                        throw new RuntimeException("异常");
                    }
                }).map(i -> {
                    System.out.println(i + 1);
                    return i + 1;
                });
        flux.subscribe(System.out::println,
                System.err::println,
                () -> System.out.println("done"),
                s -> ss.request(10)
        );
        flux.subscribe(ss);
    }

    /**
     *
     */
    @Test
    public void test5() {
        Flux<String> flux = Flux.generate(
                // 初始值
                () -> 0,
                // 执行方法，返回下一个状态，sink.next是生成的下一个对象
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });
        flux.subscribe(System.out::println);

        Flux<String> flux1 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("5 x " + i + " = " + 5 * i);
                    if (i == 10) sink.complete();
                    return state;
                });
        flux1.subscribe(System.out::println);
    }

    @Test
    public void test6() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long l = state.getAndIncrement();
                    sink.next("aaa" + l);
                    if (l == 20) {
                        sink.complete();
                    }
                    return state;
                }, System.out::println
                // 函数是把最后一个state给消费掉
        );
        flux.subscribe(System.out::println);
    }

    interface MyEventListener<T> {
        void onDataChunk(List<T> chunk);

        void processComplete();
    }

    // TODO: 2020/3/3 4.4.4没懂
    @Test
    public void test7() {
//        Flux<String> bridge = Flux.create(sink -> {
//            myEventProcessor.register(
//                    new MyEventListener<String>() {
//
//                        public void onDataChunk(List<String> chunk) {
//                            for(String s : chunk) {
//                                sink.next(s);
//                            }
//                        }
//
//                        public void processComplete() {
//                            sink.complete();
//                        }
//                    });
//        });
    }

    public String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }

    @Test
    public void test8() {
        Flux<String> flux = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null) {
                        sink.next(letter);
                    }
                });
        flux.subscribe(System.out::println);
    }

    @Test
    public void test9() {
        Flux.just(10)
                .map(i -> {
                    System.out.println(i);
                    return "5";
                })
                .onErrorReturn(e -> e instanceof Exception, "error")
                .subscribe();
    }

    @Test
    public void test10() {
        Flux<String> flux =
                Flux.interval(Duration.ofMillis(250))
                        .map(input -> {
                            if (input < 3) return "tick " + input;
                            throw new RuntimeException("boom");
                        })
                        .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test11() {
        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
                .elapsed()
                .retry(1)
                .subscribe(System.out::println, System.err::println);

        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test12() {
        Flux<String> flux = Flux
                .<String>error(new IllegalArgumentException())
                .doOnError(System.out::println)
                .retryWhen(companion -> companion.take(3));
        flux.subscribe(System.out::println);
    }

    @Test
    public void test13() {

        Flux.<String>error(new IllegalArgumentException())
                .retryWhen(companion -> companion
                        .zipWith(Flux.range(1, 4),
                                (error, index) -> {
                                    if (index < 4) return index;
                                    else throw Exceptions.propagate(error);
                                })
                ).subscribe(System.out::println);
    }

    /**
     * 测试
     */
    @Test
    public void test14() {
        Flux<String> source = Flux.just("foo", "bar", "error").map(str -> {
            if ("error".equals(str)) {
                throw new RuntimeException("boom");
            }
            return str + 1;
        });

        StepVerifier.create(
                source)
                .expectNext("foo1")
                .expectNext("bar1")
                .expectErrorMessage("boom")
                .verify();
    }

    @Test
    public void test15() {
        Hooks.onOperatorDebug();
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(1)))
                .expectSubscription()
                .expectNoEvent(Duration.ofDays(1))
                .expectNext(0L)
                .verifyComplete();
    }

    @Test
    public void test16() {
        Flux<Integer> flux = Flux.range(1, 10)
                .log()
                .take(5);
        flux.subscribe();
    }

    @Test
    public void test17() {
        Function<Flux<String>, Flux<String>> filterAndMap =
                f -> f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);

        Flux<String> flux = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .doOnNext(System.out::println)
                .transform(filterAndMap);
        flux.subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: " + d));
        System.out.println("---------------------华丽丽的分割线--------------------");
        flux.subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: " + d));
    }

    @Test
    public void test18() {
        AtomicInteger ai = new AtomicInteger();
        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
            if (ai.incrementAndGet() == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };

        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .compose(filterAndMap);

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
        System.out.println("---------------------华丽丽的分割线--------------------");
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: " + d));
    }

    @Test
    public void test19() {
        Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .doOnNext(System.out::println)
                .filter(s -> s.startsWith("o"))
                .map(s -> {
//                    try {
////                        Thread.sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    return s.toUpperCase();
                });
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        source.subscribe(d -> System.out.println("Subscriber 1: " + d));
        System.out.println("---------------------华丽丽的分割线--------------------");
        source.subscribe(d -> System.out.println("Subscriber 2: " + d));
    }

    @Test
    public void test20() {
        UnicastProcessor<String> hotSource = UnicastProcessor.create();

        Flux<String> hotFlux = hotSource.publish()
                .autoConnect()
                .map(String::toUpperCase);
        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: " + d));
        hotSource.onNext("blue");
        hotSource.onNext("green");
        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: " + d));
        hotSource.onNext("orange");
        hotSource.onNext("purple");
        hotSource.onComplete();
    }

    @Test
    public void test21() {
        Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("subscribed to source"));
        ConnectableFlux<Integer> co = source.publish();
        co.subscribe(System.out::println, e -> {
        }, () -> {
        });
        co.subscribe(System.out::println, e -> {
        }, () -> {
        });
        System.out.println("done subscribing");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("will now connect");
        co.connect();
    }

    @Test
    public void test22() {
        Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("subscribed to source"));
        Flux<Integer> autoCo = source.publish().autoConnect(2);
        autoCo.subscribe(System.out::println, e -> {
        }, () -> {
        });
        System.out.println("subscribed first");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subscribing second");
        autoCo.subscribe(System.out::println, e -> {
        }, () -> {
        });
    }

    @Test
    public void test23() {
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                        .map(a -> {
                            System.out.println(a.key());
                            return a;
                        })
                        .concatMap(g -> g.defaultIfEmpty(-1) //如果组为空，显示为 -1
                                .map(String::valueOf) //转换为字符串
                                .startWith(g.key())) //以该组的 key 开头
        )
                .expectNext("odd", "1", "3", "5", "11", "13")
                .expectNext("even", "2", "4", "6", "12")
                .verifyComplete();
    }

    @Test
    public void test24() {
        StepVerifier.create(
                Flux.range(1, 10)
                        .window(5, 3) //overlapping windows
                        .concatMap(g -> g.defaultIfEmpty(-1)) //将 windows 显示为 -1
        )
                .expectNext(1, 2, 3, 4, 5)
                .expectNext(4, 5, 6, 7, 8)
                .expectNext(7, 8, 9, 10)
                .expectNext(10)
                .verifyComplete();
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .windowWhile(i -> i % 2 == 0)
                        .concatMap(g -> g.defaultIfEmpty(-1))
        )
                .expectNext(-1, -1, -1) //分别被奇数 1 3 5 触发
                .expectNext(2, 4, 6) // 被 11 触发
                .expectNext(12) // 被 13 触发
                .expectNext(-1) // 空的 completion window，如果 onComplete 前的元素能够匹配上的话就没有这个了
                .verifyComplete();
    }

    @Test
    public void test25() {
        StepVerifier.create(
                Flux.range(1, 10)
                        .buffer(5, 3) // 缓存重叠
        )
                .expectNext(Arrays.asList(1, 2, 3, 4, 5))
                .expectNext(Arrays.asList(4, 5, 6, 7, 8))
                .expectNext(Arrays.asList(7, 8, 9, 10))
                .expectNext(Collections.singletonList(10))
                .verifyComplete();
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .bufferWhile(i -> i % 2 == 0)
        )
                .expectNext(Arrays.asList(2, 4, 6)) // 被 11 触发
                .expectNext(Collections.singletonList(12)) // 被 13 触发
                .verifyComplete();
    }

    @Test
    public void test26() {
        Flux.range(1, 50)
                .parallel(20)
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " 1-> " + i));
        System.out.println("---------------------华丽丽的分割线--------------------");
        Flux.range(1, 50)
                .parallel(20)
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " 2-> " + i));
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test27() {
        Integer[] ids = {1, 2, 3, 4, 5};
        List<String> list = null;
        List<String> collect = list.stream().flatMap(it -> Arrays.asList(it.split(" ")).stream()).collect(Collectors.toList());
        List<List<String>> collect1 = list.stream().map(it -> Arrays.asList(it.split(" "))).collect(Collectors.toList());

//        Flux<Mono<List<Integer>>> map = Flux.fromIterable(Arrays.asList(ids))
//                .distinct()
//                .buffer(2)
//                .map((subIdList) -> {
//                    Mono<List<Integer>> listMono = Mono.fromSupplier(() -> subIdList);
//                    return listMono;
//                });
//
//        Flux<User> userFlux = Flux.fromIterable(this.listByIds(Arrays.asList(1, 2)));
//        User listFlux = Flux.fromIterable(Arrays.asList(ids))
//                .distinct()
//                .buffer(2)
//                .flatMap((subIdList) -> {
//                    Mono<List<User>> listMono = Mono.fromSupplier(() -> this.listByIds(subIdList));
//                    Mono<List<User>> subscribeOn = listMono.subscribeOn(Schedulers.elastic());
//                    return subscribeOn;
//                }).flatMapIterable(Function.identity()).blockFirst();
//        List<User> users = null;
//        Map<Integer, List<User>> collect2 = users.stream().collect(Collectors.groupingBy(u->u.getId()));
//        Flux<GroupedFlux<Integer, User>> groupedFluxFlux = listFlux.groupBy(User::getId);
//        Mono<Map<Integer, User>> mapMono = listFlux.collectMap(User::getId);
//        mapMono.block();
//        Mono<Map<Integer, Collection<User>>> mapMono1 = listFlux.collectMultimap(User::getId);
//        Flux<List<Integer>> flux = Flux.fromIterable(Arrays.asList(ids))
//                .distinct()
//                .buffer(2)
//                .flatMap((o) -> Mono.fromSupplier(() -> o));
//        Flux<Integer> integerFlux = flux.flatMapIterable(Function.identity());
//                .flatMap((subIdList) -> {
////                    List<User> users = this.listByIds(subIdList);
//                    Mono<List<Integer>> listMono = Mono.fromSupplier(() -> subIdList);
////                    Mono<List<User>> subscribeOn = listMono.subscribeOn(Schedulers.elastic());
//                    return listMono;
//                });
//
//        listFlux.f

//        Mono.fromSupplier(() -> "create from supplier").subscribe(System.out::println);
//        Mono<List<User>> listMono = Mono.fromSupplier(() -> this.listByIds(Arrays.asList(ids))).subscribeOn(Schedulers.elastic());
//        listMono = listMono
//        Flux<List<Integer>> flux = Flux.fromIterable(Arrays.asList(ids))
//                .distinct()
//                .buffer(3)
//                .flatMap(() -> this.listByIds(Arrays.asList(ids)))
//        flux.subscribe(System.out::println);
//        System.out.println("end   ");
    }

    @Test
    public void test28() {
        Flux.merge(Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(5),
                Flux.interval(Duration.of(600, ChronoUnit.MILLIS), Duration.of(500, ChronoUnit.MILLIS)).take(5))
                .take(5)
                .toStream().forEach(System.out::println);

        System.out.println("-----------------------------");

        Flux.mergeSequential(Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(5),
                Flux.interval(Duration.of(600, ChronoUnit.MILLIS), Duration.of(500, ChronoUnit.MILLIS)).take(5))
                .take(5)
                .toStream().forEach(System.out::println);
    }

    @Test
    public void test29() {
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.of(10000, ChronoUnit.MILLIS)).take(3),
                Flux.just("A", "B"))
                .toStream().forEach(System.out::println);

        System.out.println("------------------");

        Flux.combineLatest(
                Arrays::toString,
                Flux.just(0, 1),
                Flux.just("A", "B"))
                .toStream().forEach(System.out::println);

        System.out.println("------------------");

        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.of(1000, ChronoUnit.MILLIS)).take(2),
                Flux.interval(Duration.of(10000, ChronoUnit.MILLIS)).take(2))
                .toStream().forEach(System.out::println);
    }

    @Test
    public void test30() {
        Flux.first(
                Flux.just(1, 2, 3),
                Flux.fromArray(new String[]{"A", "B"}),
                Flux.just(8, 8, 8))
                .subscribe(System.out::println);
    }

    private List<User> listByIds(List<Integer> list) {
        return list.stream().map(id -> User.of(id, "name-" + id)).collect(Collectors.toList());
    }
    @Test
    public void test31() throws InterruptedException {
        List<String> list = Arrays.asList("aa", "bb", "cc", "bb", "cc", "bb", "cc", "bb", "cc", "dd");
        // values 的格式是 [aa,aa,aa] [bb,bb,bb] [cc,cc,cc],[dd]
        Collection<List<String>> values = list.stream().collect(Collectors.groupingBy(it -> it)).values();
        System.out.println("开始执行：当前时间是" + LocalDateTime.now());
        Flux.fromStream(values.stream())
                // 对于现在的数据，it共有4个，分别是[aa,aa,aa] [bb,bb,bb] [cc,cc,cc],[dd]，那么有三个线程分别消费他们
                .flatMap(it -> {
                    System.out.println(it + ",thread name === " + Thread.currentThread().getName());
                    return Flux.fromStream(it.stream())
                            .delayUntil(d -> Mono.delay(Duration.ofSeconds(getRandom())));

                }).subscribe(it -> {
            System.out.println("it=" + it + ",time=" + LocalDateTime.now() + ",thread name === " + Thread.currentThread().getName());
        });
        Thread.sleep(50000000L);
    }

    private int getRandom() {
        int i = new Random().nextInt(10);
        System.out.println("执行random,random is " + i + ",thread name === " + Thread.currentThread().getName());
        return i;
    }


    static class User {
        private Integer id;
        private String name;

        public static User of(Integer id, String name) {
            return new User(id, name);
        }

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}


