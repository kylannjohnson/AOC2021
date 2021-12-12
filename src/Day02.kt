fun main() {
    val day = "Day02"
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0

        input.map { line ->
            line.split(" ").map { it.trim() }
        }.forEach {
            when (it.first()) {
                "forward" -> horizontal += it[1].toInt()
                "down" -> depth += it[1].toInt()
                "up" -> depth -= it[1].toInt()
            }
        }

        return depth * horizontal
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0

        input.map { line ->
            line.split(" ").map { it.trim() }
        }.forEach {
            when (it.first()) {
                "forward" -> {
                    horizontal += it[1].toInt()
                    depth += it[1].toInt() * aim
                }
                "down" -> {
                    aim += it[1].toInt()
                }
                "up" -> {
                    aim -= it[1].toInt()
                }
            }
        }

        return depth * horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900) { "${part2(testInput)} != 900" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

