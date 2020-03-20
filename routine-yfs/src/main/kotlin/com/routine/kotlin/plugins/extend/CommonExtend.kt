package com.routine.kotlin.plugins.extend

import com.alibaba.fastjson.JSON
import org.apache.commons.lang3.math.NumberUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.measureTimeMillis

fun <T : Comparable<T>> Sequence<T>.minAndMax(): Pair<T, T> {
    val iterator = iterator()
    var min = iterator.next()
    var max = min
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (min > e) min = e
        if (max < e) max = e
    }
    return min to max
}

/**
 * 判断列表不是空，并且不为空
 */
fun List<Any>?.notNullAndEmpty(): Boolean = !this.isNullOrEmpty()

inline fun <reified T> String.toObject(): T = JSON.parseObject(this, T::class.java)

inline fun <reified T> String.toList(): List<T> = JSON.parseArray(this, T::class.java)

fun String?.isNumber(): Boolean = NumberUtils.isNumber(this)

fun String.toLocalDate(): LocalDate = LocalDate.parse(this)

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

fun String?.notNullAndBlank(): Boolean = this != null && this.isNotBlank()

fun String?.notNullAndEmpty(): Boolean = this != null && this.isNotEmpty()

fun <T> timeRecord(name: String, block: () -> T): T {
    var result: T? = null
    val measureTimeMillis = measureTimeMillis {
        result = block()
    }
    return result!!
}
