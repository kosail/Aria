package com.korealm.aria.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.korealm.aria.utils.AndroidAudioController

class AudioControllerViewModel(application: Application): AndroidViewModel(application) {
    val state = AndroidAudioController(application.applicationContext)

    override fun onCleared() {
        super.onCleared()
        state.release()
    }

}