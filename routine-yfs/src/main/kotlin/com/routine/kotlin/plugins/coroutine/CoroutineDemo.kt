package com.routine.kotlin.plugins.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import java.time.LocalDateTime


fun main() {
    val list = listOf("a", "b", "c", "d", "e")
    println("start process" + LocalDateTime.now())
    val resultList = CoroutineScope(Dispatchers.Default).async {
        val deferredList = mutableListOf<Deferred<String>>()
        list.forEach {
            val str = async { getStr(it) }
            deferredList.add(str)
        }
        deferredList.map { it.await() }
    }.asCompletableFuture().get()
    println(resultList + LocalDateTime.now())
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