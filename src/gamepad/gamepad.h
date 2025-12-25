#pragma once

#include <cstdint>
#include <vector>
#include <map>

class GamepadInput {
public:
    // Button masks
    static constexpr uint32_t BUTTON_A = 0x01;
    static constexpr uint32_t BUTTON_B = 0x02;
    static constexpr uint32_t BUTTON_X = 0x04;
    static constexpr uint32_t BUTTON_Y = 0x08;
    static constexpr uint32_t BUTTON_LB = 0x10;
    static constexpr uint32_t BUTTON_RB = 0x20;
    static constexpr uint32_t BUTTON_START = 0x40;
    static constexpr uint32_t BUTTON_SELECT = 0x80;
    static constexpr uint32_t BUTTON_LEFT_THUMB = 0x100;
    static constexpr uint32_t BUTTON_RIGHT_THUMB = 0x200;
    
    // Analog axes
    enum class AxisType {
        LEFT_X,
        LEFT_Y,
        RIGHT_X,
        RIGHT_Y,
        TRIGGER_LEFT,
        TRIGGER_RIGHT
    };
    
    struct GamepadState {
        uint32_t buttons = 0;
        std::map<AxisType, float> axes;
        int deviceId = -1;
    };
    
    GamepadState state;
    
    void ProcessButtonPress(uint32_t button) {
        state.buttons |= button;
    }
    
    void ProcessButtonRelease(uint32_t button) {
        state.buttons &= ~button;
    }
    
    void ProcessAxisMovement(AxisType axis, float value) {
        state.axes[axis] = value;
    }
    
    bool IsButtonPressed(uint32_t button) const {
        return (state.buttons & button) != 0;
    }
    
    float GetAxisValue(AxisType axis) const {
        auto it = state.axes.find(axis);
        return it != state.axes.end() ? it->second : 0.0f;
    }
};

class GamepadManager {
private:
    std::map<int, GamepadInput> gamepads;
    
public:
    void RegisterGamepad(int deviceId);
    void UnregisterGamepad(int deviceId);
    void UpdateGamepadInput(int deviceId, uint32_t buttons, const std::map<GamepadInput::AxisType, float>& axes);
    const GamepadInput* GetGamepad(int deviceId) const;
    std::vector<int> GetConnectedGamepads() const;
};
