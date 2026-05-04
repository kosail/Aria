package com.korealm.aria.shared

import aria.composeapp.generated.resources.*
import com.korealm.aria.data.CustomSoundIcons
import com.korealm.aria.model.AudioRepository
import com.korealm.aria.model.AudioResource
import com.korealm.aria.utils.getAudioRes
import org.jetbrains.compose.resources.getString

class BuiltInAudioRepository : AudioRepository {
    override suspend fun loadBuiltIn(): List<AudioResource> {
        return listOf(
            AudioResource(
                id = 1,
                icon = Res.drawable.rain_symbolic,
                title = getString(Res.string.audio_rain),
                audioPath = getAudioRes("rain")
            ),
            AudioResource(
                id = 2,
                icon = Res.drawable.storm_symbolic,
                title = getString(Res.string.audio_storm),
                audioPath = getAudioRes("storm")
            ),
            AudioResource(
                id = 3,
                icon = Res.drawable.wind_symbolic,
                title = getString(Res.string.audio_wind),
                audioPath = getAudioRes("wind")
            ),
            AudioResource(
                id = 4,
                icon = Res.drawable.waves_symbolic,
                title = getString(Res.string.audio_waves),
                audioPath = getAudioRes("waves")
            ),
            AudioResource(
                id = 5,
                icon = Res.drawable.stream_symbolic,
                title = getString(Res.string.audio_stream),
                audioPath = getAudioRes("stream")
            ),
            AudioResource(
                id = 6,
                icon = Res.drawable.birds_symbolic,
                title = getString(Res.string.audio_birds),
                audioPath = getAudioRes("birds")
            ),
            AudioResource(
                id = 7,
                icon = Res.drawable.summer_night_symbolic,
                title = getString(Res.string.audio_summer_night),
                audioPath = getAudioRes("summer_night")
            ),
            AudioResource(
                id = 8,
                icon = Res.drawable.train_symbolic,
                title = getString(Res.string.audio_train),
                audioPath = getAudioRes("train")
            ),
            AudioResource(
                id = 9,
                icon = Res.drawable.boat_symbolic,
                title = getString(Res.string.audio_boat),
                audioPath = getAudioRes("boat")
            ),
            AudioResource(
                id = 10,
                icon = Res.drawable.city_symbolic,
                title = getString(Res.string.audio_city),
                audioPath = getAudioRes("city")
            ),
            AudioResource(
                id = 11,
                icon = Res.drawable.coffee_shop_symbolic,
                title = getString(Res.string.audio_coffee),
                audioPath = getAudioRes("coffee_shop")
            ),
            AudioResource(
                id = 12,
                icon = Res.drawable.fireplace_symbolic,
                title = getString(Res.string.audio_fireplace),
                audioPath = getAudioRes("fireplace")
            ),
            AudioResource(
                id = 13,
                icon = Res.drawable.pink_noise_symbolic,
                title = getString(Res.string.audio_pink_noise),
                audioPath = getAudioRes("pink_noise")
            ),
            AudioResource(
                id = 14,
                icon = Res.drawable.white_noise_symbolic,
                title = getString(Res.string.audio_white_noise),
                audioPath = getAudioRes("white_noise")
            )
        )
    }

    override suspend fun loadUser(): List<AudioResource> {
        return emptyList() // Android target will override.
    }

    override suspend fun updateIcon(id: Int, icon: CustomSoundIcons) {
        /*
         * Android provides its own AudioRepository instance class, so it does not uses this implementation.
         * As for web and desktop targets, which don't support custom sounds, well, they do execute this code.
         *
         */

        throw UnsupportedOperationException("Custom icons not supported on Web Target! :(")
    }
}