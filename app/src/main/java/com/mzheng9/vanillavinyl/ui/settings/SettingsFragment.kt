package com.mzheng9.vanillavinyl.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.databinding.FragmentSettingsBinding
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel

class SettingsFragment : PreferenceFragmentCompat() {
    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: FragmentSettingsBinding? = null
//    private var newFriend = Friend()
override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setHasOptionsMenu(true)
    setPreferencesFromResource(R.xml.preferences_fragment, rootKey)
}
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        setHasOptionsMenu(true)
//
//        val fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
//        binding = fragmentSettingsBinding
//        binding?.apply {
//            context?.apply {}
//            settingsDoneButton.setOnClickListener {
//                findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
//            }
//        }
//        return fragmentSettingsBinding.root
//    }


//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

}