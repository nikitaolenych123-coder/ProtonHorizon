cmake_minimum_required(VERSION 3.22)
project(ProtonHorizon)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Add source directories
add_subdirectory(src/core)
add_subdirectory(src/gamepad)
add_subdirectory(src/gpu)
add_subdirectory(src/proton)

# Main emulator library
file(GLOB_RECURSE EMULATOR_SOURCES
    "src/core/*.cpp"
    "src/gamepad/*.cpp"
    "src/gpu/*.cpp"
    "src/proton/*.cpp"
)

add_library(proton_horizon SHARED
    ${EMULATOR_SOURCES}
    src/android/jni_bridge.cpp
)

target_include_directories(proton_horizon PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/src/core
    ${CMAKE_CURRENT_SOURCE_DIR}/src/gamepad
    ${CMAKE_CURRENT_SOURCE_DIR}/src/gpu
    ${CMAKE_CURRENT_SOURCE_DIR}/src/proton
    ${CMAKE_CURRENT_SOURCE_DIR}/src/android
)

find_library(log-lib log)
target_link_libraries(proton_horizon ${log-lib})
