package com.smile.talent_official.ui.main_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.smile.talent_official.R
import com.smile.talent_official.adapter.TalentAdapter
import com.smile.talent_official.databinding.FragmentMainBinding
import com.smile.talent_official.ui.start.StartViewModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
//    lateinit var recyclerView: RecyclerView
//    lateinit var adapter: TalentAdapter
//
//    private lateinit var viewModel: StartViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {

        binding.btnFindTalent.setOnClickListener {
            findNavController().navigate(R.id.startFragment)
        }

        binding.btnAddProfile.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_workerFragment)
        }
    }


}