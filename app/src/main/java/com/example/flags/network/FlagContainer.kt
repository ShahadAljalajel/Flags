package com.example.flags.network

data class FlagContainer(
    val error: Boolean,
    val msg: String,
    val data: List<Flag>,
)
