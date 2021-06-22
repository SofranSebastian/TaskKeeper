package com.example.taskkeeper.holidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskkeeper.R
import com.example.taskkeeper.databinding.FragmentHolidaysViewerBinding
import com.example.taskkeeper.utils.Constants

class HolidaysViewerFragment : Fragment() {

    private lateinit var binding: FragmentHolidaysViewerBinding
    private val holidaysViewerViewModel: HolidaysViewerViewModel by viewModels()
    private lateinit var adapter: HolidayAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_holidays_viewer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //create the adapter
        adapter = HolidayAdapter()

        //setup the recycler view
        setupRecyclerView()

        //navigation for the bottom nav
        val bnv = binding.bottomNavigationView
        val navController = this.findNavController()
        bnv.setupWithNavController(navController)

        holidaysViewerViewModel.holidaysList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                it.body()?.let { it1 -> adapter.setHolidayList(it1) }
            } else {
                Toast.makeText(this.context, it.code(), Toast.LENGTH_SHORT).show()
            }
        }

        //array of strings for the country code selection
        val countries = resources.getStringArray(R.array.countries_codes)

        //adapter for the dropdown
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, countries)

        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        //number picker
        binding.numberPicker.minValue = Constants.MINIMUM_YEAR
        binding.numberPicker.maxValue = Constants.MAXIMUM_YEAR
        binding.numberPicker.value = Constants.CURRENT_YEAR

        //get button listener
        binding.button.setOnClickListener {
            if (binding.autoCompleteTextView.text.toString() == "Country Code") {
                Toast.makeText(this.context, "Please select a country code.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                holidaysViewerViewModel.getHolidays(
                    binding.numberPicker.value,
                    binding.autoCompleteTextView.text.toString()
                )
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvHolidays.adapter = adapter
        binding.rvHolidays.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }
}