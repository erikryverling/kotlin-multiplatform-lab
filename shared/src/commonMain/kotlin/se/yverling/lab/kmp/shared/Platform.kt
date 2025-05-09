package se.yverling.lab.kmp.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
