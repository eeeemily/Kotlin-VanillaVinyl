package com.mzheng9.vanillavinyl.ui.details

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.database.Album
import com.mzheng9.vanillavinyl.databinding.RecordsdisplayFragmentBinding


class RecordsDisplayFragment : Fragment() {

    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: RecordsdisplayFragmentBinding? = null
    private val albumAdapter = AlbumAdapter()

    private var deletePosition: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindingMain = RecordsdisplayFragmentBinding.inflate(inflater, container, false)
        binding = bindingMain

        binding?.apply {
            addAlbumBtn.setOnClickListener {
                findNavController().navigate(R.id.action_recordsDisplayFragment_to_dataEntryFragment)
            }
            albumsRecyclerview.run {
                layoutManager = LinearLayoutManager(context)
                adapter = albumAdapter
            }
        }
        return bindingMain.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.albums.observe(viewLifecycleOwner, {
            albumAdapter.updateAlbums(it)
        })
        binding?.apply {
            removeAlbumBtn.setOnClickListener {
                if (highlightedIndex != -1) {
                    val thisAlbum = albumAdapter.getAlbumAtPosition(highlightedIndex)
//                    context?.toast("Deleted: ${thisAlbum.nickName}")
                    itemDeletedAlert(thisAlbum)
//                    sharedViewModel.deleteAlbum(album = thisAlbum)
//                    deletePosition = null
                    findNavController().navigate(R.id.action_recordsDisplayFragment_self)
                }else{
                    context?.toast("You haven't select anyone!")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun itemDeletedAlert(album: Album) {
        val msg = resources.getString(R.string.album_deleted_alert, album.albumName)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.alert)
            setMessage(msg)
            setIcon(R.drawable.ic_baseline_notifications_active_24)
            setPositiveButton(R.string.yes) { _, _ ->
                sharedViewModel.deleteAlbum(album)
                highlightedIndex = -1
                context?.toast("Deleted: ${album.albumName}")
            }
            setNegativeButton(R.string.no) { _, _ ->
                albumAdapter.notifyDataSetChanged()
                highlightedIndex = -1
                context?.toast("You're still album with ${album.albumName}")
            }
            show()
        }
    }

}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
