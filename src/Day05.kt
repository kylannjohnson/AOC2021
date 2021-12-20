import kotlin.math.absoluteValue

fun main() {
    val day = "Day05"
    val part1 = 5
    val part2 = 12

    fun part1(input: List<String>): Int {
        val segments = parse(input)
        val map = segments.makeMap()
        return map.flatten().count { it >= 2 }
    }

    fun part2(input: List<String>): Int {
        val segments = parse(input)
        val map = segments.makeMapDiagonal()

//        map.print()
        return map.flatten().count { it >= 2 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == part1) { "${part1(testInput)} != $part1" }
    check(part2(testInput) == part2) { "${part2(testInput)} != $part2" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

private fun List<List<Int>>.print() {
    println(joinToString("\n") {
        it.joinToString(" ")
    })
    println()
}

private fun List<Segment>.makeMap(): List<List<Int>> {
    val map = (0..1000).map {
        (0..1000).map {
            0
        }.toMutableList()
    }.toMutableList()

    filter {
        it.from.x == it.to.x || it.from.y == it.to.y
    }.forEach { segment -> // iterated over each segment
        if (segment.from.x == segment.to.x) {
            segment.yRange.forEach { y ->
                map[y][segment.from.x] = map[y][segment.from.x] + 1
            }
        } else {
            segment.xRange.forEach { x ->
                map[segment.from.y][x] = map[segment.from.y][x] + 1
            }
        }
    }

    return map
}

private fun List<Segment>.makeMapDiagonal(): List<List<Int>> {
    val map = (0..999).map {
        (0..999).map {
            0
        }.toMutableList()
    }.toMutableList()

    forEach { segment -> // iterated over each segment
        if (segment.from.x == segment.to.x) {
            segment.yRange.forEach { y ->
                map[y][segment.from.x] = map[y][segment.from.x] + 1
            }
        } else if (segment.from.y == segment.to.y) {
            segment.xRange.forEach { x ->
                map[segment.from.y][x] = map[segment.from.y][x] + 1
            }
        } else if (segment.isDiagonal()) {
            var x = segment.from.x
            var y = segment.from.y
            var xt = segment.to.x
            val yt = segment.to.y

            var points = if(yt > y) {
                (yt - y + 1).absoluteValue
            } else {
                (yt - y - 1).absoluteValue
            }

            while (points > 0) {
                map[y][x] = map[y][x] + 1
                y += segment.yStep
                x += segment.xStep

                points--
            }
        } else {
            println("$segment")
        }
    }

    return map
}

fun parse(input: List<String>): List<Segment> {
    return input.map { line ->
        val c = line.split(" -> ")
        val c1 = c[0].split(",")
        val c2 = c[1].split(",")
        val p1 = Point(c1[0].toInt(), c1[1].toInt())
        val p2 = Point(c2[0].toInt(), c2[1].toInt())
        Segment(p1, p2)
    }
}

class Segment(val from: Point, val to: Point) {

    val xRange: IntRange = if (from.x < to.x) {
        (from.x..to.x)
    } else {
        (to.x..from.x)
    }

    val yRange: IntRange = if (from.y < to.y) {
        (from.y..to.y)
    } else {
        (to.y..from.y)
    }

    val xStep = if (from.x < to.x) {
        xRange.step
    } else {
        xRange.step * -1
    }

    val yStep = if (from.y < to.y) {
        yRange.step
    } else {
        yRange.step * -1
    }

    override fun toString(): String {
        return "${from.x}, ${from.y} -> ${to.x}, ${to.y}"
    }

    fun isDiagonal(): Boolean {
        return (from.x - to.x).absoluteValue == (from.y - to.y).absoluteValue
    }
}

class Point(val x: Int, val y: Int)

