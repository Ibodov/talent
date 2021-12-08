package com.smile.talent_official.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.smile.talent_official.R
import com.smile.talent_official.adapter.TalentAdapter
import com.smile.talent_official.databinding.FragmentStartBinding
import com.smile.talent_official.data.models.TalentModel

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TalentAdapter

    private lateinit var viewModel: StartViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartViewModel::class.java)

        init()

        observeViewModel()
    }

    private fun init() {
        recyclerView = binding.rvTalents
        adapter = TalentAdapter(viewModel.recyclerViewCallback)
        recyclerView.adapter = adapter

    }

    fun observeViewModel() {
        viewModel.openTalent.observe(
            viewLifecycleOwner,
            {
                it.getContentIfNotHandled()?.let {
                    clickNote(it)
                }
            }
        )
        viewModel.getAllResource.observe(
            viewLifecycleOwner,
            { listTalents ->  //this заменяем на viewLifecycleOwner
//            listNotes.asReversed() //в начале списка помещается новая запись
                adapter.setList(listTalents.asReversed())
            }
        )

        viewModel.getAllNumbersResource.observe(
            viewLifecycleOwner,
            {
                viewModel.refresh()
            }
        )

    }

    fun clickNote(talentModel: TalentModel) {
        val bundle = Bundle()
        bundle.putSerializable("talent", talentModel)
        findNavController().navigate(R.id.action_startFragment_to_employerFragment, bundle)
    }

}