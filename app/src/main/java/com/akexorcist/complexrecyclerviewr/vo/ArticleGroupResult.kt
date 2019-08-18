package com.akexorcist.complexrecyclerviewr.vo

import android.os.Parcel
import android.os.Parcelable

data class ArticleGroupResult(
    val groupList: List<Group>?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Group))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(groupList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleGroupResult> {
        override fun createFromParcel(parcel: Parcel): ArticleGroupResult {
            return ArticleGroupResult(parcel)
        }

        override fun newArray(size: Int): Array<ArticleGroupResult?> {
            return arrayOfNulls(size)
        }
    }

    data class Group(
        var id: String?,
        var name: String?,
        var topicList: ArrayList<Topic>?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(Topic)
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeTypedList(topicList)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Group> {
            override fun createFromParcel(parcel: Parcel): Group {
                return Group(parcel)
            }

            override fun newArray(size: Int): Array<Group?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Topic(
        var id: String?,
        var title: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(title)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Topic> {
            override fun createFromParcel(parcel: Parcel): Topic {
                return Topic(parcel)
            }

            override fun newArray(size: Int): Array<Topic?> {
                return arrayOfNulls(size)
            }
        }
    }
}
