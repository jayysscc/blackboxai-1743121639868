package com.example.scheduler.teacher.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.scheduler.R
import com.example.scheduler.databinding.DialogAddAvailabilityBinding
import com.example.scheduler.teacher.models.AvailabilitySlot
import com.example.scheduler.teacher.viewmodels.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddAvailabilityDialog : DialogFragment() {
    private var _binding: DialogAddAvailabilityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ScheduleViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddAvailabilityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupUi()
    }

    private fun setupUi() {
        dialog?.setTitle(R.string.add_availability)
        binding.tilDuration.hint = getString(R.string.duration_minutes)
    }

    private fun setupClickListeners() {
        binding.btnSelectDate.setOnClickListener { showDatePicker() }
        binding.btnSelectTime.setOnClickListener { showTimePicker() }
        binding.btnSave.setOnClickListener { saveAvailabilitySlot() }
        binding.btnCancel.setOnClickListener { dismiss() }
    }

    private fun showDatePicker() {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(year, month, day)
                binding.tvSelectedDate.text = getString(R.string.date_format, day, month + 1, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                binding.tvSelectedTime.text = getString(R.string.time_format, hour, minute)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun saveAvailabilitySlot() {
        val teacherId = "teacher123" // TODO: Get actual teacher ID from auth
        val duration = binding.etDuration.text.toString()
        
        if (duration.isNotEmpty()) {
            val slot = AvailabilitySlot(
                date = binding.tvSelectedDate.text.toString(),
                time = binding.tvSelectedTime.text.toString(),
                duration = "$duration minutes",
                teacherId = teacherId
            )
            viewModel.addAvailabilitySlot(slot)
            dismiss()
        } else {
            binding.tilDuration.error = getString(R.string.error_duration_empty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}