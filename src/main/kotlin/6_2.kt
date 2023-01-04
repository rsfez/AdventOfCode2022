import java.io.File

fun main() {
    var buffer = ""
    File("inputs/6").forEachLine { buffer = it }
    val windowSet = mutableSetOf<Char>().apply { add(buffer[0]) }
    var i = 0
    var j = 1

    while (j < buffer.length) {
        if (!windowSet.contains(buffer[j])) {
            windowSet.add(buffer[j])
            if (windowSet.size >= 14) {
                println(j + 1)
                return
            }
            j++
            continue
        }
        windowSet.remove(buffer[i])
        i++
    }
}
//n p d

