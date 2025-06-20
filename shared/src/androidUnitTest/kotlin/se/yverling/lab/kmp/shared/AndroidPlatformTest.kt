package se.yverling.lab.kmp.shared

import kotlin.test.Test
import kotlin.test.assertEquals

// Run with ./gradlew testDebugUnitTest
class AndroidPlatformTest {
    @Test
    fun `platform name should be Android`() {
        // Using platform specific tests we can access the platform specific code directly
        val platform = AndroidPlatform()
        assertEquals(expected = "Android", actual = platform.name)
    }
}
