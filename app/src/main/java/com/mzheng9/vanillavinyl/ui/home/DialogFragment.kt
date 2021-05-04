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
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.IllegalStateException
import com.mzheng9.vanillavinyl.databinding.FragmentDialogBinding


class DialogFragment : DialogFragment() {
    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding:  FragmentDialogBinding? = null
//    private var newItem = Item()

//    private lateinit var photoFile: File
//    private lateinit var photoUri: Uri
//    private val picasso = Picasso.get()

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let{
//            val builder = AlertDialog.Builder(it)
//            builder.setMessage(R.string.app_name)
//                .setPositiveButton(R.string.ok, DialogInterface.OnClickListener{ dialog, id ->
//                })
//                .setNegativeButton(R.string.cancel_remove, DialogInterface.OnClickListener{ dialog, id ->
//                })
//            builder.create()
//
//        }?: throw IllegalStateException("Activity cannot be null")
//    }
//private var newAlbum = Album()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentSettingsBinding = FragmentDialogBinding.inflate(inflater, container, false)
        binding = fragmentSettingsBinding
        binding?.apply {

//            seasonSpinner.onItemSelectedListener = this@SettingsFragment
//
//            context?.apply {
//                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, YEARS)
//                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                birthSpinner.adapter = aa
//                birthSpinner.onItemSelectedListener = this@SettingsFragment
//            }
//            settingsDoneButton.setOnClickListener {
//                findNavController().navigate(R.id.action_settingsFragment_to_MainFragment)
//                sharedViewModel.addAlbum(newAlbum)
//            }
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
//                birthSpinner -> newAlbum.comment = position.toString()
//                seasonSpinner -> newAlbum.nickName = position.toString()
//            }
//        }
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>?) {}
//    companion object {
//        const val YEAR_ZERO = 1984
//        val YEARS = (YEAR_ZERO..2021).toList().toTypedArray()
//    }
}