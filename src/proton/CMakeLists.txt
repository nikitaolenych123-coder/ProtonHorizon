cmake_minimum_required(VERSION 3.22)

file(GLOB_RECURSE PROTON_SOURCES "*.cpp")
add_library(proton_lib ${PROTON_SOURCES})

target_include_directories(proton_lib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
