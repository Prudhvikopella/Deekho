package com.deekho.app.ui.adapter

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.deekho.app.R
import com.deekho.app.databinding.ItemAnimeListBinding
import com.deekho.app.ui.interfaces.AnimeItemClickListener
import com.deekho.app.ui.model.Anime
import com.deekho.app.utils.ViewExtensions.loadImage

class AnimeListAdapter(
    private val callback : AnimeItemClickListener
) :
    PagingDataAdapter<Anime, AnimeListAdapter.AnimeListViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListViewHolder {
        val binding = ItemAnimeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AnimeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeListViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category ,callback)
    }

    class AnimeListViewHolder(private val binding: ItemAnimeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(category: Anime?, callback: AnimeItemClickListener) {
                Log.e("CHECKDATA" , category!!.title)
                binding.ivApproved.visibility = View.GONE
                binding.tvTitle.text = category.title
                binding.ivPoster.loadImage(category.images.jpg.large_image_url , R.drawable.ic_launcher_background)
                if(category.approved)
                    binding.ivApproved.visibility = View.VISIBLE
                if(category.airing){
                    binding.vStatus.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(binding.root.context, R.color.green)
                    )
                }else if(category.status.equals("Finished Airing") && category.airing){
                    binding.vStatus.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(binding.root.context, R.color.yellow)
                    )
                }else{
                    binding.vStatus.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(binding.root.context, R.color.red)
                    )
                }
                if(category.trailer?.youtube_id == null)
                    binding.ivVideo.setImageResource(R.drawable.ic_no_video)

                binding.clParent.setOnClickListener{
                    callback.onItemClickec(category.mal_id)
                }
            }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem == newItem
        }
    }


}