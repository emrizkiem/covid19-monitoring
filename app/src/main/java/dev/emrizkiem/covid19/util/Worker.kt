package dev.emrizkiem.covid19.util

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.KoinComponent

class Worker(
    private val context: Context,
    params: WorkerParameters
): CoroutineWorker(context, params), KoinComponent {
    override suspend fun doWork(): Result  {
        TODO("Not yet implemented")
    }
}