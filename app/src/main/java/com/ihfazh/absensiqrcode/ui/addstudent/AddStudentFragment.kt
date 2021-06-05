package com.ihfazh.absensiqrcode.ui.addstudent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ihfazh.absensiqrcode.databinding.FragmentAddStudentBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.internal.disposables.DisposableContainer

class AddStudentFragment : Fragment() {
    private lateinit var binding: FragmentAddStudentBinding
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            view.findNavController().navigateUp()
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
}