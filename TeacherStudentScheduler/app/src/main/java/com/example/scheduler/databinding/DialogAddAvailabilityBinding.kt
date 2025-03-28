package com.example.scheduler.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.scheduler.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DialogAddAvailabilityBinding private constructor(
    private val rootView: View
) : ViewBinding {
    val root: View = rootView
    val btnSelectDate: MaterialButton = rootView.findViewById(R.id.btnSelectDate)
    val tvSelectedDate: View = rootView.findViewById(R.id.tvSelectedDate)
    val btnSelectTime: MaterialButton = rootView.findViewById(R.id.btnSelectTime)
    val tvSelectedTime: View = rootView.findViewById(R.id.tvSelectedTime)
    val tilDuration: TextInputLayout = rootView.findViewById(R.id.tilDuration)
    val etDuration: TextInputEditText = rootView.findViewById(R.id.tilDuration)
    val btnSave: MaterialButton = rootView.findViewById(R.id.btnSave)
    val btnCancel: MaterialButton = rootView.findViewById(R.id.btnCancel)

    companion object {
        fun inflate(
            inflater: LayoutInflater,
            parent: ViewGroup?,
            attachToParent: Boolean
        ): DialogAddAvailabilityBinding {
            val root = inflater.inflate(
                R.layout.dialog_add_availability,
                parent,
                attachToParent
            )
            return DialogAddAvailabilityBinding(root)
        }
    }

    override fun getRoot(): View = root
}