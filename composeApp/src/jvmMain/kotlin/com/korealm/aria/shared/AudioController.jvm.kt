package com.korealm.aria.shared

import com.korealm.aria.model.AudioResource
import korlibs.audio.sound.*
import korlibs.io.file.std.resourcesVfs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration.Companion.milliseconds

class JvmAudioController : AudioController {
    private val mutex = Mutex()

    private val sounds = ConcurrentHashMap<AudioResource, Sound>()

    // Active playback instances (one per sound)
    private val channels = ConcurrentHashMap<AudioResource, SoundChannel>()
    private val perSoundVolume = ConcurrentHashMap<AudioResource, Double>()
    private var globalVolume = 0.8

    private suspend fun getOrLoadSound(audio: AudioResource): Sound = withContext(Dispatchers.IO) {
        mutex.withLock {
            sounds.getOrPut(audio) {
                resourcesVfs[audio.audioPath].readSound(streaming = true)
            }
        }
    }

    override suspend fun play(audio: AudioResource) {
        val sound = getOrLoadSound(audio)
        channels[audio]?.stop()

        val volume = (perSoundVolume[audio] ?: 0.8) * globalVolume

        val channel = sound.play(PlaybackParameters(
            times = PlaybackTimes.INFINITE,
            volume = volume
        ))

        channels[audio] = channel
    }

    override suspend fun stop(audio: AudioResource) {
        channels[audio]?.stop()
        channels.remove(audio)
    }

    override suspend fun setVolume(audio: AudioResource, volume: Double) {
        perSoundVolume[audio] = volume

        val effective = volume * globalVolume

        channels[audio]?.volume = effective
    }

    override suspend fun setGlobalVolume(volume: Double) {
        globalVolume = volume

        channels.forEach { (audio, channel) ->
            val base = perSoundVolume[audio] ?: 0.8
            channel.volume = base * globalVolume
        }
    }

    /**
    * Load all sounds to warm up the KorGe backend.
     *
     * Without warming up the backend, first time audio playing takes around 1-2 seconds, even with the play(streaming = true) parameter.
     * However, after starting an audio for the first time, the second time works flawlessly.
     *
     * I believe that this flaw is inherited from javax.sample and not a KorGe issue.
     *
     * @return Unit
    */
    suspend fun warmup(audio: AudioResource) {
        val sound = getOrLoadSound(audio)

        val channel = sound.play(
            PlaybackParameters(
                times = PlaybackTimes.ONE,
                volume = 0.0
            )
        )

        // let korge backend initialize.
        // For some reason, loading them into resourcesVfs is not enough, yet actually start playing them.
        delay(50.milliseconds)
        channel.stop()
    }
}