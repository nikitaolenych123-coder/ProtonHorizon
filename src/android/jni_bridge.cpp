#include <jni.h>
#include <android/log.h>
#include <string>

#include "emulator.h"

#define LOG_TAG "ProtonHorizon-JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

static Emulator* g_emulator = nullptr;

extern "C" {

JNIEXPORT jboolean JNICALL
Java_com_protonhorizon_emulator_EmulatorEngine_initializeEmulator(JNIEnv* env, jobject obj) {
    LOGI("JNI: initializeEmulator called");
    
    if (!g_emulator) {
        g_emulator = new Emulator();
    }
    
    return g_emulator->Initialize() ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL
Java_com_protonhorizon_emulator_EmulatorEngine_shutdownEmulator(JNIEnv* env, jobject obj) {
    LOGI("JNI: shutdownEmulator called");
    
    if (g_emulator) {
        g_emulator->Shutdown();
        delete g_emulator;
        g_emulator = nullptr;
    }
}

JNIEXPORT jboolean JNICALL
Java_com_protonhorizon_emulator_EmulatorEngine_loadGame(JNIEnv* env, jobject obj, jstring gamePath) {
    LOGI("JNI: loadGame called");
    
    if (!g_emulator) return JNI_FALSE;
    
    const char* path = env->GetStringUTFChars(gamePath, nullptr);
    bool result = g_emulator->LoadRom(path);
    env->ReleaseStringUTFChars(gamePath, path);
    
    return result ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL
Java_com_protonhorizon_emulator_EmulatorEngine_runEmulationFrame(JNIEnv* env, jobject obj) {
    if (g_emulator) {
        g_emulator->RunFrame();
    }
}

JNIEXPORT jboolean JNICALL
Java_com_protonhorizon_emulator_EmulatorEngine_handleInput(JNIEnv* env, jobject obj, 
                                                           jint inputType, jintArray data) {
    LOGI("JNI: handleInput called");
    
    if (!g_emulator || !data) return JNI_FALSE;
    
    jint* arr = env->GetIntArrayElements(data, nullptr);
    jsize len = env->GetArrayLength(data);
    
    g_emulator->ProcessInput(inputType, arr, len);
    
    env->ReleaseIntArrayElements(data, arr, 0);
    
    return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL
Java_com_protonhorizon_emulator_EmulatorEngine_setGraphicsAPI(JNIEnv* env, jobject obj, jstring api) {
    LOGI("JNI: setGraphicsAPI called");
    
    if (!g_emulator) return JNI_FALSE;
    
    const char* apiStr = env->GetStringUTFChars(api, nullptr);
    bool result = g_emulator->SetGraphicsAPI(apiStr);
    env->ReleaseStringUTFChars(api, apiStr);
    
    return result ? JNI_TRUE : JNI_FALSE;
}

} // extern "C"
