package com.mzheng9.vanillavinyl.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.databinding.FragmentHomeBinding
import com.mzheng9.vanillavinyl.databinding.FragmentDialogBinding
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel


class HomeFragment : Fragment() {
    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentHomeBinding
        binding?.apply {

            startBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_recordsDisplayFragment)
            }
            webViewBtn.setOnClickListener() {
                findNavController().navigate(R.id.action_homeFragment_to_webviewFragment)
            }
            addFab.setOnClickListener(){
                findNavController().navigate(R.id.action_homeFragment_to_dialogFragment)
            }
        }
        return fragmentHomeBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}