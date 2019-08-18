package com.akexorcist.library.complexrecyclerview.state

import android.os.Bundle

interface StateChangedObserver {
    fun onSaveState(state: Bundle)

    fun onRestoreState(state: Bundle)
}
