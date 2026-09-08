package com.korealm.aria.data

/**
 * User audios start from index 10_000, so we have reserved 10_000 positions for built-in sounds.
 *
 * Index size is not an issue, so I think it's fine to reserve a lot of space before starting with user audios.
 */
const val CUSTOM_SOUND_START_INDEX = 10_000