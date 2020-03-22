package com.routine.kotlin.plugins.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.future.asCompletableFuture
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis


fun main() {
    runBlocking {
        val launch = launch {
            // 默认继承 parent coroutine 的 CoroutineDispatcher，指定运行在 main 线程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(10000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
        delay(10000)
        launch.start()
        launch(Dispatchers.Unconfined) {
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(100)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
    }
    println(123)
}

fun launchTest() {
    val list = listOf("aa", "bb", "cc", "bb", "cc", "bb", "cc", "bb", "cc", "dde")
    // values 的格式是 [aa,aa,aa] [bb,bb,bb] [cc,cc,cc],[dd]
    val values = list.groupBy { it }.values.toList()
    CoroutineScope(Dispatchers.IO).async {
        values.forEach {
            launch {
                it.forEach {
                    delay(5000)
                    println(it + ",current thread is " + Thread.currentThread().id)
                }
            }
        }
    }.asCompletableFuture().get()
    println("end 1----------------------------")
    values.forEach {
        CoroutineScope(Dispatchers.IO).launch {
            it.forEach {
                delay(5000)
                println(it + ",current thread is " + Thread.currentThread().id)
            }
        }
    }
    println("end 2 ----------------------------")
    Thread.sleep(5000000)
}


fun delayAndForTest() {
    val list = listOf("aa", "bb", "cc", "bb", "cc", "bb", "cc", "bb", "cc", "dd", "aa", "a", "v", "s", "s", "fads", "hh", "qd", "de", "kk", "aa", "bb", "cc",
            "bb", "cc", "bb", "cc", "bb", "cc", "dd", "aa", "a", "v", "s", "s", "fads", "hh", "qd", "de", "kk",
            "bab", "cca", "abb", "acc", "abb", "cac", "dad", "aaa", "aaf", "fv", "sf", "sf", "hello", "hfh", "qdf", "def", "kkf",
            "bb", "cc", "bb", "cc", "bb", "cc", "dd", "aa", "a", "v", "s", "s", "fads", "hh", "qd", "de", "kk"
    )
    // values 的格式是 [aa,aa,aa] [bb,bb,bb] [cc,cc,cc],[dd]
    val values = list.groupBy { it }.values.toList()
    handleLists(values)
}


fun handleFor() {
    val list = listOf("aa", "bb", "cc", "bb", "cc", "bb", "cc", "bb", "cc", "dd")
    // values 的格式是 [aa,aa,aa] [bb,bb,bb] [cc,cc,cc],[dd]
    val values = list.groupBy { it }.values.toList()
    CoroutineScope(Dispatchers.IO).async {
        values.map {
            async {
                it.map { it1 ->
                    it1.forEach {
                        delay(5000)
                        println(it + ",current thread is " + Thread.currentThread().id)
                    }
                }
            }
        }.forEach { it.await() }
    }.asCompletableFuture().get()


}

private fun handleListList(list: List<String>) {
    CoroutineScope(Dispatchers.IO).async {
        list.forEach {
            delay(5000)
            println(it)
        }
    }.asCompletableFuture().get()
}

private fun handleLists(lists: List<List<String>>) {
    CoroutineScope(Dispatchers.IO).async {
        lists.map {
            async {
                it.forEach {
                    delay(5000)
                    println(it + ",current thread is " + Thread.currentThread().id)
                }
            }
        }.map {
            it.await()
        }
    }.asCompletableFuture().get()
}


fun dispatcherTest() {
    runBlocking {
        launch {
            // 默认继承 parent coroutine 的 CoroutineDispatcher，指定运行在 main 线程
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

