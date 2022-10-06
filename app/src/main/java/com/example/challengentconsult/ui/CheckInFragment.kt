package com.example.challengentconsult.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengentconsult.R
import com.example.challengentconsult.databinding.FragmentCheckInBinding
import com.example.challengentconsult.model.CheckInModel
import com.example.challengentconsult.viewmodel.CheckInViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInFragment : Fragment() {
    private var _binding: FragmentCheckInBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: CheckInViewModel by viewModel()
    private val args: CheckInFragmentArgs by navArgs()

    private var checkInModel = CheckInModel("", "", "")

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

    private fun setView() {
        binding.textTitleCheckIn.text = args.eventCheckIn.title
    }

    private fun setListener() {
        binding.backCheckIn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonMakeCheckin.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            checkInModel.id                 = args.eventCheckIn.id.toString()
            checkInModel.name               = binding.checkName.text.toString()
            checkInModel.email              = binding.checkEmail.text.toString()

            viewModel.apply {
                this.postCheckIn(checkInModel = checkInModel).observe(viewLifecycleOwner){ response ->
                    if (response.code == "200"){
                        binding.progressBar2.visibility = View.INVISIBLE
                        dialogConstruction(checkInModel)
                    } else{
                        Toast.makeText(requireContext(), "Ops, houve um erro\ntente novamente.", Toast.LENGTH_LONG).show()
                        binding.progressBar2.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun dialogConstruction(checkInModel: CheckInModel){
        var dialogBuilder = MaterialAlertDialogBuilder(requireContext())
        var inflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.success, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder
            .setTitle("Sucesso em fazer check-in")
            .setNeutralButton("Fechar") { dialog, which ->
                findNavController().navigate(R.id.action_checkInFragment_to_homeFragment)
            }
            .setPositiveButton("VER COMPROVANTE") { dialog, which ->
                val action = CheckInFragmentDirections.actionCheckInFragmentToSuccessCheckInFragment(checkInModel)
                findNavController().navigate(action)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}