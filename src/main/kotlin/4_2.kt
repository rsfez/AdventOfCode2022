import java.io.File

fun main() {
    data class Sector(val lower: Int, val upper: Int) {
        fun overlapsWith(other: Sector): Boolean =
            (this.lower >= other.lower && this.lower <= other.upper) || (this.upper <= other.upper && this.upper >= other.lower)
    }

    var overlaps = 0
    File("inputs/4").forEachLine { line ->
        val sectors = line.split(',')
            .map { str -> str.split('-') }
            .map { bounds -> Sector(bounds[0].toInt(), bounds[1].toInt()) }
        val sector1 = sectors[0]
        val sector2 = sectors[1]
        if (sector1.overlapsWith(sector2) || sector2.overlapsWith(sector1)) {
            overlaps++
        }
    }
    println(overlaps)
}