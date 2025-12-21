package com.korealm.aria.shared

import android.content.Context
import android.media.MediaPlayer
import com.korealm.aria.model.AudioResource

class AndroidAudioController(
    private val context: Context
) : AudioController {
    private val players = mutableMapOf<AudioResource, MediaPlayer>()
    private val perSoundVolume = mutableMapOf<AudioResource, Float>()
    private var globalVolume: Float = 1f

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
                val base = perSoundVolume[audio] ?: 0.8f
                val volume = base * globalVolume
                setVolume(volume, volume)
            }
        }
    }

    override fun load(audio: AudioResource) {
        getOrCreatePlayer(audio)
    }

    override fun play(audio: AudioResource) {
        val player = getOrCreatePlayer(audio)
        if (!player.isPlaying) {
            player.start()
        }
    }

    override fun stop(audio: AudioResource) {
        players[audio]?.let { player ->
            if (player.isPlaying) {
                player.pause()
            }
            player.seekTo(0)
        }
    }

    override fun setVolume(audio: AudioResource, volume: Float) {
        perSoundVolume[audio] = volume
        players[audio]?.let { player ->
            val effectiveVolume = volume * globalVolume
            player.setVolume(effectiveVolume, effectiveVolume)
        }
    }

    override fun setGlobalVolume(volume: Float) {
        globalVolume = volume
        players.forEach { (res, player) ->
            val base = perSoundVolume[res] ?: 1f
            val effectiveVolume = base * globalVolume
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