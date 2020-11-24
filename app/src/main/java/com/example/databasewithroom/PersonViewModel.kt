package com.example.databasewithroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.databasewithroom.database.PersonDatabase
import com.example.databasewithroom.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Person>>
    private val personRepository: PersonRepository

    init {
        val personDao = PersonDatabase.getDatabase(application).personDao()
        personRepository = PersonRepository(personDao)
        readAllData = personRepository.readAllData
    }

    fun addPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.addPerson(person)
        }
    }

    fun updatePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.updatePerson(person)
        }
    }

    fun deletePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePerson(person)
        }
    }
}