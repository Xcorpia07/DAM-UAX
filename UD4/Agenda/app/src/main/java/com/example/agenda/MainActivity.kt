package com.example.agenda

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.agenda.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGuardar.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                guardarDatos(binding.edittextNombre.text.toString(), binding.edittextTelefono.text.toString().toInt(),binding.edittextArchivo.text.toString())
            }
        }

        binding.buttonConsultar.setOnClickListener {
            binding.textViewConsulta.text=""
            if (binding.edittextArchivo.text.isEmpty()){
                mySnackBarAction("Nombre del archivo incorrecto","OK")
            }else
                consultarDatos(binding.edittextArchivo.text.toString())
        }
    }

    suspend fun guardarDatos(nombre: String, telefono: Int,nombreArchivo: String){
        dataStore.edit {
            it[stringPreferencesKey("name")] = nombre
            it[intPreferencesKey("phone")] = telefono
        }
        if(binding.edittextNombre.text.isNotEmpty() && binding.edittextTelefono.text.isNotEmpty() && binding.edittextArchivo.text.isNotEmpty()){

            val archivoGuardar = File(ruta(nombreArchivo))

            if (archivoGuardar.exists()) {
                mySnackBarAction("El nombre del archivo ya existe","OK")
            } else {
                try {
                    withContext(Dispatchers.IO) {
                        val writer = FileWriter(archivoGuardar, true)
                        writer.append("Nombre: $nombre\nTeléfono: $telefono\n")
                        writer.close()
                    }
                    mySnackBarAction("Contacto guardado correctamente", "OK")
                    binding.edittextNombre.text.clear()
                    binding.edittextTelefono.text.clear()
                    binding.edittextArchivo.text.clear()
                } catch (e: IOException) {
                    e.printStackTrace()
                    mySnackBarAction("Error al guardar el contacto", "OK")
                }
            }
        }
    }

    fun consultarDatos(nombreArchivo:String){

        val archivoConsulta = File(ruta(nombreArchivo))
        if (archivoConsulta.exists()) {
            binding.textViewConsulta.text = archivoConsulta.readText()
        } else {
            mySnackBarAction("Archivo no encontrado","OK")
        }
    }

    private fun mySnackBarAction (mensaje : String, boton: String ){
        Snackbar
            .make(binding.root,mensaje, Snackbar.LENGTH_INDEFINITE)
            .setAction(boton, View.OnClickListener {
            }).show()
    }

    private fun ruta(nombreArchivo: String):String{
        return "data/data/com.example.agenda/$nombreArchivo.txt"
    }
}