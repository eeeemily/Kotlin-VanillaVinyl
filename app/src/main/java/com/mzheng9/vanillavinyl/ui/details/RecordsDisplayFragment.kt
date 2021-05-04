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
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = albumAdapter

            }
        }
        return bindingMain.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImage()
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
                } else {
                    context?.toast("You haven't select anyone!")
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
//    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        setHasOptionsMenu(true)
//        setPreferencesFromResource(R.xml.preferences_fragment, rootKey)
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            EFFECT_SELECTION -> {
                setImage()
            }
        }
    }

    private fun setImage() {
        context?.toast("set image is called!!!")

        val effect = prefs.getString(EFFECT_SELECTION, "0")?.toInt()
        if(effect!=null){
            context?.toast("Effect is not null!!! $effect")
            albumAdapter.updateAlbumImageView(effect)
        }

//        val resID = if (prefs.getBoolean(
//                SHOW_NOW_IMAGE,
//                false
//            )
//        ) R.drawable.ur_rushrhees_new else R.drawable.ur_rushrhees_old
//
//        val picasso = Picasso.get()
//        picasso.load(resID)
//            .transform(effect)
//            .into(urImageView)
    }

}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
