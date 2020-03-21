package com.routine.kotlin.plugins.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.future.asCompletableFuture
import org.junit.Test
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis


fun main() {
    dispatcherTest()
}


fun dispatcherTest() {
    runBlocking {
        launch { // 默认继承 parent coroutine 的 CoroutineDispatcher，指定运行在 main 线程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(100)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(100)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
    }
}

fun asyncTest() {
    runBlocking {
        val measureTimeMillis = measureTimeMillis {
            val i = async { getI() }
            val am = async { getAm() }
            val from = async { getFrom() }
            val city = async { getCity() }
            val result = "${i.await()} ${am.await()} ${from.await()} ${city.await()}"
            println(result)
        }
        println(measureTimeMillis)
    }
}



fun coroutineScopeTest() {
    val list = listOf("a", "b", "c", "d", "e")
    println("start process" + LocalDateTime.now())
    val resultList = CoroutineScope(Dispatchers.Default).async {
        list.map { async { getStr(it) } }.map { it.await() }
    }.asCompletableFuture().get()
    println(resultList + LocalDateTime.now())
}


fun globalScopeTest() {
    println("start process")
    var launch = GlobalScope.launch {
        val i = getI()
        val am = getAm()
        val from = getFrom()
        val city = getCity()
        println("$i $am $from $city")
    }.asCompletableFuture().get()
    println("end process")
    Thread.sleep(5000)
//    launch.ge
}


fun getStr(str: String): String {
    Thread.sleep(5000)
    return str + str;
}


fun getStr() {
    println("start process")
    CoroutineScope(Dispatchers.Default).async {
        val i = async { getI() }
        val am = async { getAm() }
        val from = async { getFrom() }
        val city = async { getCity() }
        val result = "${i.await()} ${am.await()} ${from.await()} ${city.await()}"
        println(result)
    }.asCompletableFuture().get()
}

fun getI(): String {
    Thread.sleep(5000)
    return "I "
}

fun getAm(): String {
    Thread.sleep(5000)
    return "am "
}

fun getFrom(): String {
    Thread.sleep(8000)
    return "from "
}

fun getCity(): String {
    Thread.sleep(5000)
    return "邯郸 ! "
}

