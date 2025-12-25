cmake_minimum_required(VERSION 3.22)

file(GLOB_RECURSE GAMEPAD_SOURCES "*.cpp")
add_library(gamepad_lib ${GAMEPAD_SOURCES})

target_include_directories(gamepad_lib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
