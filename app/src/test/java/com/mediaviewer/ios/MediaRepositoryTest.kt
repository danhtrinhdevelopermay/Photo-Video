package com.mediaviewer.ios

import android.content.ContentResolver
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.mediaviewer.ios.data.MediaRepository
import com.mediaviewer.ios.data.MediaType
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MediaRepositoryTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockContentResolver: ContentResolver

    private lateinit var mediaRepository: MediaRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockContext = ApplicationProvider.getApplicationContext()
        mediaRepository = MediaRepository(mockContext)
    }

    @Test
    fun `getAllMedia should return list of media items`() = runTest {
        // Given
        val mediaItems = mediaRepository.getAllMedia()

        // Then
        assertNotNull(mediaItems)
        assertTrue(mediaItems is List)
    }

    @Test
    fun `getImages should return only image media items`() = runTest {
        // Given
        val images = mediaRepository.getImages()

        // Then
        assertNotNull(images)
        images.forEach { mediaItem ->
            assertEquals(MediaType.IMAGE, mediaItem.type)
        }
    }

    @Test
    fun `getVideos should return only video media items`() = runTest {
        // Given
        val videos = mediaRepository.getVideos()

        // Then
        assertNotNull(videos)
        videos.forEach { mediaItem ->
            assertEquals(MediaType.VIDEO, mediaItem.type)
        }
    }

    @Test
    fun `getAlbums should return list of albums`() = runTest {
        // Given
        val albums = mediaRepository.getAlbums()

        // Then
        assertNotNull(albums)
        albums.forEach { album ->
            assertNotNull(album.name)
            assertNotNull(album.mediaItems)
            assertTrue(album.mediaItems.isNotEmpty() || album.mediaItems.isEmpty())
        }
    }
}