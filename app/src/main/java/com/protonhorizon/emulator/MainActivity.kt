package com.protonhorizon.emulator

import android.app.Activity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton = findViewById<Button>(R.id.btn_start)
        startButton.setOnClickListener { Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show() }
    }
}
package com.protonhorizon.emulator

import android.app.Activity
import android.os.Bundle
import android.widget.*

/**
 * Головна активність для Android інтерфейсу
 */
class MainActivity : Activity() {
    
    private lateinit var engine: EmulatorEngine
    private lateinit var surfaceView: SurfaceView
    private lateinit var gamepadIndicator: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        engine = EmulatorEngine(this)
        surfaceView = findViewById(R.id.game_surface)
        gamepadIndicator = findViewById(R.id.gamepad_indicator)
        
        setupUI()
        initializeEmulator()
    }
    
    private fun setupUI() {
        // Налаштування кнопок для управління
        val startButton = findViewById<Button>(R.id.btn_start)
        val stopButton = findViewById<Button>(R.id.btn_stop)
        val settingsButton = findViewById<Button>(R.id.btn_settings)
        
        startButton?.setOnClickListener { startEmulation() }
        stopButton?.setOnClickListener { stopEmulation() }
        settingsButton?.setOnClickListener { showSettings() }
    }
    
    private fun initializeEmulator() {
        if (engine.start()) {
            showToast("Емулятор ініціалізований")
        } else {
            showToast("Помилка при ініціалізації")
        }
    }
    
    private fun startEmulation() {
        // Завантаження гри
        val gamePath = "/sdcard/Games/game.iso"
        if (engine.loadGame(gamePath)) {
            showToast("Гра завантажена")
        }
    }
    
    private fun stopEmulation() {
        engine.stop()
        showToast("Емуляція зупинена")
    }
    
    private fun showSettings() {
        val intent = android.content.Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
    
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onDestroy() {
        engine.stop()
        super.onDestroy()
    }
}

// Активність для налаштувань
class SettingsActivity : Activity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Налаштування інтерфейсу
    }
}
