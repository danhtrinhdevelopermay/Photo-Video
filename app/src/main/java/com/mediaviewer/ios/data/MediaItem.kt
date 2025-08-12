package com.mediaviewer.ios.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaItem(
    val id: Long,
    val path: String,
    val name: String,
    val type: MediaType,
    val size: Long,
    val dateAdded: Long,
    val duration: Long = 0, // For videos
    val width: Int = 0,
    val height: Int = 0
) : Parcelable

enum class MediaType {
    IMAGE, VIDEO
}

data class Album(
    val name: String,
    val mediaItems: List<MediaItem>,
    val coverItem: MediaItem?
)