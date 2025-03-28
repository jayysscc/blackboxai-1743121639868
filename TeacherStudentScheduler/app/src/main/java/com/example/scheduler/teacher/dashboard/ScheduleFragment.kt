package com.example.scheduler.teacher.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.example.scheduler.databinding.FragmentScheduleBinding
import com.example.scheduler.teacher.adapters.AvailabilityAdapter
import com.example.scheduler.teacher.viewmodels.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var availabilityAdapter: AvailabilityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        availabilityAdapter = AvailabilityAdapter { slot ->
            // Handle slot click
        }
        
        binding.rvAvailability.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = availabilityAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewModel.availabilitySlots.observe(viewLifecycleOwner) { slots ->
            availabilityAdapter.submitList(slots)
            binding.emptyView.visibility = if (slots.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAddAvailabilityDialog() {
        AddAvailabilityDialog().show(childFragmentManager, "AddAvailabilityDialog")
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }
}