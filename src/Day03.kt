import kotlin.math.pow

fun toDecimal(binary: String): Int {
    var position = 0
    return binary.reversed().toCharArray().map { it.digitToInt() }.fold(0.toDouble()) { acc, c ->
        val d = c.toDouble()
        acc + (d * 2.toDouble().pow(position++))
    }.toInt()
}


fun main() {
    val day = "Day03"


    fun part1(input: List<String>): Int {
        val columns = mutableListOf<MutableList<Char>>()
        input.forEach { line ->
            line.toCharArray().forEachIndexed { index, c ->
                if (index < columns.size) {
                    columns[index].add(c)
                } else {
                    columns.add(mutableListOf(c))
                }
            }
        }

        val gamma = columns.fold("") { acc, column ->
            val numOfOnes = column.fold(0) { count, c -> if (c == '1') count + 1 else count }
            acc + if (numOfOnes > column.size / 2) {
                "1"
            } else "0"
        }

        val epsilon = columns.fold("") { acc, column ->
            val numOfOnes = column.fold(0) { count, c -> if (c == '1') count + 1 else count }
            acc + if (numOfOnes <= column.size / 2) {
                "1"
            } else "0"
        }

        return toDecimal(gamma) * toDecimal(epsilon)
    }

    fun asColumns(input: List<String>): List<List<Char>> {
        val columns = mutableListOf<MutableList<Char>>()
        input.forEach { line ->
            line.toCharArray().forEachIndexed { index, c ->
                if (index < columns.size) {
                    columns[index].add(c)
                } else {
                    columns.add(mutableListOf(c))
                }
            }
        }
        return columns
    }

    fun calculate(input: List<String>, shouldPickOnes: (Int, Int) -> Boolean): String {
        var foo: List<String> = input.toList()
        var index = 0

        while(foo.size > 1) {
            val column: List<Char> = asColumns(foo)[index]

            val ones = column.filter { it == '1' }.size
            val zeros = column.filter { it == '0' }.size

            foo = if (shouldPickOnes(ones, zeros)) {
                foo.filter { it[index] == '1' }
            } else {
                foo.filter { it[index] == '0' }
            }
            ++index
        }

        return foo[0]
    }

    fun part2(input: List<String>): Int {

        val oxygen = calculate(input) { ones, zeros -> ones >= zeros }
        val co2 = calculate(input) { ones, zeros -> ones < zeros }


        return toDecimal(oxygen) * toDecimal(co2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230) { "${part2(testInput)} != 230" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

