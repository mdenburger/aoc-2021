package day06

import java.io.File

fun main() {
    println("Answer 1: " + fishCountAfter(80))
    println("Answer 2: " + fishCountAfter(256))
}

fun fishCountAfter(days: Int): Long {
    val input = File("src/main/kotlin/day06/day06-input.txt").readText().split(",").map { it.toInt() }

    val fishCountOnDay = LongArray(9)
    input.forEach {
        fishCountOnDay[it] += 1L
    }

    repeat(days) {
        val todayCount = fishCountOnDay.shiftLeft()
        fishCountOnDay[6] += todayCount
        fishCountOnDay[8] += todayCount
    }

    return fishCountOnDay.sum()
}

fun LongArray.shiftLeft(): Long {
    val first = first()
    for (index in 0..size - 2) {
        set(index, get(index + 1))
    }
    set(lastIndex, 0)
    return first
}
