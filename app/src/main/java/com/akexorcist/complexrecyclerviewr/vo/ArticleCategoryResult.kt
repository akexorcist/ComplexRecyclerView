package com.akexorcist.complexrecyclerviewr.vo

import android.os.Parcel
import android.os.Parcelable

data class ArticleCategoryResult(
    val categoryList: List<Category>?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Category))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(categoryList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleCategoryResult> {
        override fun createFromParcel(parcel: Parcel): ArticleCategoryResult {
            return ArticleCategoryResult(parcel)
        }

        override fun newArray(size: Int): Array<ArticleCategoryResult?> {
            return arrayOfNulls(size)
        }
    }

    data class Category(
        var id: String?,
        var name: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Category> {
            override fun createFromParcel(parcel: Parcel): Category {
                return Category(parcel)
            }

            override fun newArray(size: Int): Array<Category?> {
                return arrayOfNulls(size)
            }
        }
    }
}
