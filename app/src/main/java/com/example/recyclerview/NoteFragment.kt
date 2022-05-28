package com.example.recyclerview

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.FragmentNoteBinding


class NoteFragment : Fragment(R.layout.fragment_note), OnItemClick {
    private lateinit var binding: FragmentNoteBinding
    private val sharedPreferences: SharedPreferences =
        requireContext().getSharedPreferences("note", Context.MODE_PRIVATE)
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val notes = mutableListOf<Note>()
        sharedPreferences.all.forEach {
            notes.add(Note(it.key, it.value.toString()))

        }
        recyclerViewAdapter = RecyclerViewAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.loadItems(notes)
        binding.button.setOnClickListener {

            findNavController().navigate(R.id.action_noteFragment_to_detailsFragment)

        }



        return view
    }

    override fun onClick(title: String) {
        val action = NoteFragmentDirections.actionNoteFragmentToDetailsFragment(title)
        findNavController().navigate(action)
    }
}