package com.example.listapp2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersModel (
    val username: String?,
    val name: String?,
    val avatar: String?,
    val company: String?,
    val location: String?,
    val repository: Int?,
    val follower: Int?,
    val following: Int?
) : Parcelable