package com.nescom.robomus.domain

import android.media.midi.MidiReceiver
import android.util.Log

class Synth : MidiReceiver() {
    var started: Boolean = false
        private set

    fun start() {
        started = true
    }

    fun stop() {
        started = false
    }

    override fun onSend(data: ByteArray?, offset: Int, count: Int, timestamp: Long) {
        // play sound
        Log.d("MIDI Received", "onSend: $data, $offset, $count, $timestamp")
    }

}