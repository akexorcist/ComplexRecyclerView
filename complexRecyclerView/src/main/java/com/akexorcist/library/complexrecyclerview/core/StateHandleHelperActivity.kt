package com.akexorcist.library.complexrecyclerview.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.library.complexrecyclerview.state.StateHandler

abstract class StateHandleHelperActivity: AppCompatActivity() {
    @Suppress("MemberVisibilityCanBePrivate")
    protected val stateHandler = StateHandler()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        stateHandler.performSaveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        stateHandler.performRestoreState(savedInstanceState)
    }
}
