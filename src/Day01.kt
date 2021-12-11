fun main() {
    fun part1(input: List<String>): Int {

        var x = 0
        var y = 1
        var increases = 0
        val inputAsNumbers = input.map { it.toInt() }

        while (y < input.size) {
            if (inputAsNumbers[y] > inputAsNumbers[x]) {
                ++increases
            }
            x++; y++
        }

        return increases
    }

    fun part2(input: List<String>): Int {
        var x1 = 0
        var x2 = 1
        var x3 = 2

        var y1 = 1
        var y2 = 2
        var y3 = 3

        var increases = 0
        val inputAsNumbers = input.map { it.toInt() }

        while (y3 < input.size) {
            val window1 = listOf(x1, x2, x3)
            val sum1 = if (window1.all { 0 <= it && it < input.size }) {
                inputAsNumbers[x1] + inputAsNumbers[x2] + inputAsNumbers[x3]
            } else 0

            val window2 = listOf(y1, y2, y3)
            val sum2 = if (window2.all { 0 <= it && it < input.size }) {
                inputAsNumbers[y1] + inputAsNumbers[y2] + inputAsNumbers[y3]
            } else 0

            if (sum2 > sum1) {
                increases++
            }

            ++x1 % input.size
            ++x2 % input.size
            ++x3 % input.size
            ++y1 % input.size
            ++y2 % input.size
            ++y3 % input.size
        }

        return increases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5) { "${part2(testInput)} != 5" }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
