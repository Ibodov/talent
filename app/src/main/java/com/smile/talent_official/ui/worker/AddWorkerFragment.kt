package com.smile.talent_official.ui.worker

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smile.talent_official.R
import com.smile.talent_official.databinding.FragmentWorkerBinding
import com.smile.talent_official.data.models.TalentModel
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.smile.talent_official.BuildConfig
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


const val REQUEST_CODE = 24


class AddWorkerFragment : Fragment() {


    lateinit var binding: FragmentWorkerBinding
    private lateinit var viewModelAdd: AddWorkerViewModel

    val GALLERY_REQUEST = 1

    val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                previewImage.setImageURI(uri)
            }
        }

    private val requestMultiplePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultsMap ->
            if (allPermissionsGranted()) {
                takePicture()
            }
        }

    var file: File? = null
    var uri: Uri? = null

    private val previewImage by lazy { requireActivity().findViewById<ImageView>(R.id.profile_image) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelAdd = ViewModelProvider(this).get(AddWorkerViewModel::class.java)

        init()

        observeViewModel()

    }

    private fun init() {
        binding.btnAddProfile2.setOnClickListener {
            val specialistName = binding.etAddName.text.toString()
            val specialization = binding.etAddSpecialization.text.toString()
            var uriStr = if (uri != null) uri.toString() else ""
            viewModelAdd.insert(
                TalentModel(
                    name = specialistName,
                    specialization = specialization,
                    uri = uriStr
                )
            )

        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_workerFragment_to_startFragment2)
        }


        binding.btnChooseImage.setOnClickListener {
            if (allPermissionsGranted()) {
                takePicture()
            } else {
                requestMultiplePermissionLauncher.launch(REQUIRED_PERMISSIONS)
            }
        }
    }

    private fun takePicture() {
        file = createImageFile()
        uri = FileProvider.getUriForFile(
            requireContext(),
            BuildConfig.APPLICATION_ID + ".provider", file!!
        )

        takePictureLauncher.launch(uri)
    }

    fun observeViewModel() {
        viewModelAdd.popBackStack.observe(
            viewLifecycleOwner,
            {
                it.getContentIfNotHandled()?.let {
//                    findNavController().navigate(R.id.action_workerFragment_to_startFragment2)
//                    findNavController().popBackStack()
                    clickTalent(talentModel = TalentModel())
                }
            }
        )
    }

    private fun clickTalent(talentModel: TalentModel) {
        val bundle = Bundle()
        bundle.putSerializable("talent", talentModel)
        findNavController().navigate(R.id.startFragment, bundle)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && requestCode == Activity.RESULT_OK) {


            val image = data?.extras?.get("data") as Bitmap
            binding.profileImage.setImageBitmap(image)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    fun createImageFile(): File {
        val timeStamp = System.currentTimeMillis()
        val storageDir = getOutputDirectory()

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, ".tmp").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS =
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    }
}
