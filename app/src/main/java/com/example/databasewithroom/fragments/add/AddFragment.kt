package com.example.databasewithroom.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.databasewithroom.PersonViewModel
import com.example.databasewithroom.R
import com.example.databasewithroom.model.Person
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mPersonViewModel: PersonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mPersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        view.btnSave.setOnClickListener {
            insertData()
        }
        return view
    }

    private fun insertData() {
        val firstName = etFname.text.toString()
        val lastName = etLname.text.toString()

        if (inputCheck(firstName, lastName)) {
            val person = Person(0, firstName, lastName)
            mPersonViewModel.addPerson(person)
            Toast.makeText(requireContext(), "Inserted!!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all the details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }
}