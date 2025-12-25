package com.protonhorizon.emulator

import kotlinx.coroutines.*
import java.io.File
import java.net.URL
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Менеджер для завантаження компонентів з інтернету
 */
class DownloadManager(private val basePath: String) {
    
    companion object {
        private const val DOWNLOAD_CHUNK_SIZE = 1024 * 1024 // 1MB
        
        private val componentUrls = mapOf(
            "proton" to "https://github.com/GloriousEggroll/proton-ge-custom/releases/download/",
            "dxvk" to "https://github.com/doitsujin/dxvk/releases/download/",
            "vk3d" to "https://github.com/HansKristian-Work/vkd3d-proton/releases/download/",
            "wine" to "https://github.com/Kron4ek/Wine-Builds/releases/download/"
        )
    }
    
    private val httpClient = OkHttpClient()
    
    suspend fun downloadComponent(
        componentName: String,
        version: String,
        onProgress: (progress: Int) -> Unit = {}
    ): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val url = getDownloadUrl(componentName, version)
            val destination = File(basePath, "$componentName-$version.tar.gz")
            
            val request = Request.Builder()
                .url(url)
                .build()
            
            val response = httpClient.newCall(request).execute()
            if (!response.isSuccessful) return@withContext false
            
            val totalSize = response.body?.contentLength() ?: 0
            var downloadedSize = 0L
            
            response.body?.let { body ->
                destination.outputStream().use { fileOut ->
                    body.byteStream().use { inputStream ->
                        val buffer = ByteArray(DOWNLOAD_CHUNK_SIZE)
                        var bytesRead: Int
                        
                        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                            fileOut.write(buffer, 0, bytesRead)
                            downloadedSize += bytesRead
                            
                            if (totalSize > 0) {
                                val progress = (downloadedSize * 100 / totalSize).toInt()
                                onProgress(progress)
                            }
                        }
                    }
                }
            }
            
            true
        } catch (e: Exception) {
            false
        }
    }
    
    private fun getDownloadUrl(componentName: String, version: String): String {
        return when (componentName.lowercase()) {
            "proton" -> "${componentUrls["proton"]}${version}/Proton-${version}.tar.gz"
            "dxvk" -> "${componentUrls["dxvk"]}${version}/dxvk-${version}.tar.gz"
            "vk3d" -> "${componentUrls["vk3d"]}${version}/vkd3d-proton-${version}.tar.gz"
            "wine" -> "${componentUrls["wine"]}${version}/wine-${version}.tar.gz"
            else -> ""
        }
    }
    
    suspend fun extractComponent(
        componentName: String,
        version: String,
        extractPath: String
    ): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val archive = File(basePath, "$componentName-$version.tar.gz")
            if (!archive.exists()) return@withContext false
            
            // Розпакування архіву
            val processBuilder = ProcessBuilder(
                "tar", "-xzf", archive.absolutePath, "-C", extractPath
            )
            val process = processBuilder.start()
            process.waitFor()
            
            archive.delete() // Видалення архіву після розпакування
            process.exitValue() == 0
        } catch (e: Exception) {
            false
        }
    }
}
