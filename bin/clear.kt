import java.io.File

fun main() {
    // Obtener el directorio actual
    val dirActual = File(System.getProperty("user.dir"))

    // Función para recorrer directorios y subdirectorios
    fun recorrerDirectorios(directorio: File) {
        val archivos = directorio.listFiles()
        if (archivos != null) {
            for (archivo in archivos) {
                if (archivo.isDirectory) {
                    // Recursivamente recorrer subdirectorios
                    recorrerDirectorios(archivo)
                } else if (archivo.extension == "ini") {
                    // Intentar borrar el archivo
                    try {
                        if (archivo.delete()) {
                            println("Archivo borrado: ${archivo.absolutePath}")
                        } else {
                            println("No se pudo borrar el archivo: ${archivo.absolutePath}")
                        }
                    } catch (e: Exception) {
                        println("No se pudo borrar el archivo ${archivo.absolutePath}: ${e.message}")
                    }
                }
            }
        }
    }

    // Iniciar el recorrido desde el directorio actual
    recorrerDirectorios(dirActual)
}
