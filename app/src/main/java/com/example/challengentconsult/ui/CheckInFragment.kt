package com.example.challengentconsult.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengentconsult.R
import com.example.challengentconsult.databinding.FragmentCheckInBinding
import com.example.challengentconsult.model.CheckInModel
import com.example.challengentconsult.viewmodel.CheckInViewModel

class CheckInFragment : Fragment() {
    private var _binding: FragmentCheckInBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: CheckInViewModel
    private val args: CheckInFragmentArgs by navArgs()

    private var checkInModel = CheckInModel("", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckInBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListener()

        return view
    }

    private fun setListener() {
        binding.backCheckIn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonMakeCheckin.setOnClickListener {
            checkInModel.id     = args.eventCheckIn.id.toString()
            checkInModel.name   = binding.checkName.text.toString()
            checkInModel.email  = binding.checkEmail.text.toString()

            viewModel.apply {
                this.postCheckIn(checkInModel = checkInModel).observe(viewLifecycleOwner){

                }
            }
        }
    }

    private fun setView() {
        binding.textTitleCheckIn.text = args.eventCheckIn.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}