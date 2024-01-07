package com.example.flagcountry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flagcountry.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioButton.text = "Brasil"
        binding.radioButton2.text = "Canada"
        binding.radioButton3.text = "Alemania"
        binding.radioButton4.text = "Dinamarca"
        binding.radioButton5.text = "España"
        binding.radioButton6.text = "Francia"
        binding.radioButton7.text = "Gran Bretaña"
        binding.radioButton8.text = "Italia"
        binding.radioButton9.text = "México"
        binding.radioButton10.text = "Estados Unidos"

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> binding.imageView.setImageResource(R.drawable.br)
                R.id.radioButton2 -> binding.imageView.setImageResource(R.drawable.ca)
                R.id.radioButton3 -> binding.imageView.setImageResource(R.drawable.de)
                R.id.radioButton4 -> binding.imageView.setImageResource(R.drawable.dk)
                R.id.radioButton5 -> binding.imageView.setImageResource(R.drawable.es)
                R.id.radioButton6 -> binding.imageView.setImageResource(R.drawable.fr)
                R.id.radioButton7 -> binding.imageView.setImageResource(R.drawable.gb)
                R.id.radioButton8 -> binding.imageView.setImageResource(R.drawable.it)
                R.id.radioButton9 -> binding.imageView.setImageResource(R.drawable.mx)
                R.id.radioButton10 -> binding.imageView.setImageResource(R.drawable.us)
            }
        }
    }
}


