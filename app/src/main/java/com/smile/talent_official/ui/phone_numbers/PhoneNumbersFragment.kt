package com.smile.talent_official.ui.phone_numbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smile.talent_official.R
import com.smile.talent_official.databinding.FragmentPhoneNumbersBinding
import com.smile.talent_official.ui.detail.EmployerViewModel


class PhoneNumbersFragment : Fragment() {

    private lateinit var binding: FragmentPhoneNumbersBinding
    private lateinit var viewModel: PhoneNumbersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneNumbersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PhoneNumbersViewModel::class.java)

        init()
        observeViewModel()

    }

    private fun init() {
        binding.recyclerView.adapter = PhoneNumbersAdapter(emptyList())

    }

    private fun observeViewModel() {
        viewModel.getAllNumbersResource.observe(
            viewLifecycleOwner,
            {
                (binding.recyclerView.adapter as PhoneNumbersAdapter).submitList(it)
            }
        )
    }

}