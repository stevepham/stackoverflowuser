package com.ht117.sofossill.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "display_name") val displayName: String?,
    @ColumnInfo(name = "profile_image") val profileImage: String?,
    @ColumnInfo(name = "reputation") val reputation: Long?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "last_access_date") val lastAccessDate: Long?,
    @ColumnInfo(name = "book_marked") val isBookmarked: Boolean)