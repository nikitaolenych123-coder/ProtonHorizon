#include "gamepad.h"

void GamepadManager::RegisterGamepad(int deviceId) {
    GamepadInput input;
    input.state.deviceId = deviceId;
    gamepads[deviceId] = input;
}

void GamepadManager::UnregisterGamepad(int deviceId) {
    gamepads.erase(deviceId);
}

void GamepadManager::UpdateGamepadInput(int deviceId, uint32_t buttons, 
                                       const std::map<GamepadInput::AxisType, float>& axes) {
    auto it = gamepads.find(deviceId);
    if (it != gamepads.end()) {
        it->second.state.buttons = buttons;
        it->second.state.axes = axes;
    }
}

const GamepadInput* GamepadManager::GetGamepad(int deviceId) const {
    auto it = gamepads.find(deviceId);
    return it != gamepads.end() ? &it->second : nullptr;
}

std::vector<int> GamepadManager::GetConnectedGamepads() const {
    std::vector<int> result;
    for (const auto& pair : gamepads) {
        result.push_back(pair.first);
    }
    return result;
}
