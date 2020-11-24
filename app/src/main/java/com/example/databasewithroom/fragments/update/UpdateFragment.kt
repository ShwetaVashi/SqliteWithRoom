package com.example.databasewithroom.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.databasewithroom.PersonViewModel
import com.example.databasewithroom.R
import com.example.databasewithroom.model.Person
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mPersonViewModel: PersonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mPersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        view.etUpdateFname.setText(args.currentPerson.firstName)
        view.etUpdateLname.setText(args.currentPerson.lastName)

        view.btnUpdate.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return view

    }

    private fun updateItem() {
        val firstName = etUpdateFname.text.toString()
        val lastName = etUpdateLname.text.toString()
        if (inputCheck(firstName, lastName)) {
            val updatedPerson = Person(args.currentPerson.id, firstName, lastName)
            mPersonViewModel.updatePerson(updatedPerson)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        } else {
            Toast.makeText(requireContext(), "Enter all details", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.deletemenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deletePerson()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePerson() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mPersonViewModel.deletePerson(args.currentPerson)
            Toast.makeText(requireContext(), "Successfully Deleted ${args.currentPerson.firstName}", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ ->
        }
        builder.setTitle("Delete ${args.currentPerson.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentPerson.firstName}?")
        builder.create().show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
    }
}