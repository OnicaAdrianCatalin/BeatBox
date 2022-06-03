package com.example.beatbox

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SoundTest {
    private lateinit var sound: Sound

    @Before
    fun setUp() {
        sound = Sound("assetpath.wav")
    }

    @Test
    fun checkIfSoundNameRemovesSuffix() {
        assertTrue(sound.name == "assetpath")
    }
}
