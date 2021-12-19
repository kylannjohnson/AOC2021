fun main() {
    val day = "Day00"
    val part1 = 0
    val part2 = 0

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == part1) { "${part1(testInput)} != $part1" }
    check(part2(testInput) == part2) { "${part2(testInput)} != $part2" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

