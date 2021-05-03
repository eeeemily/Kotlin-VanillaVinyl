package com.mzheng9.vanillavinyl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.mzheng9.friends2.R
//import com.mzheng9.friends2.database.Friend
//import com.mzheng9.friends2.databinding.RecyclerviewItemBinding
//var highlightedIndex: Int = -1
//
//class FriendAdapter : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
//var friends: List<Friend> = emptyList()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FriendViewHolder(
//        RecyclerviewItemBinding.inflate(
//            LayoutInflater.from(parent.context), parent, false
//        )
//    )
//
//    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) =
//        holder.bind(friends[position])
//
//
//    override fun getItemCount() = friends.size
//
//    fun updateFriends(newFriends: List<Friend>) {
//        this.friends = newFriends
//        notifyDataSetChanged()
//    }
//
//    fun getFriendAtPosition(position: Int): Friend {
//        return friends[position]
//    }
//
//    class FriendViewHolder(private val binding: RecyclerviewItemBinding) :
//        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
//                private lateinit var friend: Friend
//        private val friendTextView: TextView = itemView.findViewById(R.id.item_textView)
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        fun bind(friend: Friend) {
//            this.friend = friend
//            friendTextView.text =
//                    "First Name: " + friend.firstName + " Last Name: " + friend.lastName + " Age: " + friend.age
//        }
//
//        override fun onClick(v: View?) {
//            /*if one friend is already highlighted*/
//            if (highlightedIndex != -1) {
//                /*if one friend is already highlighted*/
//                if (adapterPosition == highlightedIndex) {
//                    highlightedIndex = -1
//                    friendTextView.setBackgroundResource(R.drawable.friend_cell_background)
//                } else {
//                    friendTextView.context?.toast("unselect/delete the selected Friend first!")
//                }
//            }
//            /*if no friend is selected*/
//            else {
//                highlightedIndex = adapterPosition
//                friendTextView.setBackgroundResource(R.drawable.friend_cell_selected_background)
//          }
//        }
//
//    }
//}
