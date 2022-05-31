package com.example.beatbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ListItemSoundBinding

class SoundAdapter(private val sounds: List<Sound>, val beatBox: BeatBox) :
    RecyclerView.Adapter<SoundAdapter.SoundHolder>() {
    class SoundHolder(private val binding: ListItemSoundBinding, val beatBox: BeatBox) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
        val binding: ListItemSoundBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_sound,
            parent,
            false
        )
        return SoundHolder(binding, beatBox)
    }

    override fun onBindViewHolder(holder: SoundHolder, position: Int) {
        val sound = sounds[position]
        holder.bind(sound)
    }

    override fun getItemCount(): Int = sounds.size
}
