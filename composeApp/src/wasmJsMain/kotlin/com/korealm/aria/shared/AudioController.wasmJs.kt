package com.korealm.aria.shared

import com.korealm.aria.model.AudioResource
import js.buffer.ArrayBuffer
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.coroutineScope
import web.audio.*
import web.http.Response
import web.http.arrayBuffer

@OptIn(ExperimentalWasmJsInterop::class)
class WebAudioController : AudioController {
    private val BASE_AUDIO_VOLUME = 0.8f
    private val audioContext = AudioContext()

    private val buffers = mutableMapOf<AudioResource, AudioBuffer>()
    private val sources = mutableMapOf<AudioResource, AudioBufferSourceNode>()
    private val gains = mutableMapOf<AudioResource, GainNode>()

    private val perSoundVolume = mutableMapOf<AudioResource, Float>()
    private var globalVolume: Float = BASE_AUDIO_VOLUME

    private suspend fun loadBuffer(audio: AudioResource): AudioBuffer {
        buffers[audio]?.let { return it }

        val response: Response = window.fetch(audio.audioRes).await()
        val arrayBuffer: ArrayBuffer = response.arrayBuffer()
        val decoded: AudioBuffer = audioContext.decodeAudioData(arrayBuffer)

        buffers[audio] = decoded
        return decoded
    }

    override suspend fun play(audio: AudioResource) {
        val buffer = loadBuffer(audio)
        sources[audio]?.let {
            it.stop()
            it.disconnect()
        }

        val source = audioContext.createBufferSource()
        source.buffer = buffer
        source.loop = true

        val gainNode = gains.getOrPut(audio) {
            audioContext.createGain().also {
                it.connect(audioContext.destination)
            }
        }

        val base = perSoundVolume[audio] ?: BASE_AUDIO_VOLUME
        gainNode.gain.value = base * globalVolume

        source.connect(gainNode)

        source.start()

        sources[audio] = source
    }

    override suspend fun stop(audio: AudioResource) {
        sources[audio]?.let { source ->
            source.stop()
            source.disconnect()
            sources.remove(audio)
        }
    }

    override suspend fun setVolume(audio: AudioResource, volume: Float) {
        perSoundVolume[audio] = volume
        gains[audio]?.gain?.value = volume * globalVolume
    }

    override suspend fun setGlobalVolume(volume: Float) {
        globalVolume = volume
        gains.forEach { (res, gain) ->
            val base = perSoundVolume[res] ?: BASE_AUDIO_VOLUME
            gain.gain.value = base * globalVolume
        }
    }
}
