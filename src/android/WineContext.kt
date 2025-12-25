package com.protonhorizon.emulator.winlator

/**
 * Оптимізований контекст для роботи з Wine/Proton через Winlator
 */
class WineContext(private val winePrefix: String) {
    
    companion object {
        const val ARCHITECTURE_32BIT = "win32"
        const val ARCHITECTURE_64BIT = "win64"
    }
    
    private var architecture = ARCHITECTURE_64BIT
    private val environment = mutableMapOf<String, String>()
    
    init {
        setupDefaultEnvironment()
    }
    
    private fun setupDefaultEnvironment() {
        environment["WINEARCH"] = architecture
        environment["WINEPREFIX"] = winePrefix
        environment["WINEDLLPATH"] = "$winePrefix/drive_c/windows/system32"
    }
    
    fun setArchitecture(arch: String) {
        if (arch in arrayOf(ARCHITECTURE_32BIT, ARCHITECTURE_64BIT)) {
            architecture = arch
            environment["WINEARCH"] = arch
        }
    }
    
    fun setEnvironmentVariable(key: String, value: String) {
        environment[key] = value
    }
    
    fun getEnvironmentVariable(key: String): String? = environment[key]
    
    fun getAllEnvironmentVariables(): Map<String, String> = environment.toMap()
    
    fun setupDXVK(dxvkPath: String) {
        environment["DXVK_HUD"] = "fps"
        environment["DXVK_LOG_LEVEL"] = "info"
    }
    
    fun setupVK3D(vk3dPath: String) {
        environment["VK3D_LOG_LEVEL"] = "info"
    }
    
    fun disableWINEDEBUG() {
        environment.remove("WINEDEBUG")
    }
    
    fun enableWINEDEBUG(debugLevel: String = "warn") {
        environment["WINEDEBUG"] = debugLevel
    }
}
