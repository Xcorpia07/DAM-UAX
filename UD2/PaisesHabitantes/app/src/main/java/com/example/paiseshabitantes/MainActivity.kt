package com.example.paiseshabitantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.paiseshabitantes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val paises = arrayOf(
        "Brasil", "Canada", "Alemania", "Dinamarca", "España",
        "Francia", "Gran Bretaña", "Italia", "México", "Estados Unidos" )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adaptadorLista = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises)
        binding.listViewPaises.adapter = adaptadorLista

        binding.listViewPaises.setOnItemLongClickListener { parent, view, position, id ->
            val paisSeleccionado = paises[position]
            binding.textViewPais.text = "País seleccionado: $paisSeleccionado"
            true
        }

        binding.listViewPaises.setOnItemClickListener { parent, view, position, id ->
            val paisSeleccionado = paises[position]
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("nombrePais",paisSeleccionado)
            startActivity(intent)
        }
    }
}