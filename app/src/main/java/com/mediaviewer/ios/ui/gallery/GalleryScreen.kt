package com.mediaviewer.ios.ui.gallery

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mediaviewer.ios.data.MediaItem
import com.mediaviewer.ios.data.MediaType
import com.mediaviewer.ios.ui.components.BlurredGlassCard
import com.mediaviewer.ios.ui.components.iOSTabRow
import com.mediaviewer.ios.ui.theme.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel,
    modifier: Modifier = Modifier,
    onMediaClick: (MediaItem) -> Unit
) {
    val mediaItems by viewModel.mediaItems.collectAsState()
    val albums by viewModel.albums.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SystemBackground,
                        SystemBackgroundSecondary
                    )
                )
            )
    ) {
        // iOS-style header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Photos",
                style = MaterialTheme.typography.displayMedium,
                color = SystemLabel,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        
        // Custom iOS-style tab row
        iOSTabRow(
            selectedTabIndex = selectedTab,
            tabs = listOf("Library", "Albums"),
            onTabSelected = viewModel::selectTab,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = SystemBlue,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            selectedTab == 0 -> {
                // All Photos Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(mediaItems) { mediaItem ->
                        MediaThumbnail(
                            mediaItem = mediaItem,
                            onClick = { onMediaClick(mediaItem) }
                        )
                    }
                }
            }
            selectedTab == 1 -> {
                // Albums View
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(albums) { album ->
                        AlbumCard(
                            album = album,
                            onClick = { /* Navigate to album */ }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MediaThumbnail(
    mediaItem: MediaItem,
    onClick: () -> Unit
) {
    val aspectRatio = 1f
    
    Box(
        modifier = Modifier
            .aspectRatio(aspectRatio)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        GlideImage(
            model = mediaItem.path,
            contentDescription = mediaItem.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        
        // Video overlay
        if (mediaItem.type == MediaType.VIDEO) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .background(
                        Color.Black.copy(alpha = 0.7f),
                        RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text(
                    text = formatDuration(mediaItem.duration),
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }
        
        // Glass effect overlay on long press
        var isPressed by remember { mutableStateOf(false) }
        val overlayAlpha by animateFloatAsState(
            targetValue = if (isPressed) 0.3f else 0f,
            label = "overlay_alpha"
        )
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(overlayAlpha)
                .background(GlassAccent)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun AlbumCard(
    album: com.mediaviewer.ios.data.Album,
    onClick: () -> Unit
) {
    BlurredGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Album cover
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                album.coverItem?.let { coverItem ->
                    GlideImage(
                        model = coverItem.path,
                        contentDescription = album.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f)
                                )
                            )
                        )
                )
            }
            
            // Album info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = album.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SystemLabel,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Text(
                    text = "${album.mediaItems.size} items",
                    style = MaterialTheme.typography.labelLarge,
                    color = SystemLabelSecondary
                )
            }
        }
    }
}

private fun formatDuration(duration: Long): String {
    val seconds = duration / 1000
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}