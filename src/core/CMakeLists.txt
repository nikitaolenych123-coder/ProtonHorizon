cmake_minimum_required(VERSION 3.22)

file(GLOB_RECURSE CORE_SOURCES "*.cpp")
add_library(core_lib ${CORE_SOURCES})

target_include_directories(core_lib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
