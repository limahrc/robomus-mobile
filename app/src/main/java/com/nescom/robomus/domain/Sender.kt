package com.nescom.robomus.domain

import android.media.midi.MidiReceiver
import android.media.midi.MidiSender

class Sender : MidiSender() {
    override fun onConnect(receiver: MidiReceiver?) {
        receiver?.onSend(byteArrayOf(0x90.toByte(), 0x40.toByte(), 0x7F.toByte()), 0, 3, 0)
    }

    override fun onDisconnect(receiver: MidiReceiver?) {
        receiver?.onSend(byteArrayOf(0x80.toByte(), 0x40.toByte(), 0x7F.toByte()), 0, 3, 0)
    }
}