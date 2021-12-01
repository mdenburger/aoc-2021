package day01

import java.io.File

fun main() {
    val measurements = File("src/main/kotlin/day01/day01-input.txt").readLines().map { it.toInt() }
    val answer = measurements.windowed(3).windowed(2).count { (window1, window2) -> window1.sum() < window2.sum() }
    println(answer)
}