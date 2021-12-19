import Game.Companion.parse

fun main() {
    val day = "Day04"
    val part1 = 4512
    val part2 = 1924

    fun part1(input: List<String>): Int {
        val game = parse(input)

        val winner = game.play().firstOrNull() ?: error("no Winner!")

        return winner.winningNumber * winner.board.allUnmarked().sumOf { it.value }
    }

    fun part2(input: List<String>): Int {
        val game = parse(input)

        val winner = game.play().lastOrNull() ?: error("no Winner!")

        return winner.winningNumber * winner.board.allUnmarked().sumOf { it.value }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == part1) { "${part1(testInput)} != $part1" }
    check(part2(testInput) == part2) { "${part2(testInput)} != $part2" }

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

class Game private constructor(private val numbers: List<Int>, private val boards: List<Board>) {

    fun play(): List<Winner> {

        val winners = mutableListOf<Winner>()

        val remainingBoards = boards.toMutableList()

        numbers.forEach { n ->
            remainingBoards.filter { !it.hasWon }.forEach { b ->
                b.mark(n)
            }

            remainingBoards.filter { !it.hasWon }.forEach { b ->
                if (b.hasWinner()) {
                    b.hasWon = true
                    winners.add(Winner(b, n))
                }
            }
        }

        return winners
    }

    companion object {
        fun parse(input: List<String>): Game {
            val numbers = input[0].split(",").map { it.toInt() }

            var start = 2
            var end = start + 4
            val boards = mutableListOf<Board>()

            while (end < input.size) {

                val boardRows = mutableListOf<List<Space>>()

                input.subList(start, end + 1).map { row ->
                    val reg = "([0-9])+\\b".toRegex()
                    val boardRow = reg.findAll(row.trim()).map { r ->
                        Space(r.groupValues.first().toInt())
                    }.toList()

                    boardRows.add(boardRow)
                }

                boards.add(Board(boardRows))

                start += 6
                end = start + 4
            }

            return Game(numbers, boards)
        }
    }
}

class Winner(val board: Board, val winningNumber: Int)

class Space(val value: Int, var marked: Boolean = false) {
    override fun toString(): String {
        return value.toString() + (if (marked) "+" else "")
    }
}

class Board(val values: List<List<Space>>, var hasWon : Boolean = false) {
    fun allUnmarked(): List<Space> {
        return values.flatten().filter { !it.marked }
    }

    fun mark(n: Int) {
        values.forEach { row ->
            row.forEach {
                if (it.value == n) {
                    it.marked = true
                }
            }
        }
    }

    fun hasWinner(): Boolean {
        val rowIsMarked = values.any { it.all { s -> s.marked } }
        var columnIsMarked = false

        (0 until 5).forEach { col ->
            val column = values.map { it[col] }
            if (column.all { it.marked }) {
                columnIsMarked = true
            }
        }

        return rowIsMarked || columnIsMarked
    }

    override fun toString(): String {
        return values.joinToString("\n")
    }
}

