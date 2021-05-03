package com.mzheng9.vanillavinyl.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.databinding.FragmentHomeBinding
import com.mzheng9.vanillavinyl.databinding.FragmentSettingsBinding
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel


class HomeFragment : Fragment() {
    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null
//    private var newFriend = Friend()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentHomeBinding
        binding?.apply {

//            seasonSpinner.onItemSelectedListener = this@SettingsFragment
        startBtn.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_recordsDisplayFragment)
        }
//            context?.apply {
//                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, YEARS)
//                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                birthSpinner.adapter = aa
//                birthSpinner.onItemSelectedListener = this@SettingsFragment
//            }
//            settingsDoneButton.setOnClickListener {
//                findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
////                sharedViewModel.addFriend(newFriend)
//            }
        }
        return fragmentHomeBinding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        menu.clear()
//    }
}