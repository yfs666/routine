package com.routine.kotlin.plugins.reactor

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers.*
import java.time.Duration
import java.time.LocalTime
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.function.Function
import java.util.function.Function.identity

private val queue: BlockingQueue<Int> = LinkedBlockingQueue<Int>()

private val random = Random()


fun test2() {
    Flux.fromStream(listOf(1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8).stream())
            .groupBy { it % 3 }
//            .doOnNext { println(it) }
//            .doOnNext { println("key= ${it.key()}") }
            .map {
                it.metrics()
            }
            .flatMap {
                it.delayElements(Duration.ofSeconds(5)).subscribeOn(single())
            }
            .subscribe {
                println("it= $it, thread = ${Thread.currentThread().id}")
            }
    Thread.sleep(500000)
}

fun test1() {


    Flux.generate<Int> { queue.take().apply { it.next(this) } }
            .subscribeOn(single())
//            .doOnNext { println("消费端的随机数是：$it") }
            .groupBy {
                it % 3
            }
            .map { it.metrics() }
            .flatMap {
                it.delayElements(Duration.ofSeconds(5)).subscribeOn(elastic())
            }
            .doOnNext { println("${LocalTime.now()} 最后执行消费成功：$it") }
            .subscribe()

    Flux.interval(Duration.ofMillis(10000), Duration.ofMillis(10)).subscribe {
        val number = random.nextInt(1000)
//        println("执行一次生产消息:$number")
        queue.plusAssign(number)
    }

    println("====================")
    Thread.sleep(50000000000)
}


fun test3() {
    Flux.interval(Duration.ofMillis(10), Duration.ofMillis(10)).subscribe {
        val number = random.nextInt(1000)
//        println("执行一次生产消息:$number")
        queue.plusAssign(number)
    }

    Flux.generate<Int> { queue.take().apply { it.next(this) } }
            .subscribeOn(single())
//            .doOnNext { println("消费端的随机数是：$it") }
            .groupBy {
                it % 100
            }.doOnNext {

            }
            .flatMap {
                println("这是此流的key================== ${it.key()}")
                it.delayElements(Duration.ofSeconds(10))
            }
            .doOnNext { println("${LocalTime.now()} 最后执行消费成功：$it") }
            .subscribe()
    println("====================")
    Thread.sleep(50000000000)
}


fun test4() {
    val block = Flux.generate<Int> { queue.take().apply { it.next(this) } }
            .subscribeOn(newSingle("myThread"))
            .bufferTimeout(100, Duration.ofSeconds(10))
            .doOnNext { println("${LocalTime.now()} 数据收集好了 $it ") }
            .flatMapIterable(identity())
            .groupBy { it % 3 }
            .flatMap {
                it.subscribeOn(elastic()).delayUntil { Mono.delay(Duration.ofSeconds(5)) }
            }
            .doOnNext {
                println("${LocalTime.now()} 最后执行消费成功=====================：$it")
            }
            .subscribe()
    listOf(1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2).forEach {
        queue.plusAssign(it)
    }
    println("${LocalTime.now()} 第1批队列加完")
    Thread.sleep(35000)
    listOf( 3, 6, 3, 6, 3, 6, 3, 4, 5, 4, 5, 4, 5, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6).forEach {
        queue.plusAssign(it)
    }
    println("${LocalTime.now()} 第2批队列加完")
    println("====================")
    Thread.sleep(50000000000)
}



fun test5() {
    val i: Function<Int, Int>
    val block = Flux.fromIterable(listOf(3, 6, 3, 6, 3, 6, 3, 4, 5, 4, 5, 4, 5, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6))
            .collectMap { it % 3 }
            .block()
    println(block)
}

fun main() {

}
