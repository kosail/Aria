package com.korealm.aria.shared

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.korealm.aria.model.AudioResource

class AndroidAudioController(
    private val context: Context
) : AudioController {
    private val BASE_AUDIO_VOLUME = 0.8

    private val players = mutableMapOf<AudioResource, ExoPlayer>()
    private val perSoundVolume = mutableMapOf<AudioResource, Double>()
    private var globalVolume = BASE_AUDIO_VOLUME

    @OptIn(UnstableApi::class)
    private fun getOrCreatePlayer(audio: AudioResource): ExoPlayer {
        return players.getOrPut(audio) {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(
                    // user-added sounds starts on id 10_000. All other sounds are built-in.
                    if (audio.id >= 10_000) "file://${audio.audioPath}" else "asset:///${audio.audioPath}"
                )
                setMediaItem(mediaItem)
                repeatMode = Player.REPEAT_MODE_ALL
                prepare()
                val base = perSoundVolume[audio] ?: BASE_AUDIO_VOLUME
                volume = (base * globalVolume).toFloat()
            }
        }
    }

    override suspend fun play(audio: AudioResource) {
        val player = getOrCreatePlayer(audio)
        if (!player.isPlaying) {
            player.play()
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
            player.volume = effectiveVolume
        }
    }

    override suspend fun setGlobalVolume(volume: Double) {
        globalVolume = volume
        players.forEach { (res, player) ->
            val base = perSoundVolume[res] ?: BASE_AUDIO_VOLUME
            val effectiveVolume = (base * globalVolume).toFloat()
            player.volume = effectiveVolume
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