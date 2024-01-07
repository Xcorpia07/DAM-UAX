package com.example.reproductorvideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reproductorvideo.databinding.ActivityMainBinding
import android.widget.MediaController

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.embotelladoradls17))


        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        binding.videoView.setMediaController(mediaController)

        binding.videoView.setOnPreparedListener{
            it.start()
        }
    }
}


