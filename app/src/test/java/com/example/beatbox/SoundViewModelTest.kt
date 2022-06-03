package com.example.beatbox

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SoundViewModelTest {
    @Mock
    private lateinit var beatBox: BeatBox

    @Mock
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    @Test
    fun callsBeatBoxPlayButtonClicked() {
        subject.onButtonClicked()
        verify(beatBox).play(sound)
    }

    @Test
    fun exposesSoundNameAsTitle() {
        Mockito.`when`(sound.name).thenReturn("70_EH")
        Assert.assertSame(subject.title, sound.name)
    }
}
