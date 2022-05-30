package com.example.beatbox

import android.content.res.AssetManager

class BeatBox(private val assets: AssetManager) {
    val sounds: List<Sound>

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        return assets.list(SOUNDS_FOLDER)
            ?.map { fileName ->
                "$SOUNDS_FOLDER/$fileName"
            }?.map { assetPath ->
                Sound(assetPath)
            } ?: emptyList()
    }

    companion object {
        private const val SOUNDS_FOLDER = "sample_sounds"
    }
}
