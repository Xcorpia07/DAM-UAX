package com.example.paiseshabitantes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.paiseshabitantes.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombrePais = intent.getStringExtra("nombrePais")

        binding.textViewPais.text = "Pais: $nombrePais"

        val poblacion = numeroHabitantes(nombrePais)
        binding.textViewHabitantes.text = "Numero de Habitantes: $poblacion"
        }

    private fun numeroHabitantes(nombrePais: String?): String {
        return when (nombrePais) {
            "Brasil" -> "203 millones"
            "Canada" -> "39,5 millones"
            "Alemania" -> "84 millones"
            "Dinamarca" -> "5,9 millones"
            "España" -> "48,4 millones"
            "Francia" -> "68,5 millones"
            "Gran Bretaña" -> "68 millones"
            "Italia" -> "58,8 millones"
            "México" -> "131,1 millones"
            "Estados Unidos" -> "340 millones"
            else -> "Desconocido"
        }
    }

        override fun onStart() {
            super.onStart()

        }
}