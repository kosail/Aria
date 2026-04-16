package com.korealm.aria.model

import aria.composeapp.generated.resources.*
import com.korealm.aria.utils.getAudioRes
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class AudioResource(
    val iconRes: DrawableResource,
    val titleRes: StringResource,
    val audioRes: String
) {
    RAIN (
        iconRes = Res.drawable.rain_symbolic,
        titleRes = Res.string.audio_rain,
        audioRes = getAudioRes("rain")
    ),
    STORM (
        iconRes = Res.drawable.storm_symbolic,
        titleRes = Res.string.audio_storm,
        audioRes = getAudioRes("storm")
    ),
    WIND (
        iconRes = Res.drawable.wind_symbolic,
        titleRes = Res.string.audio_wind,
        audioRes = getAudioRes("wind")
    ),
    WAVES (
        iconRes = Res.drawable.waves_symbolic,
        titleRes = Res.string.audio_waves,
        audioRes = getAudioRes("waves")
    ),
    STREAM (
        iconRes = Res.drawable.stream_symbolic,
        titleRes = Res.string.audio_stream,
        audioRes = getAudioRes("stream")
    ),
    BIRDS (
        iconRes = Res.drawable.birds_symbolic,
        titleRes = Res.string.audio_birds,
        audioRes = getAudioRes("birds")
    ),
    SUMMER_NIGHT (
        iconRes = Res.drawable.summer_night_symbolic,
        titleRes = Res.string.audio_summer_night,
        audioRes = getAudioRes("summer_night")
    ),
    TRAIN (
        iconRes = Res.drawable.train_symbolic,
        titleRes = Res.string.audio_train,
        audioRes = getAudioRes("train")
    ),
    BOAT (
        iconRes = Res.drawable.boat_symbolic,
        titleRes = Res.string.audio_boat,
        audioRes = getAudioRes("boat")
    ),
    CITY (
        iconRes = Res.drawable.city_symbolic,
        titleRes = Res.string.audio_city,
        audioRes = getAudioRes("city")
    ),
    COFFEE_SHOP (
        iconRes = Res.drawable.coffee_shop_symbolic,
        titleRes = Res.string.audio_coffee,
        audioRes = getAudioRes("coffee_shop")
    ),
    FIREPLACE (
        iconRes = Res.drawable.fireplace_symbolic,
        titleRes = Res.string.audio_fireplace,
        audioRes = getAudioRes("fireplace")
    ),
    PINK_NOISE (
        iconRes = Res.drawable.pink_noise_symbolic,
        titleRes = Res.string.audio_pink_noise,
        audioRes = getAudioRes("pink_noise")
    ),
    WHITE_NOISE (
        iconRes = Res.drawable.white_noise_symbolic,
        titleRes = Res.string.audio_white_noise,
        audioRes = getAudioRes("white_noise")
    )
}