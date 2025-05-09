package se.yverling.lab.kmp.shared.network

import kotlinx.serialization.Serializable

@Serializable
internal data class CurrentWeatherDto(val main: Main) {
    @Serializable
    data class Main(val temp: Float)
}
