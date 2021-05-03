package com.mzheng9.vanillavinyl.ui.details

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
//import com.mzheng9.friends2.R
import com.mzheng9.vanillavinyl.ui.details.RecordsDisplayViewModel
import com.mzheng9.vanillavinyl.R
//import com.mzheng9.vanillavinyl.database.Friend
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
//                val friend = Friend()
//                friend.age = editFriendAge.text.toString()?.toInt()
//                friend.firstName = editFriendFname.text.toString()
//                friend.lastName = editFriendLname.text.toString()
//                friend.nickName = editFriendNname.text.toString()
//                friend.comment = editFriendComment.text.toString()
//                sharedViewModel.insert(friend)
                findNavController().navigate(R.id.action_dataEntryFragment_to_mainFragment)
                context?.hideKeyboard(it)
            }
            buttonCancel.setOnClickListener {
                context?.toast("You didn't add a friend...")
                findNavController().navigate(R.id.action_dataEntryFragment_to_mainFragment)
                context?.hideKeyboard(it)
            }
        }
        return dataEntryBinding.root
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
