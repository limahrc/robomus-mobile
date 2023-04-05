package com.nescom.robomus.domain

import android.content.Context
import android.media.midi.MidiDevice
import android.media.midi.MidiDeviceInfo
import android.media.midi.MidiManager
import android.os.Build
import android.text.TextUtils.isEmpty
import android.util.Log

class MidiController(
    private val context: Context,
    private val onMidiReceived: (ByteArray, Int, Int, Long) -> Unit
) :
    MidiManager.OnDeviceOpenedListener {

    constructor(context: Context) : this(context, { _, _, _, _ -> })

    private lateinit var midiManager: MidiManager
    var deviceInfos: Array<MidiDeviceInfo>? = null
        private set

    fun startMidiService() {
        midiManager = context.getSystemService(Context.MIDI_SERVICE) as MidiManager
        deviceInfos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            midiManager.getDevicesForTransport(MidiManager.TRANSPORT_MIDI_BYTE_STREAM)
                .toTypedArray()
        } else {
            midiManager.devices
        }
    }

    fun logDevices() = with(deviceInfos?.toList() ?: emptyList()) {
        if (isEmpty()) {
            Log.d("MIDI", "No devices found")
        } else {
            forEach { device ->
                Log.d("MIDI", device.toString())
            }
        }
    }

    override fun onDeviceOpened(p0: MidiDevice?) {
        p0?.let {
        }
    }
}
