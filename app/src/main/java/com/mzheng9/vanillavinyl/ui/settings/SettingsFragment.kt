package com.mzheng9.vanillavinyl.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.databinding.FragmentSettingsBinding
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel

class SettingsFragment : Fragment() {
    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: FragmentSettingsBinding? = null
//    private var newFriend = Friend()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentSettingsBinding
        binding?.apply {

//            seasonSpinner.onItemSelectedListener = this@SettingsFragment

            context?.apply {
//                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, YEARS)
//                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                birthSpinner.adapter = aa
//                birthSpinner.onItemSelectedListener = this@SettingsFragment
            }
            settingsDoneButton.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
//                sharedViewModel.addFriend(newFriend)
            }
        }
        return fragmentSettingsBinding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        binding?.apply {
//
//            when (parent) {
//                birthSpinner -> newFriend.comment = position.toString()
//                seasonSpinner -> newFriend.nickName = position.toString()
//            }
//        }
//    }

//    override fun onNothingSelected(parent: AdapterView<*>?) {}
//    companion object {
//        const val YEAR_ZERO = 1984
//        val YEARS = (YEAR_ZERO..2021).toList().toTypedArray()
//    }
}