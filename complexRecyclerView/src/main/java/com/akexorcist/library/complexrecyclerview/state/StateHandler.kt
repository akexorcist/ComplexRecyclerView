package com.akexorcist.library.complexrecyclerview.state

import android.os.Bundle

class StateHandler {
    private val observerList = arrayListOf<StateChangedObserver>()

    fun addObserver(observer: StateChangedObserver) {
        observerList.add(observer)
    }

    fun removeObserver(observer: StateChangedObserver) {
        observerList.remove(observer)
    }

    fun performSaveState(state: Bundle) {
        observerList.forEach { listener ->
            listener.onSaveState(state)
        }
    }

    fun performRestoreState(state: Bundle) {
        observerList.forEach { listener ->
            listener.onRestoreState(state)
        }
    }
}
