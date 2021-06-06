package com.ihfazh.absensiqrcode.ui.addevent

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentAddEventBinding
import com.ihfazh.absensiqrcode.ui.DisposableFragment
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import java.util.*

class AddEventFragment : DisposableFragment() {
    private lateinit var binding : FragmentAddEventBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener{
            view.findNavController().navigateUp()
        }

        val titleStream = RxTextView.textChanges(binding.etTitle).map{
            it.toString().isNotEmpty()
        }

        val dateStream = RxTextView.textChanges(binding.etDate).map{
            it.toString().isNotEmpty()
        }

        val timeStream = RxTextView.textChanges(binding.etTime).map {
            it.toString().isNotEmpty()
        }

        val invalidValue = Observables.combineLatest(
            titleStream,
            dateStream,
            timeStream
        ) {
            title, date, time -> title && date && time
        }

        val disposable = invalidValue.subscribe{
            binding.btnSave.isEnabled = it
        }

        compositeDisposable.add(disposable)

        val cal = Calendar.getInstance()
        binding.etDate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                DatePickerDialog(
                    requireContext(),
                    {
                            _, year, month, day ->  binding.etDate.setText("$year/$month/$day")

                    }
                    ,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DATE)).apply {
                    show()
                }
            }
        }

        binding.etTime.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                TimePickerDialog(
                    requireContext(),
                    {
                       _, hour, minute -> binding.etTime.setText("$hour:$minute")
                    },
                    cal.get(Calendar.HOUR),
                    cal.get(Calendar.MINUTE),
                    true
                ).apply {
                    show()
                }

            }
        }

        binding.btnSave.setOnClickListener{
            saveEvent()
        }

    }

    private fun saveEvent() {
        Log.d(TAG, "Event save.....")
    }

    companion object {
        const val TAG = "ADD FRAGMENT"
    }
}