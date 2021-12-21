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
    val one = part1(testInput)
    val two = part2(testInput)
    check(one == part1) { "$one != $part1" }
    check(two == part2) { "$two != $part2" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

