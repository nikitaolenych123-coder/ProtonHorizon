#pragma once

#include <cstdint>
#include <vector>
#include <memory>

class Emulator {
public:
    Emulator();
    ~Emulator();
    
    bool Initialize();
    void Shutdown();
    void RunFrame();
    void Stop();
    
    bool LoadRom(const char* path);
    bool SetGraphicsAPI(const char* api);
    
    void ProcessInput(int type, const int* data, int size);
    
private:
    bool initialized;
    uint8_t* memory;
    size_t memorySize;
    
    void InitializeMemory();
    void CleanupMemory();
};
