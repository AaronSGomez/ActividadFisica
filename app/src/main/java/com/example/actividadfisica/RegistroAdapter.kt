package com.example.actividadfisica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadfisica.model.Registro

class RegistroAdapter ( private val items: MutableList<Registro> = mutableListOf()): RecyclerView.Adapter<RegistroAdapter.VH>() {


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Accedemos a las vistas del layout activity_list.xml usando sus IDs
        val activity: TextView  = itemView.findViewById(R.id.tvActivity)  // Icono del clima
        val duration: TextView  = itemView.findViewById(R.id.tvDuration)  // Texto con la hora
        val date: TextView  = itemView.findViewById(R.id.tvDate)
        val type: TextView  = itemView.findViewById(R.id.tvType)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater
            .from(parent.context) // Usamos el contexto del RecyclerView
            .inflate(
                R.layout.activity_list,  // El layout XML que define UNA FILA
                parent,              // El padre donde vivirá esta vista (RecyclerView)
                false                // false → NO añadir al padre todavía (lo hace RecyclerView)
            )

        // Devolvemos un ViewHolder con la vista ya inflada.
        return VH(view)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        // Obtenemos el objeto HourWeather de esa posición
        val item = items[position]

        // Actualizamos la fila con los datos
        holder.activity.text = item.Nombre
        holder.duration.text = item.Duracion.toString()
        holder.date.text = item.Fecha
        holder.type.text = item.Tipo


    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<Registro>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }



}