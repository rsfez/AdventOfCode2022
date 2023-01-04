private data class Directory(val name: String, val root: Directory?) {
    val files = mutableMapOf<String, Int>()
    val subFolders = mutableMapOf<String, Directory>()

    fun getSize(): Int = files.values.sum() + subFolders.values.sumOf(Directory::getSize)
    fun getCleanupSize
}

fun main() {
    val filesystem = buildFilesystem()
    println(filesystem.getSize())
}

private fun buildFilesystem(): Directory {
    val filesystem = Directory(name = "disk", root = null).apply { subFolders["/"] = Directory("/", this) }
    var current = filesystem

    java.io.File("inputs/7").forEachLine { line ->
        when {
            line.startsWith("$") -> {
                val instruction = line.removePrefix("$ ")
                when {
                    instruction.startsWith("cd") -> {
                        val destination = instruction.removePrefix("cd ")
                        current = if (destination == "..") {
                            current.root!!
                        } else {
                            current.subFolders[destination]!!
                        }
                    }
//                    instruction.startsWith("ls") -> {
//
//                    }
                }
            }

            line.startsWith("dir") -> {
                val dir = line.removePrefix("dir ")
                current.subFolders[dir] = Directory(dir, current)
            }

            else -> {
                val fileInfo = line.split(" ")
                current.files[fileInfo[1]] = fileInfo[0].toInt()
            }
        }
    }
    return filesystem
}