package com.example.recyclerview

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recyclerview.databinding.FragmentDetailsBinding
import com.example.recyclerview.databinding.FragmentNoteBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {
    val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private val sharedPreferences: SharedPreferences =
        requireContext().getSharedPreferences("note", Context.MODE_PRIVATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        if(args.tag != "None"){
            binding.titleID.setText(args.tag)
            binding.textID.setText(sharedPreferences.getString(args.tag, "default").toString())
        }
        val title = binding.titleID.text.toString()
        val text = binding.textID.text.toString()

        binding.button2.setOnClickListener {
            sharedPreferences.edit().putString(title, text).apply()
            findNavController().navigate(R.id.action_detailsFragment_to_noteFragment)

        }

        return view
    }


}