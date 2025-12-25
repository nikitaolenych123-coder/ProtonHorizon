#pragma once

#include <string>
#include <cstdint>

class GPUContext {
public:
    enum class GraphicsAPI {
        VULKAN,
        DXVK,
        VK3D,
        OPENGL
    };
    
    virtual ~GPUContext() = default;
    
    virtual bool Initialize(GraphicsAPI api) = 0;
    virtual void Shutdown() = 0;
    virtual bool Present() = 0;
    virtual void ClearScreen(uint32_t color) = 0;
};

class VulkanContext : public GPUContext {
private:
    GraphicsAPI currentAPI;
    
public:
    bool Initialize(GraphicsAPI api) override;
    void Shutdown() override;
    bool Present() override;
    void ClearScreen(uint32_t color) override;
};

class DXVKContext : public GPUContext {
private:
    GraphicsAPI currentAPI;
    
public:
    bool Initialize(GraphicsAPI api) override;
    void Shutdown() override;
    bool Present() override;
    void ClearScreen(uint32_t color) override;
};

class VK3DContext : public GPUContext {
private:
    GraphicsAPI currentAPI;
    
public:
    bool Initialize(GraphicsAPI api) override;
    void Shutdown() override;
    bool Present() override;
    void ClearScreen(uint32_t color) override;
};
