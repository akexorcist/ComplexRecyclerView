package com.akexorcist.library.complexrecyclerview.core

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ComplexRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    companion object {
        private const val ACTUAL_COLUMN_COUNT = 1000
        const val AUTO_FIT = 100
    }

    fun setFactoryList(factoryList: ArrayList<in ComplexAdapter.Factory<Any>>) {
        if (layoutManager != null || layoutManager !is GridLayoutManager) {
            layoutManager = GridLayoutManager(
                context,
                ACTUAL_COLUMN_COUNT
            )
        }
        (layoutManager as GridLayoutManager).spanSizeLookup = initSpanSizeLookup(factoryList)
        if (adapter == null || adapter !is ComplexAdapter) {
            adapter = ComplexAdapter()
        }
        (adapter as ComplexAdapter).updateFactoryList(factoryList)
    }

    private fun initSpanSizeLookup(factoryList: ArrayList<in ComplexAdapter.Factory<Any>>): GridLayoutManager.SpanSizeLookup =
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val pair =
                    ComplexUtility.getPositionWithSum(
                        factoryList,
                        position
                    )
                return ((factoryList[pair.first] as ComplexAdapter.Factory<*>).getColumnSizeByItem(
                    pair.second
                ) * 10).toInt()
            }
        }
}
