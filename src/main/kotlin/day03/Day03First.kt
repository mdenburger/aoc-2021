package day03

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day03/day03-input.txt").readLines()
    val numbers = lines.map { it.toInt(2) }

    var gamma = 0
    var epsilon = 0

    repeat(lines.first().length) { bit ->
        val mask = 1 shl bit
        val zeros = numbers.count { (it and mask) == 0 }
        val common = if (zeros > (lines.size / 2)) 0 else 1
        gamma += common * mask
        epsilon += (1 - common) * mask
    }

    println(gamma * epsilon)
}