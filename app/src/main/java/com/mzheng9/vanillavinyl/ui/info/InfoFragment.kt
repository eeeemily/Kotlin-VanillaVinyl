package com.mzheng9.vanillavinyl.ui.info

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mzheng9.vanillavinyl.BuildConfig
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {


    private var binding: FragmentInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val infoFragmentBinding = FragmentInfoBinding.inflate(inflater, container, false)
        binding = infoFragmentBinding
        return infoFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            titleTextView.text = resources.getString(R.string.app_name)
            versionTextView.text = BuildConfig.VERSION_NAME
            copyrightTextView.text = resources.getString(R.string.info_copy_right)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}