package com.example.grabadoraaudio

import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grabadoraaudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivityMainBinding

    private var rutaArchivo = "/data/data/com.example.grabadoraaudio/grabacion.3gp"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonGrabar.setOnClickListener {
            iniciarGrabacion()
        }

        binding.buttonDetener.setOnClickListener {
            detenerGrabacion()
        }

        binding.buttonReproducir.setOnClickListener {
            reproducirGrabacion()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detenerGrabacion()
    }

    private fun detenerGrabacion(){
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }

    private fun iniciarGrabacion(){
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(rutaArchivo)
            prepare()
            start()
        }
    }

    private fun reproducirGrabacion() {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(rutaArchivo)
            prepare()
            start()
        }
    }
}