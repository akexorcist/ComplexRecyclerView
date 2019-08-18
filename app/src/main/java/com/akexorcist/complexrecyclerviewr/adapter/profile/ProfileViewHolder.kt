package com.akexorcist.complexrecyclerviewr.adapter.profile

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_profile.*

class ProfileViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun setName(name: String?) {
        textViewName.text = name ?: "-"
    }

    fun setJob(job: String?) {
        textViewJob.text = job ?: "-"
    }
}
