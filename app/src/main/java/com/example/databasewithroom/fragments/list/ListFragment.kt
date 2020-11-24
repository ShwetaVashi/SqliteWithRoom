package com.example.databasewithroom.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databasewithroom.PersonViewModel
import com.example.databasewithroom.R
import com.example.databasewithroom.adapter.ListItemAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mPersonViewModel: PersonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListItemAdapter()
        view.rvList.adapter = adapter
        view.rvList.layoutManager = LinearLayoutManager(requireContext())

        mPersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        mPersonViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)

        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}