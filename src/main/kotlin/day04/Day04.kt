package day04

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day04/day04-input.txt").readLines().toMutableList()

    val numbers = lines.removeAt(0).split(",").map { it.toInt() }
    val boards = mutableListOf<Board>()

    while (lines.isNotEmpty()) {
        boards.add(parseBoard(lines))
    }

    part1(numbers.toMutableList(), boards.toMutableList())
    part2(numbers.toMutableList(), boards.toMutableList())
}

fun parseBoard(lines: MutableList<String>): Board {
    lines.removeFirst() // empty line

    val numbers = mutableListOf<Int>()

    repeat(5) {
        val row = lines.removeFirst().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        numbers += row
    }

    return Board(numbers)
}

fun part1(numbers: MutableList<Int>, boards: List<Board>) {
    var drawn: Int
    var winner: Board?

    do {
        drawn = numbers.removeFirst()
        boards.forEach { it.mark(drawn) }
        winner = boards.find { it.wins() }
    } while (winner == null)

    println(winner.score() * drawn)
}

fun part2(numbers: MutableList<Int>, boards: MutableList<Board>) {
    var drawn: Int
    var lastWinner: Board? = null

    do {
        drawn = numbers.removeFirst()
        boards.forEach { it.mark(drawn) }
        val winners = boards.filter { it.wins() }
        if (winners.isNotEmpty()) {
            boards.removeAll(winners)
            lastWinner = winners.first()
        }
    } while (boards.isNotEmpty())

    println(lastWinner!!.score() * drawn)
}

class Board(numbers: List<Int>) {

    private val numbers = numbers.map { BoardNumber(it) }

    fun mark(number: Int) {
        numbers.find { it.value == number }?.mark()
    }

    fun wins(): Boolean = hasWinningRow() || hasWinningColumn()

    private fun hasWinningRow(): Boolean =
        numbers.chunked(5).any { row -> row.all { it.marked } }

    private fun hasWinningColumn(): Boolean =
        (0..4).any { col ->
            (0..4).all { row ->
                numbers[col + (row * 5)].marked
            }
        }

    fun score(): Int =
        numbers.filter { !it.marked }.sumOf { it.value }
}

class BoardNumber(val value: Int) {
    var marked = false

    fun mark() {
        marked = true
    }
}
