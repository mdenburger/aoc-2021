package day02

import java.io.File

fun main() {
    var position = 0
    var depth = 0

    File("src/main/kotlin/day02/day02-input.txt")
        .readLines()
        .map { it.split(" ") }
        .forEach {
            val command = it[0]
            val amount = it[1].toInt()
            when (command) {
                "forward" -> position += amount
                "down" -> depth += amount
                "up" -> depth -= amount
            }
        }

    println(position * depth)
}