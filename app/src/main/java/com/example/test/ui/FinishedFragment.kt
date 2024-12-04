package com.example.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.data.response.EventAdapter
import com.example.test.data.response.EventViewModel
import com.example.test.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {
    private val viewModel: EventViewModel by viewModels()
    private lateinit var adapter: EventAdapter
    private var _binding: FragmentFinishedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getEvents(active = 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
