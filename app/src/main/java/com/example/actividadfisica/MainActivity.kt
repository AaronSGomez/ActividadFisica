package com.example.actividadfisica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.actividadfisica.ui.theme.ActividadFisicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vincular la actividad
        setContentView(R.layout.activity_main)
        //elementos del interfaz
        val tvTitulo= findViewById<TextView>(R.id.tvTitulo)
        val etActivity= findViewById<EditText>(R.id.etActivity)
        val etDuration= findViewById<EditText>(R.id.etDuration)
        val btnSave= findViewById<Button>(R.id.btnSave)

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActividadFisicaTheme {

    }
}