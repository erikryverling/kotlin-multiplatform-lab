package se.yverling.lab.kmp.shared

import platform.UIKit.UIDevice.Companion.currentDevice

class IOSPlatform : Platform {
    override val name: String = buildString {
        append(currentDevice.systemName())
        append(" ")
        append(currentDevice.systemVersion)
    }
}

actual fun getPlatform(): Platform = IOSPlatform()
