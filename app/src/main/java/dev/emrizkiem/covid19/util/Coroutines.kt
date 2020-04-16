package dev.emrizkiem.covid19.util

import dev.emrizkiem.covid19.data.model.home.CovidOverview
import java.net.ConnectException

suspend fun <T: Any> fetchState(call: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        call.invoke()
    } catch (e: ConnectException) {
        ResultState.Error(e.message.toString())
    } catch (e: Exception) {
        ResultState.Error(e.message.toString())
    } catch (e: Throwable) {
        ResultState.Error(e.message.toString())
    }
}