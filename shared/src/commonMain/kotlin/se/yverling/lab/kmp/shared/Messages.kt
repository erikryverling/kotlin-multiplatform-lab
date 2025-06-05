package se.yverling.lab.kmp.shared

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object Messages {
    private val platform: Platform = getPlatform()

    val component = createAppComponent()
    private val weather = component.weather

    fun asFlow() = flow {
        emit("Welcome to ${platform.name}")
        delay(1.seconds)
        emit("Here's the current temperature")
        delay(1.seconds)
        emit(weather.getTemperature())
    }
}
