package com.darth.alert_dialogs.dialogs

import com.darth.alert_dialogs.util.JSONUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.darth.alert_dialogs.adapter.CarListAdapter
import com.darth.alert_dialogs.databinding.FragmentSpinnerBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpinnerFragment : DialogFragment() {

    private var _binding : FragmentSpinnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vehicleCancelButton.setOnClickListener {
            dismiss()
        }

        val jsonUtil = JSONUtil()
        val jsonFileString = jsonUtil.getJsonData(requireContext(), "carlist.json")
        if (jsonFileString != null) {
            Log.i("data", jsonFileString)
        } else {
            Log.e("data", "jsonFileString is null")
        }
        val gson = Gson()


        val listCarManufacturer = object : TypeToken<List<CarListAdapter>>() {}.type
        val vehicles: List<CarListAdapter>? = gson.fromJson(jsonFileString, listCarManufacturer)
        if (vehicles != null) {
            Log.i("data", "Deserialized vehicles: $vehicles")
            val vehicleManufacturerList : MutableList<String> = ArrayList()
            vehicleManufacturerList.add("Choose your vehicle:")
            for (vehicle in vehicles) {
                vehicleManufacturerList.add(vehicle.brand)
            }

            val vehicleManufacturerAdapter : ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, vehicleManufacturerList)

            val vehicleManufacturerSpinner = binding.vehicleManufacturer
            vehicleManufacturerSpinner.adapter = vehicleManufacturerAdapter
            vehicleManufacturerSpinner.setSelection(0)

            vehicleManufacturerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // give an error later!
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedManufacturer : String = vehicleManufacturerList[position]
                    val defaultItem: String = vehicleManufacturerList[0]

                    if (selectedManufacturer != defaultItem) {
                        Toast.makeText(requireContext(), "$selectedManufacturer selected!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Log.e("data", "Failed to deserialize vehicles")
        }

        val carListAdapterType = object : TypeToken<List<CarListAdapter>>() {}.type
        val carListAdapterList: List<CarListAdapter>? = gson.fromJson(jsonFileString, carListAdapterType)

        if (carListAdapterList != null) {
            // Log.i("data", "Deserialized carListAdapterList: $carListAdapterList")
            val carManufacturerList: MutableList<String> = mutableListOf()
            val defaultCarManufacturer = "Choose your vehicle model"
            carManufacturerList.add(defaultCarManufacturer)

            for (carListAdapter in carListAdapterList) {
                carManufacturerList.add(carListAdapter.brand)
            }

            val carManufacturerAdapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_dropdown_item, carManufacturerList)

            val carManufacturerSpinner = binding.vehicleManufacturer
            carManufacturerSpinner.adapter = carManufacturerAdapter
            carManufacturerSpinner.setSelection(0)

            carManufacturerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Give an error message if needed
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position != 0) {
                        val selectedCarListAdapter = carListAdapterList[position - 1]
                        val carModelsList = selectedCarListAdapter.models
                        val carModelsAdapter: ArrayAdapter<String> = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            carModelsList
                        )
                        val carModelsSpinner = binding.vehicleModel
                        carModelsSpinner.adapter = carModelsAdapter
                        carModelsSpinner.setSelection(0)

                        carModelsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                // Give an error message if needed...........
                            }

                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                if (position != 0) {
                                    val selectedCarModel = carModelsList[position]
                                    Toast.makeText(
                                        requireContext(),
                                        "$selectedCarModel selected!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Log.e("data", "Failed to deserialize carListAdapterList")
        }

        val vehicleYearList : MutableList<String> = ArrayList()
        vehicleYearList.add("Choose your vehicle year:")
        for (year in 2025 downTo 1950) {
            vehicleYearList.add(year.toString())
        }

        val vehicleYearAdapter : ArrayAdapter<String> = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, vehicleYearList)

        val vehicleYearSpinner = binding.vehicleYear // year
        vehicleYearSpinner.adapter = vehicleYearAdapter
        vehicleYearSpinner.setSelection(0)

        vehicleYearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // give an error later!
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val selectedYear : String = vehicleYearList[position]
                val defaultItem: String = vehicleYearList[0]

                if (selectedYear != defaultItem) {
                    Toast.makeText(requireContext(), "$selectedYear selected!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}