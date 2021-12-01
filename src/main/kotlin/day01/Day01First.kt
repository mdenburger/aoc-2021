package day01

import java.io.File

fun main() {
    val measurements = File("src/main/kotlin/day01/day01-input.txt").readLines().map { it.toInt() }
    val answer = measurements.windowed(2).count { (value, next) -> value < next }
    println(answer)
}