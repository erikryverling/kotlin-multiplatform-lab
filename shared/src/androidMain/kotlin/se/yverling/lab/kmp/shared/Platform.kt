package se.yverling.lab.kmp.shared

class AndroidPlatform : Platform {
    override val prefix: String = "Platform: "
    override val name: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
