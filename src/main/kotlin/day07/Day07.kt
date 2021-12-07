package day07

import java.io.File
import kotlin.math.abs
import kotlin.math.min

fun main() {
    println("Answer 1: " + minimumFuel(::constantCost))
    println("Answer 2: " + minimumFuel(::increasingCost))
}

fun minimumFuel(cost: (positions: List<Int>, position: Int) -> Int): Int {
    val positions = File("src/main/kotlin/day07/day07-input.txt").readText().split(",").map { it.toInt() }

    val min = positions.minOrNull()!!
    val max = positions.maxOrNull()!!

    return (min..max).fold(Int.MAX_VALUE) { minimum, position ->
        min(cost(positions, position), minimum)
    }
}

fun constantCost(positions: List<Int>, position: Int): Int =
    positions.sumOf { abs(it - position) }

fun increasingCost(positions: List<Int>, position: Int): Int =
    positions.sumOf {
        val distance = abs(it - position)
        (distance * (distance + 1)) shr 1  // sum of integers 1..N = N * (N - 1) / 2
    }
