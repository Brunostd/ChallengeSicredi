package com.example.challengentconsult.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.challengentconsult.R
import com.example.challengentconsult.adapter.EventsAdapter
import com.example.challengentconsult.databinding.FragmentHomeBinding
import com.example.challengentconsult.model.EventModel
import com.example.challengentconsult.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setRecycler()

        return view
    }

    private fun setRecycler() {
        viewModel.apply {
            this.getEvents().observe(viewLifecycleOwner){ list ->
                this.getList(list).observe(viewLifecycleOwner){
                    binding.recyclerView.adapter = EventsAdapter(it)
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}