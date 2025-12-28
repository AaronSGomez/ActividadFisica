package com.example.actividadfisica

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.math.abs
import kotlin.math.sqrt

// IMPORT DE LA CLASE ENCARGADA DEL COLOR
import android.graphics.Color
import android.widget.LinearLayout

// me daba fallo la app por usar el appCompactActivity() ComponentActivity() soluciona error (mas moderno)
class RealTimeSession : ComponentActivity(), SensorEventListener {

    // TextView donde se mostrará la intensidad de la agitación
    // en formato numérico (ej.: 1.25, 3.80…).
    private lateinit var tvShakeValue : TextView

    // TextView donde se muestra un nivel interpretado:
    // "quieto", "suave", "medio", "depravado".
    private lateinit var tvShakeLevel : TextView

    // SensorManager es la clase responsable de gestionar los sensores
    // del dispositivo (acelerómetro, giroscopio, luz, proximidad, etc.).
    private lateinit var sensorManager : SensorManager

    // Objeto que representa el sensor acelerómetro del dispositivo.
    // Puede ser null si el móvil no tiene acelerómetro.
    private var accelerometer: Sensor? = null


    // Almacena la aceleración del sensor en la actualización anterior.
    private var lastAcceleration = 0f

    // Almacena la aceleración actual.
    private var currentAcceleration = 0f

    // Valor final calculado de cuánta agitación hay.
    // Lo calculamos como la diferencia entre la aceleración actual
    // y la aceleración anterior.
    private var shakeIntensity = 0f

    // Para cambiar el fondo debemos crear una variable que coja el contenedor
    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        container = findViewById<LinearLayout>(R.id.gameLayout)


        // Vinculación de los TextView definidos en el XML.
        tvShakeValue = findViewById(R.id.tvShakeValue)
        tvShakeLevel = findViewById(R.id.tvShakeLevel)

        // Obtenemos el SensorManager del sistema para acceder a los sensores.
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Obtenemos el acelerómetro del dispositivo.
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Inicializamos los valores de aceleración con la gravedad terrestre (~9.8).
        // Esto sirve para que la primera comparación no dé resultados exagerados.
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH
    }


    // -----------------------------------------------------------------
    override fun onSensorChanged(evento: SensorEvent) {

        // Verificamos que el evento pertenece al acelerómetro.
        if (evento.sensor.type == Sensor.TYPE_ACCELEROMETER){

            // 1) Lectura de los valores del acelerómetro en X, Y, Z.
            val x = evento.values[0]
            val y = evento.values[1]
            val z = evento.values[2]

            // 2) Cálculo de la aceleración total usando el módulo del vector:
            //      |a| = sqrt( x² + y² + z² )
            //
            // Esto convierte tres valores independientes en una sola magnitud.
            val acceleration = sqrt(x * x + y * y + z * z)

            // 3) Guardamos la lectura anterior y la sustituimos por la nueva.
            lastAcceleration = currentAcceleration
            currentAcceleration = acceleration

            // 4) Delta = diferencia entre la lectura actual y la anterior.
            // El valor absoluto sirve para evitar negativos.
            val delta = currentAcceleration - lastAcceleration
            shakeIntensity = abs(delta)

            // 5) Mostramos la intensidad numérica con dos decimales.
            tvShakeValue.text = String.format("%.2f", shakeIntensity)

            // 6) Clasificamos el nivel de agitación según el valor obtenido.
            val levelText=when {
                shakeIntensity < 1f -> {
                    container.setBackgroundColor(Color.WHITE)// Casi sin movimiento
                    tvShakeLevel.setTextColor(Color.BLACK)
                    tvShakeValue.setTextColor(Color.BLACK)
                    // texto al final, porque es el return
                    "Nivel: quieto"
                }
                shakeIntensity < 4f -> {
                    container.setBackgroundColor(Color.CYAN)// Movimiento leve
                    tvShakeLevel.setTextColor(Color.BLACK)
                    tvShakeValue.setTextColor(Color.BLACK)
                    "Nivel: suave"

                }
                shakeIntensity < 8f -> {
                    container.setBackgroundColor(Color.MAGENTA)// Movimiento moderado
                    tvShakeLevel.setTextColor(Color.WHITE)
                    tvShakeValue.setTextColor(Color.WHITE)
                    "Nivel: medio"

                }
                else -> {
                    container.setBackgroundColor(Color.RED) // Movimiento muy fuerte
                    tvShakeLevel.setTextColor(Color.WHITE)
                    tvShakeValue.setTextColor(Color.WHITE)
                    "Nivel: depravado"
                }
            }



            // Mostramos el texto interpretado en pantalla.
            tvShakeLevel.text = levelText
        }
    }

    // -----------------------------------------------------------------
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onResume() {
        super.onResume()

        accelerometer?.also { sensor ->
            sensorManager.registerListener(
                this,                        // Listener (esta Activity)
                sensor,                      // Sensor a escuchar
                SensorManager.SENSOR_DELAY_UI // Frecuencia adecuada para UI
            )
        }
    }

    // -----------------------------------------------------------------
    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
