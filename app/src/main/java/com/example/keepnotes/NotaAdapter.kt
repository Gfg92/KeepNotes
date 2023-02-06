package com.example.keepnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotaAdapter(var notas: ArrayList<Nota>) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>(){
    lateinit var onClick: (View) -> Unit
    init {
        this.notas = notas
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NotaAdapter.NotaViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.nota, viewGroup, false)
        return NotaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: NotaAdapter.NotaViewHolder, position: Int) {
        val nota = notas.get(position)
        holder.bindNota(nota, onClick)
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contenido: TextView

        init {
            contenido = itemView.findViewById(R.id.txt1)
        }

        fun bindNota(n: Nota, onClick:(View) -> Unit) = with(itemView){
            contenido.setText(n.contenido)
            setOnClickListener{onClick(itemView)}
        }

    }
}