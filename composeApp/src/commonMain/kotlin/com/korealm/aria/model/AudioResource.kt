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
        audioRes = "composeResources/aria.composeapp.generated.resources/files/rain.ogg"
    ),
    STORM (
        iconRes = Res.drawable.storm_symbolic,
        titleRes = Res.string.audio_storm,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/storm.ogg"
    ),
    WIND (
        iconRes = Res.drawable.wind_symbolic,
        titleRes = Res.string.audio_wind,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/wind.ogg"
    ),
    WAVES (
        iconRes = Res.drawable.waves_symbolic,
        titleRes = Res.string.audio_waves,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/waves.ogg"
    ),
    STREAM (
        iconRes = Res.drawable.stream_symbolic,
        titleRes = Res.string.audio_stream,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/stream.ogg"
    ),
    BIRDS (
        iconRes = Res.drawable.birds_symbolic,
        titleRes = Res.string.audio_birds,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/birds.ogg"
    ),
    SUMMER_NIGHT (
        iconRes = Res.drawable.summer_night_symbolic,
        titleRes = Res.string.audio_summer_night,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/summer_night.ogg"
    ),
    TRAIN (
        iconRes = Res.drawable.train_symbolic,
        titleRes = Res.string.audio_train,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/train.ogg"
    ),
    BOAT (
        iconRes = Res.drawable.boat_symbolic,
        titleRes = Res.string.audio_boat,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/boat.ogg"
    ),
    CITY (
        iconRes = Res.drawable.city_symbolic,
        titleRes = Res.string.audio_city,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/city.ogg"
    ),
    COFFEE_SHOP (
        iconRes = Res.drawable.coffee_shop_symbolic,
        titleRes = Res.string.audio_coffee,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/coffee_shop.ogg"
    ),
    FIREPLACE (
        iconRes = Res.drawable.fireplace_symbolic,
        titleRes = Res.string.audio_fireplace,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/fireplace.ogg"
    ),
    PINK_NOISE (
        iconRes = Res.drawable.pink_noise_symbolic,
        titleRes = Res.string.audio_pink_noise,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/pink_noise.ogg"
    ),
    WHITE_NOISE (
        iconRes = Res.drawable.white_noise_symbolic,
        titleRes = Res.string.audio_white_noise,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/white_noise.ogg"
    )
}