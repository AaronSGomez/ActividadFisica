package com.example.actividadfisica

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadfisica.model.Registro  //importar clase Registro
import androidx.recyclerview.widget.LinearLayoutManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vincular la actividad
        setContentView(R.layout.activity_main)

        //elementos del interfaz
        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val etActivity = findViewById<EditText>(R.id.etActivity)
        val etDuration = findViewById<EditText>(R.id.etDuration)
        val spType = findViewById<Spinner>(R.id.spType)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnGame= findViewById<Button>(R.id.btnGame)

        val opcionesDeporte = listOf("Cardio", "Fuerza / Tonificación", "HIIT / Alta Intensidad","Natación",
            "Aerobicos / Spinning",
            "Movilidad", "Yoga / Pilates", "Artes Marciales","Outdoor",
            "Otros")
        val adapterSpinner =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesDeporte)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spType.adapter = adapterSpinner

        val rv = findViewById<RecyclerView>(R.id.rvActivities)


        //creamos adapter
        val adapter = RegistroAdapter()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val items: MutableList<Registro> = mutableListOf()

        btnSave.setOnClickListener {
            val activity = etActivity.text.toString().trim()
            val duration = etDuration.text.toString().trim().toIntOrNull()
            val type = spType.selectedItem.toString()

            val timeNow = LocalDateTime.now()   // cogemos la fecha y hora del sistema
            val format = DateTimeFormatter.ofPattern("dd/MM HH:mm")   // formateamos
            val fecha = timeNow.format(format).toString()    // convertimos a string



            if (activity.isNotEmpty() && duration != null && type.isNotEmpty()) {
                //crear objeto registro
                val registro = Registro(activity, duration, fecha, type)
                items.add(registro)
                adapter.submitList(items)
            }

            etActivity.text.clear()
            etDuration.text.clear()
            spType.setSelection(0)

        }

        btnGame.setOnClickListener {
            val intent = Intent (this, RealTimeSession::class.java)
            startActivity(intent)
        }

    }
}



