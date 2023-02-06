package com.example.keepnotes

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val notas = ArrayList<Nota>()

        notas.add(Nota(R.string.note1))
        notas.add(Nota(R.string.note2))
        notas.add(Nota(R.string.note3))
        notas.add(Nota(R.string.note4))
        notas.add(Nota(R.string.note5))
        notas.add(Nota(R.string.note6))
        notas.add(Nota(R.string.note1))
        notas.add(Nota(R.string.note2))
        notas.add(Nota(R.string.note3))


        val recView = findViewById<RecyclerView>(R.id.recyclerView)
        recView.setHasFixedSize(true)

        val adaptador = NotaAdapter(notas)
        recView.adapter = adaptador
        recView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setImageResource(R.drawable.ic_baseline_check_24)
        var icono = 0


        fab.setOnClickListener {
            if (icono == 0) {
                val rotar = getDrawable(R.drawable.ad_animaciones) as AnimatedVectorDrawable
                fab.setImageDrawable(rotar)
                rotar.start()
                icono = 1
            } else {
                val rotar = getDrawable(R.drawable.ad_animaciones_inversa) as AnimatedVectorDrawable
                fab.setImageDrawable(rotar)
                rotar.start()
                icono = 0
            }

        }
        adaptador.onClick = {
            val cx = it.getWidth()
            val cy = it.getHeight()

            // Calculamos el máximo radio posible
            val radius = calculateMaxRadius(it)

            val anim = ViewAnimationUtils.createCircularReveal(it, cx, cy, radius, 0f)
            // Comenzamos la animación circular reveal.
            anim.duration = 2000
            anim.start()

        }


    }

    fun calculateMaxRadius(view: View): Float {
        val widthSquared = view.getWidth() * view.getWidth()
        val heightSquared = view.getHeight() * view.getHeight()
        return Math.sqrt((widthSquared + heightSquared / 2).toDouble()).toFloat()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}