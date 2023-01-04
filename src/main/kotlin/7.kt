internal data class Directory(val name: String, val root: Directory?) {
    companion object {
        private const val FILESYSTEM_SIZE = 70000000
        private const val UPDATE_NEEDS = 30000000
    }

    val files = mutableMapOf<String, Int>()
    val subFolders = mutableMapOf<String, Directory>()
    val size: Int by lazy {
        files.values.sum() + subFolders.values.sumOf(Directory::size)
    }
    private val level: Int = (root?.level?.plus(1)) ?: 0

    fun getFoldersEligibleToDeletion(eligible: MutableSet<Directory>?): Set<Directory> {
        val local = eligible ?: mutableSetOf()
        if (isEligibleToDeletion()) local.add(this)
        subFolders.values.forEach {
            it.getFoldersEligibleToDeletion(local)
        }
        return local
    }

    fun getFolderToDeleteForUpdate(): Directory {
        val foldersWithSize = getAllFolders(null)
        val remainingSpace = FILESYSTEM_SIZE - size
        val target = UPDATE_NEEDS - remainingSpace
        
        var folderToRemove = this

        foldersWithSize.forEach {
            if (it.size - target > 0 && it.size - target < folderToRemove.size - target) {
                folderToRemove = it
            }
        }
        return folderToRemove
    }

    private fun isEligibleToDeletion(): Boolean = size <= 100000

    private fun getAllFolders(folders: MutableSet<Directory>?): Set<Directory> {
        val local = folders ?: mutableSetOf()
        local.add(this)
        subFolders.values.forEach {
            it.getAllFolders(local)
        }
        return local
    }

    override fun toString(): String {
        val sB = StringBuilder()
        val prefix = StringBuilder().apply {
            for (i in 0..level) {
                append("  ")
            }
        }.toString()
        sB.append("\n${prefix}===== Folder $name ====\n")
        files.forEach {
            sB.append("${prefix}${it.key}    ${it.value} B\n")
        }
        subFolders.forEach {
            sB.append("${prefix}${it.key}    ${it.value.size} B\n")
        }
        sB.append("${prefix}Size: $size B\n")
        subFolders.values.forEach {
            sB.append(it.toString())
        }
        sB.append("${prefix}------\n")
        return sB.toString()
    }
}

fun main() {
    val filesystem = buildFilesystem()
    println(filesystem)
    val subFoldersEligibleToDeletion = filesystem.getFoldersEligibleToDeletion(null)
    println(subFoldersEligibleToDeletion.sumOf { it.size })
}

internal fun buildFilesystem(): Directory {
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