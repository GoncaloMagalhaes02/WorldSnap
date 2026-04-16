package com.example.worlsnap.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.pow
import kotlin.math.sqrt

class SensorHelper(context: Context) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val gyroscope     = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val proximity     = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    // ── Callbacks ─────────────────────────────────────────────────
    var onRotateRight: (() -> Unit)? = null   // giroscópio direita
    var onRotateLeft:  (() -> Unit)? = null   // giroscópio esquerda
    var onShake:       (() -> Unit)? = null   // agitar → play/pause vídeo
    var onProximity:   (() -> Unit)? = null   // aproximar ao ouvido → play/pause áudio

    // ── Cooldowns ────────────────────────────────────────────────
    private var lastRotateTime = 0L
    private var lastShakeTime  = 0L
    private val COOLDOWN_MS    = 800L

    // ── Thresholds ───────────────────────────────────────────────
    private val ROTATE_THRESHOLD = 2.5f   // rad/s no eixo Y do giroscópio
    private val SHAKE_THRESHOLD  = 15f    // m/s² força total do acelerómetro

    // Guarda o último valor de proximidade para detetar transição longe→perto
    private var proximidadeAnterior = Float.MAX_VALUE

    override fun onSensorChanged(event: SensorEvent) {
        val now = System.currentTimeMillis()

        when (event.sensor.type) {

            // ── Giroscópio ─────────────────────────────────────────
            // Eixo Y: valor positivo = rodar para a direita
            //         valor negativo = rodar para a esquerda
            Sensor.TYPE_GYROSCOPE -> {
                val rotY = event.values[1]
                if (now - lastRotateTime > COOLDOWN_MS) {
                    when {
                        rotY > ROTATE_THRESHOLD -> {
                            lastRotateTime = now
                            onRotateRight?.invoke()
                        }
                        rotY < -ROTATE_THRESHOLD -> {
                            lastRotateTime = now
                            onRotateLeft?.invoke()
                        }
                    }
                }
            }

            // ── Acelerómetro — shake ───────────────────────────────
            Sensor.TYPE_ACCELEROMETER -> {
                val force = sqrt(
                    event.values[0].pow(2) +
                            event.values[1].pow(2) +
                            event.values[2].pow(2)
                )
                if (force > SHAKE_THRESHOLD && now - lastShakeTime > COOLDOWN_MS) {
                    lastShakeTime = now
                    onShake?.invoke()
                }
            }

            // ── Sensor de proximidade ──────────────────────────────
            // Dispara apenas na transição longe → perto (aproximar ao ouvido)
            Sensor.TYPE_PROXIMITY -> {
                val distancia = event.values[0]
                val maxRange  = event.sensor.maximumRange
                if (proximidadeAnterior > maxRange / 2f && distancia < maxRange / 2f) {
                    onProximity?.invoke()
                }
                proximidadeAnterior = distancia
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun register() {
        sensorManager.registerListener(this, gyroscope,      SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, accelerometer,  SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, proximity,      SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun unregister() {
        sensorManager.unregisterListener(this)
    }
}