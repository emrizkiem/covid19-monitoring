package dev.emrizkiem.covid19.util

object Constant {
    const val API_VERSION = "1.0.0"
    const val DB_VERSION = 1
    const val NETWORK_TIMEOUT = 60L
    const val ERROR_MESSAGE = "Cannot proceed your request, please try again later"
}

object CaseType {
    const val CONFIRMED = 0
    const val DEATHS = 1
    const val RECOVERED = 2
}

object IncrementStatus {
    const val FLAT = 0
    const val INCREASE = 1
    const val DECREASE = 2
}