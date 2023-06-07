package com.ithirteeng.superfitproject.squats.utils

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class SquatsExerciseHelper(
    private val sensorManager: SensorManager,
    private val onAction: () -> Unit,
) : SensorEventListener {
    private companion object {
        const val THRESHOLD = 4
    }

    private var exerciseCount = 0
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
        if (degrees in 50.0..100.0) {
            if (yAcceleration - SensorManager.GRAVITY_EARTH <= -THRESHOLD && !isMovingDown) {
                isMovingDown = true
            } else if (yAcceleration - SensorManager.GRAVITY_EARTH >= THRESHOLD && isMovingDown) {
                exerciseCount++
                onAction()
                isMovingDown = false
            }
        } else if (degrees in -10.0..50.0) {
            if (zAcceleration - SensorManager.GRAVITY_EARTH <= -THRESHOLD && !isMovingDown) {
                isMovingDown = true
            } else if (zAcceleration - SensorManager.GRAVITY_EARTH >= THRESHOLD && isMovingDown) {
                exerciseCount++
                onAction()
                isMovingDown = false
            }
        }
    }

    fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }
}