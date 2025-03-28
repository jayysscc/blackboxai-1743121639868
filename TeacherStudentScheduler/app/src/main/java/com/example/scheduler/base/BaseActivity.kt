package com.example.scheduler.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupViews()
        setupObservers()
    }

    abstract fun getViewBinding(): VB
    abstract fun setupViews()
    open fun setupObservers() {}

    protected fun showLoading(show: Boolean) {
        // TODO: Implement loading state management
    }

    protected fun showError(message: String) {
        // TODO: Implement error handling
    }
}