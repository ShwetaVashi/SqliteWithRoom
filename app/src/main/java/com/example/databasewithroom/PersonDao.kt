package com.example.databasewithroom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.databasewithroom.model.Person


@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //will ignore same user
    suspend fun addPerson(person: Person)

    @Query("SELECT * FROM tbl_person ORDER BY CASE WHEN :isAsc = 1 THEN firstName END ASC," +
            "CASE WHEN :isAsc = 0 THEN firstName END DESC")
    fun getAll(isAsc: Boolean): LiveData<List<Person>>

    @Update
    suspend fun updateData(person: Person)

    @Delete
    suspend fun deleteData(person: Person)
}