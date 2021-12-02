package day02

import java.io.File

fun main() {
    var position = 0
    var depth = 0
    var aim = 0

    File("src/main/kotlin/day02/day02-input.txt")
        .readLines()
        .map { it.split(" ") }
        .forEach {
            val command = it[0]
            val amount = it[1].toInt()
            when (command) {
                "forward" -> run {
                    position += amount
                    depth += (aim * amount)
                }
                "down" -> aim += amount
                "up" -> aim -= amount
            }
        }

    println(position * depth)
}
