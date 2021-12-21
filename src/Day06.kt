import java.util.LinkedList
import kotlin.math.pow

fun main() {
    val day = "Day06"
    val part1 = 5934
    val part2 = 26_984_457_539L

    fun part1(input: List<String>): Int {
        var fish = input[0].split(",").map { it.toInt() }

        repeat(80) {
            fish = fish.map { it - 1 }
            val additions = fish.count { it == -1 }
            fish = fish.map {
                when (it) {
                    -1 -> 6
                    else -> it
                }
            } + (0 until additions).map { 8 }

            //        println(fish.joinToString(","))
            print("$it ")
        }

        println(fish.joinToString(","))

        return fish.size
    }

    fun part2(input: List<String>): Long {

        val initialFish = input[0].split(",").map { it.toInt() }
        val fish : MutableMap<Int, Long> = (0..8).associateWith { 0L }.toMutableMap()

        initialFish.forEach {
            val f1 = fish[it]!! + 1
            fish[it] = f1
        }

        repeat(256) {
            var fishSize = fish.size
            var index = 0
            var additions = 0L

            repeat(fish.size) { index ->
                when(index) {
                    0 -> {
                        additions = fish[index]!!
                    }
                    else -> {
                        fish[index - 1] = fish[index]!!
                    }
                }
            }

            fish[6] = fish[6]!! + additions
            fish[8] = additions

            println("$it")
        }

        return fish.map { it.value }.sum().toLong()
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


class Fish(var value: Int = 8)
