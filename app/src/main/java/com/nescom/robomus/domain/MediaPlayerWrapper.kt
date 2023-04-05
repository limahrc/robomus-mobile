package com.nescom.robomus.domain

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri

class MediaPlayerWrapper(private val context: Context) {

    private val mediaPlayer = MediaPlayer()

    fun playFromFile(filePath: String) = with(mediaPlayer) {
        if (isPlaying) {
            stop()
            reset()
        }
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        setDataSource(context, Uri.parse(filePath))
        prepare()
        start()
    }
}