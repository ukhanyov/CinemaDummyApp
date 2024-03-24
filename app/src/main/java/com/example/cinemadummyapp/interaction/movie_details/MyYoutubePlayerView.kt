package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MyYoutubePlayerViewPreview() {
    CinemaDummyAppTheme {
        MyYoutubePlayerView(
            modifier = Modifier
                .fillMaxSize(),
            videoId = "UKasPpGF6ds"
        )
    }
}

@Composable
fun MyYoutubePlayerView(
    modifier: Modifier = Modifier,
    videoId: String
) {
    var view by remember {
        mutableStateOf<YouTubePlayerView?>(null)
    }

    DisposableEffect(Unit) { onDispose { view?.release() } }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            YouTubePlayerView(ctx).let {
                it.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
                view = it
                it
            }
        },
    )
}