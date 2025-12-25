cmake_minimum_required(VERSION 3.22)

file(GLOB_RECURSE GPU_SOURCES "*.cpp")
add_library(gpu_lib ${GPU_SOURCES})

target_include_directories(gpu_lib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
