package com.example.beatbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beatbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var beatBox: BeatBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        beatBox = BeatBox(assets)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = SoundAdapter(beatBox.sounds, beatBox)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    companion object {
        private const val SPAN_COUNT = 3
    }
}
