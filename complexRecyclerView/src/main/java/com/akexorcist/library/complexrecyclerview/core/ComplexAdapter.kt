package com.akexorcist.library.complexrecyclerview.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.library.complexrecyclerview.state.StateChangedObserver
import com.akexorcist.library.complexrecyclerview.state.StateHandler
import java.util.*

class ComplexAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var factoryList: ArrayList<in Factory<Any>> = arrayListOf()

    fun updateFactoryList(factoryList: ArrayList<in Factory<Any>>) {
        this.factoryList = factoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        factoryList.forEach { factory ->
            val viewHolder = (factory as Factory<*>).onCreateViewHolder(
                LayoutInflater.from(parent.context),
                parent,
                viewType
            )
            if (viewHolder != null) {
                return viewHolder
            }
        }
        throw NullPointerException("View type for ${ViewTypeGenerator.getKey(viewType)} not found.")
    }

    override fun getItemViewType(position: Int): Int {
        val pair = ComplexUtility.getPositionWithSum(factoryList, position)
        return ViewTypeGenerator.getValue(
            (factoryList[pair.first] as Factory<*>).getDataClass(pair.second),
            (factoryList[pair.first] as Factory<*>).javaClass
        )
    }

    override fun getItemCount(): Int =
        factoryList.sumBy { factory ->
            (factory as Factory<*>).getCount()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pair = ComplexUtility.getPositionWithSum(factoryList, position)
        (factoryList[pair.first] as Factory<*>).onBindViewHolder(holder, pair.second)
    }

    open class Item<DATA, STATE : State, VH : RecyclerView.ViewHolder>(
        open var data: DATA,
        open var state: STATE,
        open var viewHolder: Class<VH>
    )

    abstract class State

    abstract class Factory<DATA> constructor(
        stateHandler: StateHandler
    ) : StateChangedObserver {
        @Suppress("MemberVisibilityCanBePrivate")
        protected var data: DATA? = null
        @Suppress("MemberVisibilityCanBePrivate")
        protected var itemList: ArrayList<in Item<DATA, State, RecyclerView.ViewHolder>> = arrayListOf()

        init {
            @Suppress("LeakingThis")
            stateHandler.addObserver(this)
        }

        @Suppress("unused")
        fun update(
            data: DATA,
            oldItemList: ArrayList<in Item<DATA, State, RecyclerView.ViewHolder>>? = null
        ) {
            this.data = data
            this.itemList = build(data, oldItemList)
        }

        fun refresh() {
            this.data?.let { data ->
                this.itemList = build(data, this.itemList)
            }
        }

        fun getStateList() = itemList

        fun getColumnSizeByItem(position: Int): Float {
            return getColumnCount(itemList[position])
        }

        open fun getCount(): Int = itemList.size

        open fun getColumnCount(item: Any?): Float {
            return 100f
        }

        protected abstract fun build(
            data: DATA,
            oldItemList: ArrayList<in Item<DATA, State, RecyclerView.ViewHolder>>? = null
        ): ArrayList<in Item<DATA, State, RecyclerView.ViewHolder>>

        abstract fun getDataClass(position: Int): Class<out Any>

        abstract fun onCreateViewHolder(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder?

        abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    }
}
