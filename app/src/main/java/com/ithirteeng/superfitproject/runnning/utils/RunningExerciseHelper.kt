package com.ithirteeng.superfitproject.runnning.utils

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class RunningExerciseHelper(
    private val sensorManager: SensorManager,
    private val onFirstAction: (steps: Int) -> Unit,
    private val onAction: (steps: Int) -> Unit,
) : SensorEventListener {
    private var isFirstTime = true

    init {
        val stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d("MOVING", "${event.values[0]}")
        if (isFirstTime) {
            isFirstTime = false
            onFirstAction(event.values[0].toInt())
            return
        }
        val stepsCount = event.values[0]
        onAction(stepsCount.toInt())
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }
}