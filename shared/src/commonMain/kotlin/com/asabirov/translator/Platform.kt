package com.asabirov.translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform