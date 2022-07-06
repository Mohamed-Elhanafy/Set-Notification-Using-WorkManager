package com.example.workmanger

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorker(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        NotificationHelper(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString()
        )
        Log.d("TAG", "doWork: ")
        return Result.success()

    }
}