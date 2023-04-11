package com.nescom.robomus.domain

import android.media.midi.MidiDeviceService
import android.media.midi.MidiDeviceStatus
import android.media.midi.MidiReceiver

class MidiService : MidiDeviceService() {

    private val synth = Synth()

    override fun onGetInputPortReceivers() = arrayOf(synth)


    override fun onDeviceStatusChanged(status: MidiDeviceStatus?) {
        status?.run {
            if (isInputPortOpen(0) && !synth.started) {
                synth.start()
            } else if (!isInputPortOpen(0) && synth.started) {
                synth.stop()
            }
        }
    }
}