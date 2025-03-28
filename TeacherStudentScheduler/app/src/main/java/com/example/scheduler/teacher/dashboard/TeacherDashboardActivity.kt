package com.example.scheduler.teacher.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import com.example.scheduler.R
import com.example.scheduler.base.BaseActivity
import com.example.scheduler.databinding.ActivityTeacherDashboardBinding
import com.example.scheduler.teacher.viewmodels.TeacherDashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherDashboardActivity : BaseActivity<ActivityTeacherDashboardBinding>() {
    private val viewModel: TeacherDashboardViewModel by viewModels()

    override fun getViewBinding() = ActivityTeacherDashboardBinding.inflate(layoutInflater)

    override fun setupViews() {
        setupBottomNavigation()
        setupFloatingActionButton()
        observeViewModel()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavView.apply {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.nav_schedule -> showScheduleFragment()
                    R.id.nav_meetings -> showMeetingsFragment()
                    R.id.nav_settings -> showSettingsFragment()
                }
                true
            }
            selectedItemId = R.id.nav_schedule
        }
    }

    private fun setupFloatingActionButton() {
        binding.fabAddAvailability.setOnClickListener {
            supportFragmentManager.findFragmentByTag("schedule")?.let { fragment ->
                (fragment as? ScheduleFragment)?.showAddAvailabilityDialog()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.availability.observe(this) { availability ->
            // Update UI with availability slots
        }
        
        viewModel.meetingRequests.observe(this) { requests ->
            // Update meeting requests list
        }
    }

    private fun showScheduleFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ScheduleFragment.newInstance())
            .commit()
    }

    private fun showMeetingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MeetingsFragment.newInstance())
            .commit()
    }

    private fun showSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment.newInstance())
            .commit()
    }

    private fun showAddAvailabilityDialog() {
        AddAvailabilityDialog().show(supportFragmentManager, "AddAvailabilityDialog")
    }
}