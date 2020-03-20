package com.routine.kotlin.plugins.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture


fun main() {
    getStr()
}


fun getStr() {
    println("start process")
    CoroutineScope(Dispatchers.Default).async {
        val i = async {getI()}
        val am = async {getAm()}
        val from = async {getFrom()}
        val city = async {getCity()}
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