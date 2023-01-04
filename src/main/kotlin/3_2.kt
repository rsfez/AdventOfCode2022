import java.io.File

fun main() {
    val nbElvesPerGroup = 3
    var prioritySum = 0
    var currentGroup = 0
    var currentElf = 0
    val prioOccurrence = mutableMapOf<Int, Int>().withDefault { 0 }
    File("inputs/3").forEachLine { line ->
        line.toSet().map(Char::getPriority).forEach { prio ->
            prioOccurrence[prio] = prioOccurrence.getValue(prio) + 1
            if (prioOccurrence.getValue(prio) >= nbElvesPerGroup) {
                prioritySum += prio
            }
        }

        if (++currentElf >= nbElvesPerGroup) {
            currentGroup++
            currentElf = 0
            prioOccurrence.clear()
        }
    }

    println(prioritySum)
}

private fun Char.getPriority() = if (isLowerCase()) code - 'a'.code + 1
else code - 'A'.code + 27