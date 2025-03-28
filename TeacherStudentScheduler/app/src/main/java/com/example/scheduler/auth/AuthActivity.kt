package com.example.scheduler.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scheduler.databinding.ActivityAuthBinding
import com.example.scheduler.teacher.TeacherLoginActivity
import com.example.scheduler.student.StudentLoginActivity
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        checkPermissions()
    }

    private fun setupClickListeners() {
        binding.teacherButton.setOnClickListener {
            startActivity(Intent(this, TeacherLoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.studentButton.setOnClickListener {
            startActivity(Intent(this, StudentLoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun checkPermissions() {
        // TODO: Implement runtime permission checks
        // for calendar, notifications, etc.
    }

    private fun showPermissionSnackbar() {
        Snackbar.make(binding.root, 
            R.string.permission_explanation, 
            Snackbar.LENGTH_LONG)
            .setAction(R.string.permission_required) {
                // Request permissions
            }
            .show()
    }
}