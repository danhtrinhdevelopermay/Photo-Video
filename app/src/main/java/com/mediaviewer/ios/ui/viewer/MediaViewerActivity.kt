package com.mediaviewer.ios.ui.viewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mediaviewer.ios.data.MediaItem
import com.mediaviewer.ios.ui.theme.iOSStyleMediaViewerTheme

class MediaViewerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val mediaItem = intent.getParcelableExtra<MediaItem>("media_item")
            ?: run {
                finish()
                return
            }
        
        setContent {
            iOSStyleMediaViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MediaViewerScreen(
                        mediaItem = mediaItem,
                        onBackPressed = { finish() }
                    )
                }
            }
        }
    }
}