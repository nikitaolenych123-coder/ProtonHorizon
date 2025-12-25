package com.protonhorizon.emulator

/**
 * Версійна інформація
 */
object Version {
    const val MAJOR = 1
    const val MINOR = 0
    const val PATCH = 0
    const val BUILD = 1
    
    val VERSION_NAME = "$MAJOR.$MINOR.$PATCH"
    val VERSION_CODE = MAJOR * 10000 + MINOR * 100 + PATCH * 10 + BUILD
    
    const val BUILD_DATE = "2025-12-25"
    const val DEVELOPER = "ProtonHorizon Team"
    const val REPOSITORY = "https://github.com/your-repo/ProtonHorizon"
    
    // Component versions
    const val PROTON_VERSION = "9.0"
    const val WINE_VERSION = "9.0"
    const val DXVK_VERSION = "2.3"
    const val VK3D_VERSION = "1.8"
    const val VULKAN_VERSION = "1.3"
}
