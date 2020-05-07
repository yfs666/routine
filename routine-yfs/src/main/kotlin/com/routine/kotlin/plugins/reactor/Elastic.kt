package com.routine.kotlin.plugins.reactor

import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toFlux

fun test10() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val chunked = list.chunked(3)
    val block = chunked.toFlux()
            .flatMap {
                Mono.fromSupplier {
                    Thread.sleep(5000)
                    println(it[0])
                    it.size
                }.subscribeOn(Schedulers.elastic())
            }.subscribe()
    Thread.sleep(50000)
    println(block)
}

fun test11() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val chunked = list.chunked(3)
    chunked.parallelStream().forEach {
        Thread.sleep(5000)
        println("aaa")
        it.size
    }
    Thread.sleep(50000)
}

fun main() {
    test11()
}