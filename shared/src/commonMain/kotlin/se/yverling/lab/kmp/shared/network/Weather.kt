package se.yverling.lab.kmp.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import se.yverling.lab.kmp.shared.OpenWeatherMapApiKey
import kotlin.math.roundToInt

internal class Weather {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }

        defaultRequest {
            url(BASE_URL)
        }
    }

    suspend fun getTemperature(): String {
        val response = getCurrentWeather(
            apiKey = OpenWeatherMapApiKey.key,
            longitude = LONGITUDE,
            latitude = LATITUDE,
            units = UNITS,
            languageCode = LANGUAGE_CODE,
        )

        return if (response.status.isSuccess()) "${getTemp(response)} ° C"
        else "Something went wrong"
    }

    private suspend fun getCurrentWeather(
        apiKey: String,
        longitude: Float,
        latitude: Float,
        units: String,
        languageCode: String,
    ) = httpClient.get("weather") {
        url {
            parameters.append("appid", apiKey)
            parameters.append("lat", latitude.toString())
            parameters.append("lon", longitude.toString())
            parameters.append("units", units)
            parameters.append("lang", languageCode)
        }
    }

    private companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val LONGITUDE = 18.0273F
        const val LATITUDE = 59.303F
        const val UNITS = "metric"
        const val LANGUAGE_CODE = "sv"
    }
}

private suspend fun getTemp(response: HttpResponse): String = response.body<CurrentWeatherDto>().main.temp.roundToInt().toString()
