import java.io.File

private val SYMBOL_POINT = mapOf(Pair('X', 1), Pair('Y', 2), Pair('Z', 3))
private const val WIN = 6
private const val DRAW = 3
private const val LOSS = 0
private val COMBINATIONS = mapOf(
    Pair('X', mapOf(Pair('A', DRAW), Pair('B', LOSS), Pair('C', WIN))),
    Pair('Y', mapOf(Pair('A', WIN), Pair('B', DRAW), Pair('C', LOSS))),
    Pair('Z', mapOf(Pair('A', LOSS), Pair('B', WIN), Pair('C', DRAW)))
)

fun main() {
    getTotalPoints()
}

private fun getTotalPoints() {
    var totalPoints = 0
    File("inputs/2").forEachLine {
        val p1 = it[0]
        val p2 = it[2]
        totalPoints += SYMBOL_POINT[p2]!! + COMBINATIONS[p2]!![p1]!!
    }
    println(totalPoints)
}