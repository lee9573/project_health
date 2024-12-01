package com.example.healthappproject

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class YoutubeBannerAdapter(
    private val videoIds: List<String>?,
    private val videoUrls: List<String>?
) : RecyclerView.Adapter<YoutubeBannerAdapter.YoutubeViewHolder>() {

    inner class YoutubeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnailImageView: ImageView = view.findViewById(R.id.youtubeThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.youtube_thumbnail_item, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val videoId = videoIds?.get(position)
        val videoUrl = videoUrls?.get(position)
        val thumbnailUrl = "https://img.youtube.com/vi/$videoId/0.jpg"

        Glide.with(holder.thumbnailImageView.context)
            .load(thumbnailUrl)
            .into(holder.thumbnailImageView)

        holder.thumbnailImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            holder.thumbnailImageView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = videoIds?.size ?: 0
}
