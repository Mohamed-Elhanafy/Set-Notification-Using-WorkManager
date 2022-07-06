package com.example.workmanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.android.material.textfield.TextInputLayout
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val outlineEditText = findViewById<TextInputLayout>(R.id.set_time)


        btn.setOnClickListener {
            val seconds = outlineEditText.editText?.text.toString().toLong()

            createWorkRequest(
            "this is just a test",
            seconds
        )
        }

    }

    private fun createWorkRequest(message: String, timeDelayInSeconds: Long) {

        val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.SECONDS)
            .setInputData(
                workDataOf(
                    "title" to "Reminder",
                    "message" to message,
                )
            )
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }
}