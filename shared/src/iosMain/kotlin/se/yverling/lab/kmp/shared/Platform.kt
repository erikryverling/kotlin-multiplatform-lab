package se.yverling.lab.kmp.shared

import platform.UIKit.UIDevice.Companion.currentDevice

class IosPlatform : Platform {
    override val prefix: String = "Platform: "
    override val name = currentDevice.systemName()
}

actual fun getPlatform(): Platform = IosPlatform()
