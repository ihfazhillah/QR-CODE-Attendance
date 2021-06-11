package com.ihfazh.absensiqrcode.ui.liststudents

import Event.Student
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentListStudentBinding
import com.ihfazh.absensiqrcode.ui.DisposableFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ListStudentFragment : DisposableFragment(), ListStudentAdapter.OnStudentItemClicked {
    private lateinit var binding: FragmentListStudentBinding
    private val viewModel: ListStudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onItemClicked(student: Student) {
        val action =
            ListStudentFragmentDirections.actionListStudentFragment2ToDetailStudentFragment(student.studentId)
        requireView().findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = ListStudentAdapter().apply {
            clickListener = this@ListStudentFragment
        }
        with(binding.rvStudents) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getStudents().observe(viewLifecycleOwner) {
            mAdapter.submitData(lifecycle, it)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_users_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.export_all -> {
                if (canSaveToStorage()){
                    exportAllDataToCSV()
                } else {
                    requestPermissionLauncher.launch(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exportAllDataToCSV() {
        requireContext().getExternalFilesDir(null)?.let{ downloadFile ->
            val path = downloadFile.absolutePath + "/testing.csv"
            val disposable = viewModel.exportStudentsData(path)
            {
                // https://stackoverflow.com/questions/56598480/couldnt-find-meta-data-for-provider-with-authority
                Log.d(TAG, "exportAllDataToCSV: COMPLET ")
                val intent = Intent(Intent.ACTION_VIEW)
                intent.type = "application/csv"
                intent.data = FileProvider.getUriForFile(requireContext(), requireContext().applicationContext.packageName + ".provider", File(path))
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivity(intent)
            }
            compositeDisposable.add(disposable)
        } ?: run{
            Log.d(TAG, "exportAllDataToCSV: something wrong")
        }
    }

    companion object {
        const val TAG = "List Student View"
        val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun canSaveToStorage(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                    requireContext(),
                    it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if (isGranted){
            exportAllDataToCSV()
        } else {
            Toast.makeText(context, "Cannot save into storage, no permission", Toast.LENGTH_SHORT).show()
        }
    }

}