package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robbie.rock_scissors_paper_appchallenge_chapter5.databinding.FragmentFormLoginBinding
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.menu.GameMenu


class FormLogin : Fragment() {
    private lateinit var binding: FragmentFormLoginBinding
    private lateinit var listener: FormFragmentListener
    private lateinit var userPreference: GetName



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFormLoginBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefilledCurrentName()
        binding.btnInputName.setOnClickListener {
            if (this::listener.isInitialized) {
                listener.onNameSubmitted(binding.etInputName.text.toString())
                saveSetName()
            }
        }

    }
    private fun prefilledCurrentName() {
        context?.let {
            userPreference = GetName(it)
            binding.etInputName.setText(userPreference.userName.orEmpty())// or empty == ""
        }
    }

    private fun saveSetName() {
        if (isInputName()) {
            userPreference.userName = binding.etInputName.text.toString()
            context?.startActivity(Intent(context, GameMenu::class.java))
        }
    }

    private fun isInputName(): Boolean {
        val name = binding.etInputName.text.toString()
        var isFormValid = true
        if (name.isEmpty()) {
            isFormValid = false
            binding.tillInputName.isErrorEnabled = true
            binding.tillInputName.error = "Task Name must be Filled !"
        } else {
            binding.tillInputName.isErrorEnabled = false
        }
        return isFormValid
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FormFragmentListener)
            listener = context
    }


}