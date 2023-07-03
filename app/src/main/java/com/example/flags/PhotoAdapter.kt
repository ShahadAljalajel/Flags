package com.example.flags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flags.databinding.ItemBinding
import com.example.flags.network.Flag

class PhotoAdapter : androidx.recyclerview.widget.ListAdapter<Flag,
        PhotoAdapter.PhotoViewHolder>(DiffCallback) {

    class PhotoViewHolder(
        private var binding:
        ItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MarsPhoto: Flag) {
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.PhotoViewHolder {
        return PhotoViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Flag>() {
        override fun areItemsTheSame(oldItem: Flag, newItem: Flag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Flag, newItem: Flag): Boolean {
            return oldItem.flag == newItem.flag
        }
    }
}