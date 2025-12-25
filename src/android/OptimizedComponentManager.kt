package com.protonhorizon.emulator

import kotlinx.coroutines.*
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Менеджер для паралельного завантаження і управління компонентами
 * З оптимізацією для мобільних пристроїв
 */
class OptimizedComponentManager(private val basePath: String) {
    
    private val downloadManager = DownloadManager(basePath)
    private val downloadJobs = ConcurrentHashMap<String, Job>()
    private val installedComponents = mutableMapOf<String, ComponentInfo>()
    
    data class ComponentInfo(
        val name: String,
        val version: String,
        val path: String,
        val size: Long,
        val installed: Boolean,
        val downloadProgress: Int = 0
    )
    
    /**
     * Асинхронне завантаження компонента з оновленням прогресу
     */
    suspend fun downloadComponentAsync(
        componentName: String,
        version: String,
        onProgress: (Int) -> Unit = {},
        scope: CoroutineScope = GlobalScope
    ) {
        val job = scope.async(Dispatchers.IO) {
            try {
                downloadManager.downloadComponent(componentName, version) { progress ->
                    onProgress(progress)
                }
                
                // Розпакування компонента
                val extractPath = File(basePath, componentName)
                extractPath.mkdirs()
                
                downloadManager.extractComponent(componentName, version, extractPath.absolutePath)
                
                // Обновлення інформації про компонент
                installedComponents[componentName] = ComponentInfo(
                    name = componentName,
                    version = version,
                    path = extractPath.absolutePath,
                    size = calculateSize(extractPath),
                    installed = true
                )
            } catch (e: Exception) {
                // Log error
            }
        }
        
        downloadJobs[componentName] = job
        job.await()
    }
    
    /**
     * Отримання списку доступних для завантаження компонентів
     */
    fun getAvailableComponents(): List<String> {
        return listOf(
            "proton-ge",
            "proton",
            "wine-stable",
            "wine-staging",
            "dxvk",
            "vk3d",
            "vulkan-loader"
        )
    }
    
    /**
     * Отримання розміру директорії
     */
    private fun calculateSize(file: File): Long {
        return if (file.isDirectory) {
            file.listFiles()?.fold(0L) { acc, f -> acc + calculateSize(f) } ?: 0L
        } else {
            file.length()
        }
    }
    
    /**
     * Відновлення завантаження если воно було перервано
     */
    suspend fun resumeDownload(componentName: String, onProgress: (Int) -> Unit = {}) {
        val existingJob = downloadJobs[componentName]
        if (existingJob?.isActive == true) {
            existingJob.join()
        } else {
            downloadComponentAsync(componentName, "latest", onProgress)
        }
    }
    
    /**
     * Перевірка наявності компонента
     */
    fun hasComponent(componentName: String): Boolean {
        return installedComponents[componentName]?.installed == true
    }
    
    /**
     * Видалення компонента
     */
    fun removeComponent(componentName: String): Boolean {
        val component = installedComponents[componentName] ?: return false
        val componentDir = File(component.path)
        
        return try {
            componentDir.deleteRecursively()
            installedComponents.remove(componentName)
            true
        } catch (e: Exception) {
            false
        }
    }
}
