package com.deekho.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deekho.app.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }




}