import java.io.File

fun main() {
    data class Sector(val lower: Int, val upper: Int) {
        fun belongsTo(other: Sector): Boolean = this.lower >= other.lower && this.upper <= other.upper
    }

    var overlaps = 0
    File("inputs/4").forEachLine { line ->
        val sectors = line.split(',')
            .map { str -> str.split('-') }
            .map { bounds -> Sector(bounds[0].toInt(), bounds[1].toInt()) }
        val sector1 = sectors[0]
        val sector2 = sectors[1]
        if (sector1.belongsTo(sector2) || sector2.belongsTo(sector1)) {
            overlaps++
        }
    }
    println(overlaps)
}