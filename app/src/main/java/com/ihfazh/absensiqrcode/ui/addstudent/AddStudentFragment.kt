package com.ihfazh.absensiqrcode.ui.addstudent

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ihfazh.absensiqrcode.databinding.FragmentAddStudentBinding
import Event.Student
import com.ihfazh.absensiqrcode.ui.DisposableFragment
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import java.util.*

@AndroidEntryPoint
class AddStudentFragment : DisposableFragment() {
    private lateinit var binding: FragmentAddStudentBinding
    private val viewModel: AddStudentViewModel by viewModels()

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            view.findNavController().navigateUp()
        }

        binding.btnSave.setOnClickListener{
            saveStudent()
        }

        val firstNameStream = RxTextView.textChanges(binding.etFullName)
                .map { it.toString().isNotEmpty() }

        val lastNameStream = RxTextView.textChanges(binding.etLastName)
                .map { it.toString().isNotEmpty() }

        val invalidValue = Observable.combineLatest(
                firstNameStream,
                lastNameStream
        ) {
            firstName, lastName -> firstName && lastName
        }

        val disposable = invalidValue.subscribe {
            binding.btnSave.isEnabled = it
        }

        compositeDisposable.add(disposable)

    }

    private fun saveStudent() {
        val firstName = binding.etFullName.text.toString()
        val lastName = binding.etLastName.text.toString()

        val studentID = UUID.randomUUID().toString()

        val student = Student(studentID, firstName, lastName)

        val disposable = viewModel.addStudent(student)
            .subscribe({
            findNavController().navigateUp()
        }){
            Toast.makeText(this.context, "Something wrong with the database.", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Error on save student", it)
        }

        compositeDisposable.add(disposable)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddStudentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        private const val TAG = "ADD STUDENT FRAGMENT"
    }
}