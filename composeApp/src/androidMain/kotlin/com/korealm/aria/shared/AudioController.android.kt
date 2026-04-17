package com.korealm.aria.shared

import android.content.Context
import android.media.MediaPlayer
import com.korealm.aria.model.AudioResource

class AndroidAudioController(
    private val context: Context
) : AudioController {
    private val BASE_AUDIO_VOLUME = 0.8

    private val players = mutableMapOf<AudioResource, MediaPlayer>()
    private val perSoundVolume = mutableMapOf<AudioResource, Double>()
    private var globalVolume = BASE_AUDIO_VOLUME

    private fun getOrCreatePlayer(audio: AudioResource): MediaPlayer {
        return players.getOrPut(audio) {
            MediaPlayer().apply {
                val assetFileDescriptor = context.assets.openFd(audio.audioRes)
                setDataSource(
                    assetFileDescriptor.fileDescriptor,
                    assetFileDescriptor.startOffset,
                    assetFileDescriptor.length
                )
                assetFileDescriptor.close()
                isLooping = true
                prepare()
                val base = perSoundVolume[audio] ?: BASE_AUDIO_VOLUME
                val volume = (base * globalVolume).toFloat()
                setVolume(volume, volume)
            }
        }
    }

    override suspend fun play(audio: AudioResource) {
        val player = getOrCreatePlayer(audio)
        if (!player.isPlaying) {
            player.start()
        }
    }

    override suspend fun stop(audio: AudioResource) {
        players[audio]?.let { player ->
            if (player.isPlaying) {
                player.pause()
            }
            player.seekTo(0)
        }
    }

    override suspend fun setVolume(audio: AudioResource, volume: Double) {
        perSoundVolume[audio] = volume
        players[audio]?.let { player ->
            val effectiveVolume = (volume * globalVolume).toFloat()
            player.setVolume(effectiveVolume, effectiveVolume)
        }
    }

    override suspend fun setGlobalVolume(volume: Double) {
        globalVolume = volume
        players.forEach { (res, player) ->
            val base = perSoundVolume[res] ?: BASE_AUDIO_VOLUME
            val effectiveVolume = (base * globalVolume).toFloat()
            player.setVolume(effectiveVolume, effectiveVolume)
        }
    }

    fun release() {
        players.values.forEach { player ->
            player.stop()
            player.release()
        }
        players.clear()
    }

}