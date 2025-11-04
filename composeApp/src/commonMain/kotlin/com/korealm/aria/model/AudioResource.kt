package com.korealm.aria.model

import aria.composeapp.generated.resources.*
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
        audioRes = "composeResources/aria.composeapp.generated.resources/files/rain.wav"
    ),
    STORM (
        iconRes = Res.drawable.storm_symbolic,
        titleRes = Res.string.audio_storm,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/storm.wav"
    ),
    WIND (
        iconRes = Res.drawable.wind_symbolic,
        titleRes = Res.string.audio_wind,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/wind.wav"
    ),
    WAVES (
        iconRes = Res.drawable.waves_symbolic,
        titleRes = Res.string.audio_waves,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/waves.wav"
    ),
    STREAM (
        iconRes = Res.drawable.stream_symbolic,
        titleRes = Res.string.audio_stream,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/stream.wav"
    ),
    BIRDS (
        iconRes = Res.drawable.birds_symbolic,
        titleRes = Res.string.audio_birds,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/birds.wav"
    ),
    SUMMER_NIGHT (
        iconRes = Res.drawable.summer_night_symbolic,
        titleRes = Res.string.audio_summer_night,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/summer_night.wav"
    ),
    TRAIN (
        iconRes = Res.drawable.train_symbolic,
        titleRes = Res.string.audio_train,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/train.wav"
    ),
    BOAT (
        iconRes = Res.drawable.boat_symbolic,
        titleRes = Res.string.audio_boat,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/boat.wav"
    ),
    CITY (
        iconRes = Res.drawable.city_symbolic,
        titleRes = Res.string.audio_city,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/city.wav"
    ),
    COFFEE_SHOP (
        iconRes = Res.drawable.coffee_shop_symbolic,
        titleRes = Res.string.audio_coffee,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/coffee_shop.wav"
    ),
    FIREPLACE (
        iconRes = Res.drawable.fireplace_symbolic,
        titleRes = Res.string.audio_fireplace,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/fireplace.wav"
    ),
    PINK_NOISE (
        iconRes = Res.drawable.pink_noise_symbolic,
        titleRes = Res.string.audio_pink_noise,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/pink_noise.wav"
    ),
    WHITE_NOISE (
        iconRes = Res.drawable.white_noise_symbolic,
        titleRes = Res.string.audio_white_noise,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/white_noise.wav"
    )
}