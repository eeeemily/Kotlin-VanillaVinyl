package com.mzheng9.vanillavinyl.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.IllegalStateException
import com.mzheng9.vanillavinyl.databinding.FragmentDialogBinding
import com.mzheng9.vanillavinyl.ui.details.hideKeyboard


class DialogFragment : DialogFragment() {
    private var binding:  FragmentDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentSettingsBinding = FragmentDialogBinding.inflate(inflater, container, false)
        binding = fragmentSettingsBinding
        binding?.apply {
            settingsDoneButton.setOnClickListener{
                findNavController().navigate(R.id.action_dialogFragment_to_homeFragment)
                context?.hideKeyboard(it)
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

}