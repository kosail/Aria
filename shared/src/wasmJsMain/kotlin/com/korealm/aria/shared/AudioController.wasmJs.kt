package com.korealm.aria.shared

import com.korealm.aria.model.AudioResource
import js.buffer.ArrayBuffer
import kotlinx.browser.window
import kotlinx.coroutines.await
import web.audio.*
import web.http.Response
import web.http.arrayBuffer

@OptIn(ExperimentalWasmJsInterop::class)
class WebAudioController : AudioController {
    private val BASE_AUDIO_VOLUME = 0.8
    private val audioContext = AudioContext()

    private val buffers = mutableMapOf<Int, AudioBuffer>()
    private val sources = mutableMapOf<Int, AudioBufferSourceNode>()
    private val gains = mutableMapOf<Int, GainNode>()

    private val perSoundVolume = mutableMapOf<Int, Double>()
    private var globalVolume = BASE_AUDIO_VOLUME

    private suspend fun loadBuffer(audio: AudioResource): AudioBuffer {
        buffers[audio.id]?.let { return it }

        val response: Response = window.fetch(audio.audioPath).await()
        val arrayBuffer: ArrayBuffer = response.arrayBuffer()
        val decoded: AudioBuffer = audioContext.decodeAudioData(arrayBuffer)

        buffers[audio.id] = decoded
        return decoded
    }

    override suspend fun play(audio: AudioResource) {
        val buffer = loadBuffer(audio)
        sources[audio.id]?.let {
            it.stop()
            it.disconnect()
        }

        val source = audioContext.createBufferSource()
        source.buffer = buffer
        source.loop = true

        val gainNode = gains.getOrPut(audio.id) {
            audioContext.createGain().also {
                it.connect(audioContext.destination)
            }
        }

        val base = perSoundVolume[audio.id] ?: BASE_AUDIO_VOLUME
        gainNode.gain.value = (base * globalVolume).toFloat()

        source.connect(gainNode)

        source.start()

        sources[audio.id] = source
    }

    override suspend fun stop(audio: AudioResource) {
        sources[audio.id]?.let { source ->
            source.stop()
            source.disconnect()
            sources.remove(audio.id)
        }
    }

    override suspend fun setVolume(audio: AudioResource, volume: Double) {
        perSoundVolume[audio.id] = volume
        gains[audio.id]?.gain?.value = (volume * globalVolume).toFloat()
    }

    override suspend fun setGlobalVolume(volume: Double) {
        globalVolume = volume
        gains.forEach { (res, gain) ->
            val base = perSoundVolume[res] ?: BASE_AUDIO_VOLUME
            gain.gain.value = (base * globalVolume).toFloat()
        }
    }
}
