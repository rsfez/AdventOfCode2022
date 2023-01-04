import java.io.File

fun main() {
    var prioritySum = 0

    File("inputs/3").forEachLine { line ->
        val compartment1 = line.subSequence(0, line.length / 2)
        val compartment2 = line.subSequence(line.length / 2, line.length)
        val prioArray = IntArray('Z'.code + 27)
        compartment1.toSet().map(Char::getPriority).forEach {
            prioArray[it] = prioArray[it] + 1
        }
        compartment2.toSet().map(Char::getPriority).forEach {
            prioArray[it] = prioArray[it] + 1
            if (prioArray[it] > 1) {
                prioritySum += it
                return@forEach
            }
        }
    }

    println(prioritySum)
}

private fun Char.getPriority() = if (isLowerCase()) code - 'a'.code + 1
else code - 'A'.code + 27