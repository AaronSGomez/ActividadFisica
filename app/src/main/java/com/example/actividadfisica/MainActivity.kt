package com.example.actividadfisica

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadfisica.model.Registro  //importar clase Registro
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actividadfisica.ui.theme.ActividadFisicaTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vincular la actividad
        setContentView(R.layout.activity_main)

        //elementos del interfaz
        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val etActivity = findViewById<EditText>(R.id.etActivity)
        val etDuration = findViewById<EditText>(R.id.etDuration)
        val etType = findViewById<EditText>(R.id.etType)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val rv = findViewById<RecyclerView>(R.id.rvActivities)


        //creamos adapter
        val adapter = RegistroAdapter()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val items: MutableList<Registro> = mutableListOf()

        btnSave.setOnClickListener {
            val activity = etActivity.text.toString().trim()
            val duration = etDuration.text.toString().trim().toIntOrNull()
            val type = etType.text.toString().trim()

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
            etType.text.clear()

        }

    }
}



