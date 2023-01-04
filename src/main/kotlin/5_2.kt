import java.io.File

/*
[Q]         [N]             [N]
[H]     [B] [D]             [S] [M]
[C]     [Q] [J]         [V] [Q] [D]
[T]     [S] [Z] [F]     [J] [J] [W]
[N] [G] [T] [S] [V]     [B] [C] [C]
[S] [B] [R] [W] [D] [J] [Q] [R] [Q]
[V] [D] [W] [G] [P] [W] [N] [T] [S]
[B] [W] [F] [L] [M] [F] [L] [G] [J]
 1   2   3   4   5   6   7   8   9
 */

fun main() {
    val warehouse = getWarehouse()
    val instructions = getInstructions()

    instructions.forEach { instruction ->
        val quantity = instruction[0].toInt()
        val from = instruction[1].toInt() - 1
        val to = instruction[2].toInt() - 1
        val crane = ArrayDeque<String>()

        for (i in 0 until quantity) {
            val tail = warehouse[from].removeFirst()
            crane.addLast(tail)
        }

        while (crane.isNotEmpty()) {
            warehouse[to].addFirst(crane.removeLast())
        }
    }
    val stringBuilder = StringBuilder()
    warehouse.forEach { stack ->
        stringBuilder.append(stack.first())
    }
    println(stringBuilder.toString())
}

private fun getInstructions(): MutableList<List<String>> {
    val instructions = mutableListOf<List<String>>()

    File("inputs/5").forEachLine { line ->
        line.split(' ').filter {
            it.matches(Regex("\\d+"))
        }.let(instructions::add)
    }
    return instructions
}

private fun getWarehouse(): MutableList<ArrayDeque<String>> {
    val nine = ArrayDeque<String>().apply {
        addLast("M")
        addLast("D")
        addLast("W")
        addLast("C")
        addLast("Q")
        addLast("S")
        addLast("J")
    }
    val eight = ArrayDeque<String>().apply {
        addLast("N")
        addLast("S")
        addLast("Q")
        addLast("J")
        addLast("C")
        addLast("R")
        addLast("T")
        addLast("G")
    }
    val seven = ArrayDeque<String>().apply {
        addLast("V")
        addLast("J")
        addLast("B")
        addLast("Q")
        addLast("N")
        addLast("L")
    }
    val six = ArrayDeque<String>().apply {
        addLast("J")
        addLast("W")
        addLast("F")
    }
    val five = ArrayDeque<String>().apply {
        addLast("F")
        addLast("V")
        addLast("D")
        addLast("P")
        addLast("M")
    }
    val four = ArrayDeque<String>().apply {
        addLast("N")
        addLast("D")
        addLast("J")
        addLast("Z")
        addLast("S")
        addLast("W")
        addLast("G")
        addLast("L")
    }
    val three = ArrayDeque<String>().apply {
        addLast("B")
        addLast("Q")
        addLast("S")
        addLast("T")
        addLast("R")
        addLast("W")
        addLast("F")
    }
    val one = ArrayDeque<String>().apply {
        addLast("Q")
        addLast("H")
        addLast("C")
        addLast("T")
        addLast("N")
        addLast("S")
        addLast("V")
        addLast("B")
    }
    val two = ArrayDeque<String>().apply {
        addLast("G")
        addLast("B")
        addLast("D")
        addLast("W")
    }
    return mutableListOf<ArrayDeque<String>>().apply {
        add(one)
        add(two)
        add(three)
        add(four)
        add(five)
        add(six)
        add(seven)
        add(eight)
        add(nine)
    }
}