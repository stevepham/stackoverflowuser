package com.ht117.sofossill.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class UserModel(@Json(name = "user_id") val userId: Long,
                     @Json(name ="display_name") val displayName: String?,
                     @Json(name = "profile_image") val profileImage: String?,
                     @Json(name = "reputation") val reputation: Long?,
                     @Json(name = "location") val location: String?,
                     @Json(name = "last_access_date") val lastAccessDate: Long?,
                     @Volatile var isBookmarked: Boolean = false): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeString(displayName)
        parcel.writeString(profileImage)
        parcel.writeValue(reputation)
        parcel.writeString(location)
        parcel.writeValue(lastAccessDate)
        parcel.writeByte(if (isBookmarked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}