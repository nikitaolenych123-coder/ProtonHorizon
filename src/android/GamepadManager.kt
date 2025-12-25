package com.protonhorizon.emulator

/**
 * Основний класс для управління підтримкою геймпадів
 */
class GamepadManager {
    companion object {
        const val GAMEPAD_XBOX = 0x01
        const val GAMEPAD_PS = 0x02
        const val GAMEPAD_GENERIC = 0x04
        const val GAMEPAD_JOYCON = 0x08
        const val GAMEPAD_STEAM = 0x10
    }
    
    private val connectedGamepads = mutableMapOf<Int, GamepadDevice>()
    private val listeners = mutableListOf<GamepadListener>()
    
    fun registerGamepad(deviceId: Int, gamepad: GamepadDevice) {
        connectedGamepads[deviceId] = gamepad
        notifyGamepadConnected(deviceId, gamepad)
    }
    
    fun unregisterGamepad(deviceId: Int) {
        connectedGamepads.remove(deviceId)?.let {
            notifyGamepadDisconnected(deviceId)
        }
    }
    
    fun getGamepad(deviceId: Int): GamepadDevice? = connectedGamepads[deviceId]
    
    fun getConnectedGamepads(): List<GamepadDevice> = connectedGamepads.values.toList()
    
    fun addListener(listener: GamepadListener) {
        listeners.add(listener)
    }
    
    fun removeListener(listener: GamepadListener) {
        listeners.remove(listener)
    }
    
    private fun notifyGamepadConnected(deviceId: Int, gamepad: GamepadDevice) {
        listeners.forEach { it.onGamepadConnected(deviceId, gamepad) }
    }
    
    private fun notifyGamepadDisconnected(deviceId: Int) {
        listeners.forEach { it.onGamepadDisconnected(deviceId) }
    }
    
    fun processButtonInput(deviceId: Int, buttonId: Int, pressed: Boolean) {
        listeners.forEach { it.onButtonInput(deviceId, buttonId, pressed) }
    }
    
    fun processAxisInput(deviceId: Int, axisId: Int, value: Float) {
        listeners.forEach { it.onAxisInput(deviceId, axisId, value) }
    }
}

data class GamepadDevice(
    val deviceId: Int,
    val name: String,
    val type: Int,
    val vendorId: Int,
    val productId: Int
)

interface GamepadListener {
    fun onGamepadConnected(deviceId: Int, gamepad: GamepadDevice)
    fun onGamepadDisconnected(deviceId: Int)
    fun onButtonInput(deviceId: Int, buttonId: Int, pressed: Boolean)
    fun onAxisInput(deviceId: Int, axisId: Int, value: Float)
}
