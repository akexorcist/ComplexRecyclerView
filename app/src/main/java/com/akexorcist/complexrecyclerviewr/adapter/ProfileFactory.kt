package com.akexorcist.complexrecyclerviewr.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.complexrecyclerviewr.R
import com.akexorcist.complexrecyclerviewr.adapter.profile.ProfileItem
import com.akexorcist.complexrecyclerviewr.adapter.profile.ProfileState
import com.akexorcist.complexrecyclerviewr.adapter.profile.ProfileViewHolder
import com.akexorcist.complexrecyclerviewr.vo.Profile
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter
import com.akexorcist.library.complexrecyclerview.core.ViewTypeGenerator
import com.akexorcist.library.complexrecyclerview.state.StateHandler

class ProfileFactory(
    stateHandler: StateHandler
) : ComplexAdapter.Factory<Profile>(stateHandler) {
    override fun build(
        data: Profile,
        oldItemList: ArrayList<in ComplexAdapter.Item<Profile, ComplexAdapter.State, RecyclerView.ViewHolder>>?
    ): ArrayList<in ComplexAdapter.Item<Profile, ComplexAdapter.State, RecyclerView.ViewHolder>> {
        return arrayListOf(
            ProfileItem(data, ProfileState(), ProfileViewHolder::class.java)
        )
    }

    override fun getDataClass(position: Int): Class<out Any> = when (itemList[position]) {
        is ProfileItem -> ProfileItem::class.java
        else -> ProfileItem::class.java
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        return when {
            ViewTypeGenerator.validate(viewType, ProfileItem::class.java, this) ->
                ProfileViewHolder(inflater.inflate(R.layout.item_profile, parent, false))
            else ->
                null
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item: Any? = itemList[position]) {
            is ProfileItem -> setupProfileView(holder as ProfileViewHolder, item)
        }
    }

    override fun onSaveState(state: Bundle) {
    }

    override fun onRestoreState(state: Bundle) {
    }

    private fun setupProfileView(holder: ProfileViewHolder, item: ProfileItem) {
        holder.setName(item.data?.name)
        holder.setJob(item.data?.job)
    }
}
