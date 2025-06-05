package se.yverling.lab.kmp.shared

import me.tatarka.inject.annotations.Component
import se.yverling.lab.kmp.shared.network.Weather

@Component
abstract class AppComponent {
    abstract val weather: Weather
}

expect fun createAppComponent(): AppComponent
