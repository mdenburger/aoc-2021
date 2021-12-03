package day03

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day03/day03-input.txt").readLines()

    val oxygenRating = calculate(lines, 1)
    val co2Rating = calculate(lines, 0)

    println(oxygenRating * co2Rating)
}

fun calculate(lines: List<String>, commonBit: Int): Int {
    val bitSize = lines.first().length
    val numbers = lines.mapTo(mutableListOf()) { it.toInt(2) }

    for (bit in bitSize - 1 downTo 0) {
        val mask = 1 shl bit
        val zeros = numbers.count { (it and mask) == 0 }
        val half = numbers.size / 2
        val common = when {
            zeros > half -> 0
            zeros < half -> 1
            else -> 1
        }

        numbers.removeIf {
            if (commonBit == 1) {
                if (common == 0) (it and mask) != 0 else (it and mask) == 0
            } else {
                if (common == 0) (it and mask) == 0 else (it and mask) != 0
            }
        }

        if (numbers.size == 1) {
            break
        }
    }

    return numbers.first()
}