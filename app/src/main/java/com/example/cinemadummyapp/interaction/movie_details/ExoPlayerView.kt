package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun ExoPlayerViewPreview() {
    CinemaDummyAppTheme {
        ExoPlayerView(
            modifier = Modifier
                .fillMaxSize(),
            videoUrl = "https://www.imdb.com/video/vi2673654553/?playlistId=tt0099685&ref_=tt_pr_ov_vi"
        )
    }
}

@Composable
fun ExoPlayerView(
    modifier: Modifier = Modifier,
    videoUrl: String
) {
    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember(videoUrl) { MediaItem.fromUri(videoUrl) }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) { onDispose { exoPlayer.release() } }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    AndroidView(
        modifier = modifier,
        factory = { ctx -> PlayerView(ctx).apply { player = exoPlayer } },
    )
}