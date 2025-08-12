package com.mediaviewer.ios.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mediaviewer.ios.data.Album
import com.mediaviewer.ios.data.MediaItem
import com.mediaviewer.ios.data.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = MediaRepository(application)
    
    private val _mediaItems = MutableStateFlow<List<MediaItem>>(emptyList())
    val mediaItems: StateFlow<List<MediaItem>> = _mediaItems.asStateFlow()
    
    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _selectedTab = MutableStateFlow(0) // 0 = All Photos, 1 = Albums
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()
    
    init {
        loadMedia()
    }
    
    fun loadMedia() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val media = repository.getAllMedia()
                val albums = repository.getAlbums()
                
                _mediaItems.value = media
                _albums.value = albums
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun selectTab(index: Int) {
        _selectedTab.value = index
    }
}