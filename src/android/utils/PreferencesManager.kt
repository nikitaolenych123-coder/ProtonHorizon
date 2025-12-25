package com.protonhorizon.emulator.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Менеджер для управління налаштуваннями емулятора
 */
class PreferencesManager(context: Context) {
    
    companion object {
        const val PREFS_NAME = "proton_horizon_prefs"
        
        // Graphics settings
        const val GRAPHICS_API = "graphics_api"
        const val RESOLUTION_WIDTH = "resolution_width"
        const val RESOLUTION_HEIGHT = "resolution_height"
        const val ENABLE_VSYNC = "enable_vsync"
        const val TEXTURE_QUALITY = "texture_quality"
        
        // Audio settings
        const val ENABLE_AUDIO = "enable_audio"
        const val AUDIO_LATENCY = "audio_latency"
        
        // Performance settings
        const val ENABLE_TURBO = "enable_turbo"
        const val CPU_CORES = "cpu_cores"
        const val MEMORY_ALLOCATION = "memory_allocation"
        
        // Game settings
        const val LAST_GAME_PATH = "last_game_path"
        const val AUTO_LOAD_LAST_GAME = "auto_load_last_game"
        
        // Gamepad settings
        const val GAMEPAD_VIBRATION = "gamepad_vibration"
        const val GAMEPAD_DEADZONE = "gamepad_deadzone"
        
        // Default values
        const val DEFAULT_API = "vulkan"
        const val DEFAULT_WIDTH = 1280
        const val DEFAULT_HEIGHT = 720
        const val DEFAULT_VSYNC = true
        const val DEFAULT_TEXTURE_QUALITY = 1f
    }
    
    private val prefs: SharedPreferences = 
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    fun getGraphicsAPI(): String {
        return prefs.getString(GRAPHICS_API, DEFAULT_API) ?: DEFAULT_API
    }
    
    fun setGraphicsAPI(api: String) {
        prefs.edit().putString(GRAPHICS_API, api).apply()
    }
    
    fun getResolutionWidth(): Int {
        return prefs.getInt(RESOLUTION_WIDTH, DEFAULT_WIDTH)
    }
    
    fun setResolutionWidth(width: Int) {
        prefs.edit().putInt(RESOLUTION_WIDTH, width).apply()
    }
    
    fun getResolutionHeight(): Int {
        return prefs.getInt(RESOLUTION_HEIGHT, DEFAULT_HEIGHT)
    }
    
    fun setResolutionHeight(height: Int) {
        prefs.edit().putInt(RESOLUTION_HEIGHT, height).apply()
    }
    
    fun isVSyncEnabled(): Boolean {
        return prefs.getBoolean(ENABLE_VSYNC, DEFAULT_VSYNC)
    }
    
    fun setVSyncEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(ENABLE_VSYNC, enabled).apply()
    }
    
    fun getTextureQuality(): Float {
        return prefs.getFloat(TEXTURE_QUALITY, DEFAULT_TEXTURE_QUALITY)
    }
    
    fun setTextureQuality(quality: Float) {
        prefs.edit().putFloat(TEXTURE_QUALITY, quality).apply()
    }
    
    fun isAudioEnabled(): Boolean {
        return prefs.getBoolean(ENABLE_AUDIO, true)
    }
    
    fun setAudioEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(ENABLE_AUDIO, enabled).apply()
    }
    
    fun getLastGamePath(): String {
        return prefs.getString(LAST_GAME_PATH, "") ?: ""
    }
    
    fun setLastGamePath(path: String) {
        prefs.edit().putString(LAST_GAME_PATH, path).apply()
    }
    
    fun isAutoLoadLastGame(): Boolean {
        return prefs.getBoolean(AUTO_LOAD_LAST_GAME, false)
    }
    
    fun setAutoLoadLastGame(enabled: Boolean) {
        prefs.edit().putBoolean(AUTO_LOAD_LAST_GAME, enabled).apply()
    }
    
    fun isGamepadVibrationEnabled(): Boolean {
        return prefs.getBoolean(GAMEPAD_VIBRATION, true)
    }
    
    fun setGamepadVibrationEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(GAMEPAD_VIBRATION, enabled).apply()
    }
    
    fun getGamepadDeadzone(): Float {
        return prefs.getFloat(GAMEPAD_DEADZONE, 0.15f)
    }
    
    fun setGamepadDeadzone(deadzone: Float) {
        prefs.edit().putFloat(GAMEPAD_DEADZONE, deadzone).apply()
    }
}
