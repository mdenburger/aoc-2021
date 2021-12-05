package day05

import java.io.File

fun main() {
    val lines: List<Line> = File("src/main/kotlin/day05/day05-input.txt")
        .readLines()
        .map { it.split(" -> ") }
        .map { Line(it.first().asPoint(), it.last().asPoint()) }

    println("Part 1: " + countOverlappingPoints(lines.filter { it.isHorizontal() || it.isVertical() }))
    println("Part 2: " + countOverlappingPoints(lines))
}

fun countOverlappingPoints(lines: List<Line>): Int {
    val grid = mutableMapOf<Point, Int>()

    lines.forEach { line ->
            line.points().forEach { point ->
                grid.compute(point) { _, value ->
                    if (value == null) 1 else value + 1
                }
            }
        }

    return grid.filter { it.value >= 2 }.count()
}

fun String.asPoint(): Point =
    split(",").map { it.toInt() }.let { Point(it.first(), it.last()) }

data class Point(val x: Int, val y: Int)

data class Line(val first: Point, val last: Point) {

    fun isHorizontal(): Boolean = first.x == last.x
    fun isVertical(): Boolean = first.y == last.y

    fun points(): Sequence<Point> {
        val dx = (last.x - first.x).coerceIn(-1, 1)
        val dy = (last.y - first.y).coerceIn(-1, 1)

        return sequence {
            var point = first
            yield(point)

            while (point != last) {
                point = Point(point.x + dx, point.y + dy)
                yield(point)
            }
        }
    }
}
