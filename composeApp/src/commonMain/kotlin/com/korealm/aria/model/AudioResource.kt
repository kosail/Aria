package com.korealm.aria.model

import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.audio_birds
import aria.composeapp.generated.resources.audio_boat
import aria.composeapp.generated.resources.audio_cat_meow
import aria.composeapp.generated.resources.audio_city
import aria.composeapp.generated.resources.audio_coffee
import aria.composeapp.generated.resources.audio_fireplace
import aria.composeapp.generated.resources.audio_pink_noise
import aria.composeapp.generated.resources.audio_rain
import aria.composeapp.generated.resources.audio_storm
import aria.composeapp.generated.resources.audio_stream
import aria.composeapp.generated.resources.audio_summer_night
import aria.composeapp.generated.resources.audio_train
import aria.composeapp.generated.resources.audio_waves
import aria.composeapp.generated.resources.audio_white_noise
import aria.composeapp.generated.resources.audio_wind
import aria.composeapp.generated.resources.birds_symbolic
import aria.composeapp.generated.resources.boat_symbolic
import aria.composeapp.generated.resources.cat_symbolic
import aria.composeapp.generated.resources.city_symbolic
import aria.composeapp.generated.resources.coffee_shop_symbolic
import aria.composeapp.generated.resources.fireplace_symbolic
import aria.composeapp.generated.resources.pink_noise_symbolic
import aria.composeapp.generated.resources.rain_symbolic
import aria.composeapp.generated.resources.storm_symbolic
import aria.composeapp.generated.resources.stream_symbolic
import aria.composeapp.generated.resources.summer_night_symbolic
import aria.composeapp.generated.resources.train_symbolic
import aria.composeapp.generated.resources.waves_symbolic
import aria.composeapp.generated.resources.white_noise_symbolic
import aria.composeapp.generated.resources.wind_symbolic
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
        audioRes = "composeResources/aria.composeapp.generated.resources/files/stream.ogg"
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
    ),
    CAT_MEOW(
        iconRes = Res.drawable.cat_symbolic,
        titleRes = Res.string.audio_cat_meow,
        audioRes = "composeResources/aria.composeapp.generated.resources/files/cat_meow.ogg"
    )
}