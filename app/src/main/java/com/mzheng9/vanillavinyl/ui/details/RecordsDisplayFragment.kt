package com.mzheng9.vanillavinyl.ui.details

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzheng9.friends2.R
import com.mzheng9.vanillavinyl.databinding.RecordsdisplayFragmentBinding


class RecordsDisplayFragment : Fragment() {
    private val sharedViewModel: RecordsDisplayViewModel by activityViewModels()
    private var binding: RecordsdisplayFragmentBinding? = null
//    private val friendAdapter = Rec()

    private var deletePosition: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindingMain = RecordsdisplayFragmentBinding.inflate(inflater, container, false)
        binding = bindingMain

        binding?.apply {
            addFriendBtn.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_dataEntryFragment)
            }
            friendsRecyclerview.run {
                layoutManager = LinearLayoutManager(context)
//                adapter = friendAdapter
            }
        }
        return bindingMain.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        sharedViewModel.friends.observe(viewLifecycleOwner, {
//            friendAdapter.updateFriends(it)
//        })
        binding?.apply {
            removeFriendBtn.setOnClickListener {
                if (highlightedIndex != -1) {
                    val thisFriend = friendAdapter.getFriendAtPosition(highlightedIndex)
//                    context?.toast("Deleted: ${thisFriend.nickName}")
                    itemDeletedAlert(thisFriend)
//                    sharedViewModel.deleteFriend(friend = thisFriend)
//                    deletePosition = null
                    findNavController().navigate(R.id.action_MainFragment_self)
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

    fun itemDeletedAlert(friend: Friend) {
        val msg = resources.getString(R.string.friend_deleted_alert, friend.firstName)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.alert)
            setMessage(msg)
            setIcon(R.drawable.ic_baseline_notifications_active_24)
            setPositiveButton(R.string.yes) { _, _ ->
                sharedViewModel.deleteFriend(friend)
                highlightedIndex = -1
                context?.toast("Deleted: ${friend.nickName}")
            }
            setNegativeButton(R.string.no) { _, _ ->
                friendAdapter.notifyDataSetChanged()
                highlightedIndex = -1
                context?.toast("You're still friends with ${friend.nickName}")
            }
            show()
        }
    }

}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
