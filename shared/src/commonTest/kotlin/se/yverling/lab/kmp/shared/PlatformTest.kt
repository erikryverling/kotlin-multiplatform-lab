package se.yverling.lab.kmp.shared

import kotlin.test.Test
import kotlin.test.assertEquals

// Run with ./gradlew allTest (this will run on all platforms)
class PlatformTest {
    @Test
    fun `platform name should be according to platform`() {
        val platform = getPlatform()
        // Depending if this is run on Android or iOS this will either pass or fail
        assertEquals(expected = "Android 0", actual = platform.name)
    }
}
