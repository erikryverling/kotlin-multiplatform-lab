package se.yverling.lab.kmp.shared

interface Platform {
    val prefix: String
    val name: String
}

expect fun getPlatform(): Platform
