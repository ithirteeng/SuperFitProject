package com.ithirteeng.superfitproject.pushups.utils

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class PushUpsExerciseHelper(
    private val sensorManager: SensorManager,
    private val onAction: () -> Unit,
) : SensorEventListener {
    private companion object {
        const val THRESHOLD = 2
    }

    private var isMovingDown = false

    init {
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val zAcceleration = event.values[2]
        val yAcceleration = event.values[1]
        val xAcceleration = event.values[0]

        val angle = atan2(
            yAcceleration.toDouble(),
            sqrt(xAcceleration.toDouble().pow(2) + zAcceleration.toDouble().pow(2))
        )
        val degrees = Math.toDegrees(angle)
        if (degrees in -30.0..30.0) {
            if (zAcceleration - SensorManager.GRAVITY_EARTH <= -THRESHOLD && !isMovingDown) {
                isMovingDown = true
            } else if (zAcceleration - SensorManager.GRAVITY_EARTH >= THRESHOLD && isMovingDown) {
                Log.i("ACCELERATOR", "________________MADE_________________")
                onAction()
                isMovingDown = false
            }
        }
    }

    fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }
}