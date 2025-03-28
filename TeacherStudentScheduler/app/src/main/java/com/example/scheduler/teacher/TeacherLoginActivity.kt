package com.example.scheduler.teacher

import android.content.Intent
import android.os.Bundle
import com.example.scheduler.R
import com.example.scheduler.base.BaseActivity
import com.example.scheduler.databinding.ActivityTeacherLoginBinding
import com.example.scheduler.teacher.dashboard.TeacherDashboardActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TeacherLoginActivity : BaseActivity<ActivityTeacherLoginBinding>() {
    private lateinit var auth: FirebaseAuth

    override fun getViewBinding() = ActivityTeacherLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun setupViews() {
        binding.loginButton.setOnClickListener { attemptLogin() }
        binding.registerButton.setOnClickListener { navigateToRegistration() }
        binding.forgotPassword.setOnClickListener { showPasswordReset() }
    }

    private fun attemptLogin() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (validateInput(email, password)) {
            showLoading(true)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    showLoading(false)
                    if (task.isSuccessful) {
                        navigateToDashboard()
                    } else {
                        showError(getString(R.string.error_login_failed))
                    }
                }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true
        
        if (email.isEmpty()) {
            binding.emailInput.error = getString(R.string.error_email_empty)
            isValid = false
        }

        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.error_password_empty)
            isValid = false
        }

        return isValid
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, TeacherDashboardActivity::class.java))
        finish()
    }

    private fun navigateToRegistration() {
        // TODO: Implement registration navigation
    }

    private fun showPasswordReset() {
        // TODO: Implement password reset
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}