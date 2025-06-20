package se.yverling.lab.kmp.shared

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds
import se.yverling.lab.kmp.shared.network.Weather

object Messages {
    private val platform: Platform = getPlatform()
    private val weather = Weather()

    fun asFlow() = flow {
        emit("${platform.prefix} ${platform.name}")
        delay(1.seconds)
        emit("Current temperature: ${weather.getTemperature()}")
    }
}
