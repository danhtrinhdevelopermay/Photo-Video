package com.mediaviewer.ios.data

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepository(private val context: Context) {
    
    suspend fun getAllMedia(): List<MediaItem> = withContext(Dispatchers.IO) {
        val mediaItems = mutableListOf<MediaItem>()
        mediaItems.addAll(getImages())
        mediaItems.addAll(getVideos())
        mediaItems.sortedByDescending { it.dateAdded }
    }
    
    suspend fun getImages(): List<MediaItem> = withContext(Dispatchers.IO) {
        val images = mutableListOf<MediaItem>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )
        
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )
        
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val pathColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val widthColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val heightColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
            
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val path = it.getString(pathColumn)
                val name = it.getString(nameColumn)
                val size = it.getLong(sizeColumn)
                val dateAdded = it.getLong(dateColumn)
                val width = it.getInt(widthColumn)
                val height = it.getInt(heightColumn)
                
                images.add(
                    MediaItem(
                        id = id,
                        path = path,
                        name = name,
                        type = MediaType.IMAGE,
                        size = size,
                        dateAdded = dateAdded,
                        width = width,
                        height = height
                    )
                )
            }
        }
        
        images
    }
    
    suspend fun getVideos(): List<MediaItem> = withContext(Dispatchers.IO) {
        val videos = mutableListOf<MediaItem>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT
        )
        
        val cursor = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Video.Media.DATE_ADDED} DESC"
        )
        
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val pathColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val dateColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val widthColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
            val heightColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)
            
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val path = it.getString(pathColumn)
                val name = it.getString(nameColumn)
                val size = it.getLong(sizeColumn)
                val dateAdded = it.getLong(dateColumn)
                val duration = it.getLong(durationColumn)
                val width = it.getInt(widthColumn)
                val height = it.getInt(heightColumn)
                
                videos.add(
                    MediaItem(
                        id = id,
                        path = path,
                        name = name,
                        type = MediaType.VIDEO,
                        size = size,
                        dateAdded = dateAdded,
                        duration = duration,
                        width = width,
                        height = height
                    )
                )
            }
        }
        
        videos
    }
    
    suspend fun getAlbums(): List<Album> = withContext(Dispatchers.IO) {
        val allMedia = getAllMedia()
        val albumMap = mutableMapOf<String, MutableList<MediaItem>>()
        
        allMedia.forEach { mediaItem ->
            val folderName = mediaItem.path.substringBeforeLast("/").substringAfterLast("/")
            albumMap.getOrPut(folderName) { mutableListOf() }.add(mediaItem)
        }
        
        albumMap.map { (name, items) ->
            Album(
                name = name,
                mediaItems = items.sortedByDescending { it.dateAdded },
                coverItem = items.firstOrNull()
            )
        }.sortedByDescending { it.mediaItems.size }
    }
}