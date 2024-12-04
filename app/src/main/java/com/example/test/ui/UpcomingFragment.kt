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
import com.example.test.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment() {
    private val viewModel: EventViewModel by viewModels()
    private lateinit var adapter: EventAdapter
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        adapter = EventAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observing events data from ViewModel
        viewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        // Observing loading state
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observing error state
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch upcoming events
        viewModel.getEvents(active = 1) // Active = 1 for upcoming
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
