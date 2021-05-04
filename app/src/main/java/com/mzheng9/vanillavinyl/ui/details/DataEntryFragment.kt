package com.mzheng9.vanillavinyl.ui.details

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.database.Album
//import com.mzheng9.vanillavinyl.database.Album
import com.mzheng9.vanillavinyl.databinding.FragmentDataEntryBinding

class DataEntryFragment : Fragment() {

    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: FragmentDataEntryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataEntryBinding = FragmentDataEntryBinding.inflate(inflater, container, false)
        binding = dataEntryBinding
        binding?.apply {
            buttonAdd.setOnClickListener {
                val album = Album()
                album.albumName = editAlbumName.text.toString()
                album.albumArtist = editAlbumArtist.text.toString()
                album.albumRelease = editAlbumRelease.text.toString()
                album.albumCoverLink = editAlbumCoverLink.text.toString()
                album.albumComment = editAlbumComment.text.toString()
                sharedViewModel.addAlbum(album)
                findNavController().navigate(R.id.action_dataEntryFragment_to_recordsDisplayFragment)
                context?.hideKeyboard(it)
            }
            buttonCancel.setOnClickListener {
                context?.toast(getString(R.string.did_not_add))
                findNavController().navigate(R.id.action_dataEntryFragment_to_recordsDisplayFragment)
                context?.hideKeyboard(it)
            }
        }
        return dataEntryBinding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
