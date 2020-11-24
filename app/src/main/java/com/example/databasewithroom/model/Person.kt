package com.example.databasewithroom.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_person")
data class Person(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
//
//    //region fields
//    @ColumnInfo(name = "name")
//    private var name: String? = null
//
//    @ColumnInfo(name = "city")
//    private var city: String? = null
//    //endregion
) : Parcelable