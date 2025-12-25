package com.protonhorizon.emulator

import android.content.Context
import android.view.MotionEvent
import android.view.KeyEvent

/**
 * Головний клас для управління емулятором
 */
class EmulatorEngine(private val context: Context) {
    
    private val gamepadManager = GamepadManager()
    private val componentManager = ComponentManager()
    private var isRunning = false
    
    companion object {
        init {
            System.loadLibrary("proton_horizon")
        }
    }
    
    // JNI methods
    external fun initializeEmulator(): Boolean
    external fun shutdownEmulator()
    external fun loadGame(gamePath: String): Boolean
    external fun runEmulationFrame()
    external fun handleInput(inputType: Int, data: IntArray): Boolean
    external fun setGraphicsAPI(api: String): Boolean
    
    fun start(): Boolean {
        return if (initializeEmulator()) {
            isRunning = true
            true
        } else {
            false
        }
    }
    
    fun stop() {
        if (isRunning) {
            shutdownEmulator()
            isRunning = false
        }
    }
    
    fun loadGame(gamePath: String): Boolean = loadGame(gamePath)
    
    fun step() {
        if (isRunning) {
            runEmulationFrame()
        }
    }
    
    fun getGamepadManager(): GamepadManager = gamepadManager
    
    fun getComponentManager(): ComponentManager = componentManager
    
    fun setGraphicsAPI(api: String): Boolean = setGraphicsAPI(api)
    
    fun handleMotionEvent(event: MotionEvent): Boolean {
        // Обробка рухів геймпада
        val inputData = IntArray(10) { 0 }
        return handleInput(INPUT_TYPE_MOTION, inputData)
    }
    
    fun handleKeyEvent(event: KeyEvent): Boolean {
        // Обробка натискань клавіш
        val inputData = intArrayOf(event.keyCode, if (event.action == KeyEvent.ACTION_DOWN) 1 else 0)
        return handleInput(INPUT_TYPE_KEY, inputData)
    }
    
    private companion object {
        const val INPUT_TYPE_KEY = 0
        const val INPUT_TYPE_MOTION = 1
    }
}
