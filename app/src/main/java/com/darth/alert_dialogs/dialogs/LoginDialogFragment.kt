package com.darth.alert_dialogs.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.darth.alert_dialogs.R
import com.darth.alert_dialogs.databinding.FragmentLoginDialogBinding


class LoginDialogFragment : DialogFragment() {

    private var _binding : FragmentLoginDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show()
            } else {
                performLogin(username, password)
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun performLogin(username: String, password: String) {
        // Perform login logic here

        // Show success message
        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}