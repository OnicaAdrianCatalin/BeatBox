package com.example.beatbox

import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

class BeatBox(private val assets: AssetManager) {
    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build()

    init {
        sounds = loadSoundsForAudio()
    }

    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    private fun loadSounds(): List<Sound> {
        return try {
            assets.list(SOUNDS_FOLDER)
                ?.map { fileName ->
                    "$SOUNDS_FOLDER/$fileName"
                }?.map { assetPath ->
                    Sound(assetPath)
                } ?: emptyList()
        } catch (e: IOException) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }
    }

    private fun load(sound: Sound) {
        val assetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(assetFileDescriptor, 1)
        sound.soundId = soundId
    }

    private fun loadSoundsForAudio(): List<Sound> {
        val sounds = loadSounds()
        sounds.forEach { sound ->
            load(sound)
        }
        return sounds
    }

    fun release() {
        soundPool.release()
    }

    companion object {
        private const val MAX_SOUNDS = 5
        private const val TAG = "BeatBox"
        private const val SOUNDS_FOLDER = "sample_sounds"
    }
}
