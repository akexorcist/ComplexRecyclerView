package com.akexorcist.library.complexrecyclerview.core

import android.os.Parcelable

object ComplexUtility {
    fun getPositionWithSum(
        factoryList: ArrayList<in ComplexAdapter.Factory<out Parcelable>>,
        position: Int
    ): Pair<Int, Int> {
        var factoryIndex = -1
        var dataIndex = -1
        var sum = 0
        for ((index, item) in factoryList.withIndex()) {
            val factory = item as ComplexAdapter.Factory<*>
            if (sum + factory.getCount() < position + 1) {
                sum += factory.getCount()
            } else {
                factoryIndex = index
                dataIndex = position - (sum)
                break
            }
        }
        return Pair(factoryIndex, dataIndex)
    }
}
