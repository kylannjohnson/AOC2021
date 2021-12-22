import kotlin.math.absoluteValue

fun main() {
    val day = "Day07"
    val part1 = 37
    val part2 = 168

    fun part1(input: List<String>): Int {
        val nums = input[0].split(",").map { it.toInt() }
        val quantity = nums.size

        val options = (0 until quantity).map { q -> nums.sumOf { n -> (n - q).absoluteValue } }

        return options.minOf { it }
    }

    fun part2(input: List<String>): Int {
        val nums = input[0].split(",").map { it.toInt() }
        val quantity = nums.maxOf { it }

        val options = (0..quantity).map { q -> nums.sumOf { n -> summationOf((n - q).absoluteValue) } }

        return options.minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    val one = part1(testInput)
    val two = part2(testInput)
    check(one == part1) { "$one != $part1" }
    check(two == part2) { "$two != $part2" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

fun summationOf(quantity: Int): Int {
    return (1..quantity).fold(0) { acc, i -> acc + i }
}

