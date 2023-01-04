import java.io.File

private val RESULTS_POINT = mapOf(Pair('X', 0), Pair('Y', 3), Pair('Z', 6))
private const val ROCK_POINTS = 1
private const val PAPER_POINTS = 2
private const val SCISSORS_POINTS = 3
private val COMBINATIONS = mapOf(
    Pair('X', mapOf(Pair('A', SCISSORS_POINTS), Pair('B', ROCK_POINTS), Pair('C', PAPER_POINTS))),
    Pair('Y', mapOf(Pair('A', ROCK_POINTS), Pair('B', PAPER_POINTS), Pair('C', SCISSORS_POINTS))),
    Pair('Z', mapOf(Pair('A', PAPER_POINTS), Pair('B', SCISSORS_POINTS), Pair('C', ROCK_POINTS)))
)

fun main() {
    getTotalPoints()
}

private fun getTotalPoints() {
    var totalPoints = 0
    File("inputs/2").forEachLine {
        val p1 = it[0]
        val r = it[2]
        totalPoints += RESULTS_POINT[r]!! + COMBINATIONS[r]!![p1]!!
    }
    println(totalPoints)
}