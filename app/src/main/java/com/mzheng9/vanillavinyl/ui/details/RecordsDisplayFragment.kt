package com.mzheng9.vanillavinyl.ui.details

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzheng9.vanillavinyl.MainActivity.Companion.EFFECT_SELECTION
import com.mzheng9.vanillavinyl.MainActivity.Companion.SHOW_NOW_IMAGE
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.database.Album
import com.mzheng9.vanillavinyl.databinding.RecordsdisplayFragmentBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.CropSquareTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation


class RecordsDisplayFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: RecordsdisplayFragmentBinding? = null
    private val albumAdapter = AlbumAdapter()
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }
//    private var deletePosition: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = albumAdapter

            }
        }
        return bindingMain.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImage()
        setBG()
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
                    itemDeletedAlert(thisAlbum)
//                    sharedViewModel.deleteAlbum(album = thisAlbum)
//                    deletePosition = null
                    findNavController().navigate(R.id.action_recordsDisplayFragment_self)
                } else {
                    context?.toast(getString(R.string.not_select_any_album_to_delete))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        prefs.unregisterOnSharedPreferenceChangeListener(this)

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
                context?.toast(getString(R.string.deleted_colon, album.albumName))
//                context?.toast("Deleted: ${album.albumName}")
            }
            setNegativeButton(R.string.no) { _, _ ->
                albumAdapter.notifyDataSetChanged()
                highlightedIndex = -1
//                context?.toast(getString(R.string.deleted_colon, album.albumName))
                context?.toast(getString(R.string.keep_colon, album.albumName))
            }
            show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            EFFECT_SELECTION -> {
                setImage()
            }
            SHOW_NOW_IMAGE -> {
                setBG()
            }

        }
    }
    private fun setBG() {
        val resID = if (prefs.getBoolean(
                SHOW_NOW_IMAGE,
                false
            )
        ) 1 else 0
            albumAdapter.updateBG(resID)

    }
    private fun setImage() {
        val effect = prefs.getString(EFFECT_SELECTION, "0")?.toInt()
        if(effect!=null){
            albumAdapter.updateAlbumImageView(effect)
        }
    }

}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
