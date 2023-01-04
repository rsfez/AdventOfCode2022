fun main() {
    val filesystem = buildFilesystem()
    println(filesystem.getFolderToDeleteForUpdate().size)
}