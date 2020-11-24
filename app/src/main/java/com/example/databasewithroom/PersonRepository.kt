package com.example.databasewithroom

import androidx.lifecycle.LiveData
import com.example.databasewithroom.model.Person

class PersonRepository(private val personDao: PersonDao) {

    val readAllData: LiveData<List<Person>> = personDao.getAll(true)

    suspend fun addPerson(person: Person) {
        personDao.addPerson(person)
    }

    suspend fun updatePerson(person: Person) {
        personDao.updateData(person)
    }

    suspend fun deletePerson(person: Person) {
        personDao.deleteData(person)
    }


}