import java.io.File
import java.util.PriorityQueue

fun main() {
    getMaxCalories()
    getMax3Calories()
}

private fun getMax3Calories() {
    val heap = PriorityQueue<Int> { c1, c2 ->
        if (c1 < c2) 1 else if (c1 > c2) -1 else 0
    }
    var calories = 0
    File("inputs/1").forEachLine {
        if (it.isNotEmpty()) {
            calories += it.toInt()
        } else {
            heap.add(calories)
            calories = 0
        }
    }
    var sum = 0
    for (i in 0..2) {
        sum += heap.poll()
    }
    println(sum)
}

private fun getMaxCalories() {
    var max = -1
    var sum = 0

    File("inputs/1").forEachLine {
        if (it.isNotEmpty()) {
            sum += it.toInt()
        } else {
            if (sum > max) {
                max = sum
            }
            sum = 0
        }
    }
    println(max)
}