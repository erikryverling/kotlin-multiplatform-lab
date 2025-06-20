package se.yverling.lab.kmp.shared

import kotlin.test.Test
import kotlin.test.assertEquals

// Run with ./gradlew iosSimulatorArm64Test
class IosPlatformTest {
    @Test
    fun `platform name should be iOS`() {
        // Using platform specific tests we can access the platform specific code directly
        val platform = IosPlatform()
        assertEquals(expected = "iOS 18.5", actual = platform.name)
    }
}
