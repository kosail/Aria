package com.korealm.aria.data

import aria.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

enum class CustomSoundIcons(
    val icon: DrawableResource
) {
    // FEELINGS
    HEART(Res.drawable.heart_symbolic),

    // ACTIONS
    BED(Res.drawable.custom_action_bed),
    CABLE_CAR(Res.drawable.custom_action_cable_car),
    CIGARETTE(Res.drawable.custom_action_cigarette),
    GAMEPAD(Res.drawable.custom_action_gamepad),
    LIBRARY(Res.drawable.custom_action_library),
    NOTEBOOK_PEN(Res.drawable.custom_action_notebook_pen),
    PEN_TOOL(Res.drawable.custom_action_pen_tool),
    TOILET(Res.drawable.custom_action_toilet),

    // ANIMALS
    BIRD(Res.drawable.custom_animal_bird),
    BUG(Res.drawable.custom_animal_bug),
    CAT(Res.drawable.custom_animal_cat),
    DOG(Res.drawable.custom_animal_dog),
    PANDA(Res.drawable.custom_animal_panda),
    RABBIT(Res.drawable.custom_animal_rabbit),
    RAT(Res.drawable.custom_animal_rat),
    TURTLE(Res.drawable.custom_animal_turtle),

    //CHESS
    BISHOP(Res.drawable.custom_chess_bishop),
    KING(Res.drawable.custom_chess_king),
    KNIGHT(Res.drawable.custom_chess_knight),
    PAWN(Res.drawable.custom_chess_pawn),
    QUEEN(Res.drawable.custom_chess_queen),
    ROOK(Res.drawable.custom_chess_rook),

    // MISC
    ACTIVITY(Res.drawable.custom_misc_activity),
    AIR_VENT(Res.drawable.custom_misc_air_vent),
    FAN(Res.drawable.custom_misc_fan),
    GEM(Res.drawable.custom_misc_gem),
    LOADER_PINWHEEL(Res.drawable.custom_misc_loader_pinwheel),
    PLANE(Res.drawable.custom_misc_plane),
    ROCKET(Res.drawable.custom_misc_rocket),
    SWORDS(Res.drawable.custom_misc_swords),

    // MUSIC & INSTRUMENTS
    DRUM(Res.drawable.custom_music_drum),
    GUITAR(Res.drawable.custom_music_guitar),
    MIC_VOCAL(Res.drawable.custom_music_mic_vocal),
    MUSIC(Res.drawable.custom_music_music),
    PIANO(Res.drawable.custom_music_piano),

    // NATURE
    FISHING_ROD(Res.drawable.custom_nature_fishing_rod),
    FLAME(Res.drawable.custom_nature_flame),
    LEAF(Res.drawable.custom_nature_leaf),
    SNOWFLAKE(Res.drawable.custom_nature_snowflake),
    SPARKLES(Res.drawable.custom_nature_sparkles),
    STAR(Res.drawable.custom_nature_star),
    TENT_TREE(Res.drawable.custom_nature_tent_tree),
    TREES(Res.drawable.custom_nature_trees),
    WAVES_HORIZONTAL(Res.drawable.custom_nature_waves_horizontal),
    ZAP(Res.drawable.custom_nature_zap),
}