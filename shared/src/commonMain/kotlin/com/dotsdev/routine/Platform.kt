package com.dotsdev.routine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform