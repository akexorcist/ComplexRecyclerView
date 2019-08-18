package com.akexorcist.complexrecyclerviewr.adapter.profile

import com.akexorcist.complexrecyclerviewr.vo.Profile
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter

class ProfileItem(
    data: Profile?,
    state: ProfileState,
    viewHolder: Class<ProfileViewHolder>
) : ComplexAdapter.Item<Profile?, ProfileState, ProfileViewHolder>(
    data,
    state,
    viewHolder
)
