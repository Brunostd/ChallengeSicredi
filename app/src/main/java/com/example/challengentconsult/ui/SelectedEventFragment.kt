package com.example.challengentconsult.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.challengentconsult.R
import com.example.challengentconsult.databinding.FragmentSelectedEventBinding
import com.squareup.picasso.Picasso

class SelectedEventFragment : Fragment() {
    private var _binding: FragmentSelectedEventBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SelectedEventFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedEventBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListener()

        return view
    }

    private fun setView() {
        binding.textTitleSelectedEvent.text = args.selectEvent.title
        //Glide.with(requireContext()).load(args.selectEvent.image).load(binding.imageSelectedEvent)
        Picasso.get().load(args.selectEvent.image).into(binding.imageSelectedEvent)
        if (binding.imageSelectedEvent.drawable == null){
            binding.imageSelectedEvent.setImageResource(R.drawable.erro)
        }
        binding.textDescriptionSelectedEvent.text = args.selectEvent.description
        binding.textPriceSelectedEvent.text = args.selectEvent.price.toString()
    }

    private fun setListener() {
        binding.backSelected.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textRealizarCheckIn.setOnClickListener {
            val action = SelectedEventFragmentDirections.actionSelectedEventFragmentToCheckInFragment(args.selectEvent)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}