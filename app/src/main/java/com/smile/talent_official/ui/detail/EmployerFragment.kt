package com.smile.talent_official.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smile.talent_official.databinding.FragmentEmployerBinding
import com.smile.talent_official.data.models.TalentModel

class EmployerFragment : Fragment() {

    lateinit var binding: FragmentEmployerBinding
    lateinit var currentTalent: TalentModel
    private lateinit var viewModel: EmployerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerBinding.inflate(layoutInflater, container, false)
        currentTalent = arguments?.getSerializable("talent") as TalentModel
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EmployerViewModel::class.java)

        init()

//        observeViewModel()

    }

    private fun init() {
        binding.nameTv.text = currentTalent.name
        binding.specialistTv.text = currentTalent.specialization
        binding.aboutMeTv.text = currentTalent.aboutMe
        binding.profileImageTv.setImageResource(currentTalent.picture)
        binding.btnBackProfile.setOnClickListener {
            findNavController().popBackStack()
        }



        binding.btnDelete.setOnClickListener {

        }
//
//        binding.btnBack.setOnClickListener {
//            findNavController().navigate(R.id.action_detailFragment_to_startFragment)
//        }
    }

//    private fun observeViewModel() {
//        viewModel.popBackStack.observe(
//            viewLifecycleOwner,
//            {
//                it.getContentIfNotHandled()?.let {
//                    findNavController().popBackStack()
//                }
//            }
//        )
//    }
}