package com.nescom.robomus.domain

import android.content.Context
import android.media.midi.MidiDeviceInfo
import android.media.midi.MidiManager
import android.media.midi.MidiReceiver
import android.media.midi.MidiSender
import android.os.Build
import android.util.Log

class MidiController(
    private val context: Context
) {

    private lateinit var midiManager: MidiManager

    private val receiver = Synth()
    private val sender = Sender()

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

    fun connectToSender(deviceInfo: MidiDeviceInfo) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                midiManager.openDevice(deviceInfo, { device ->
                    device.openOutputPort(0)?.connect(receiver)
                }, null)
            } catch (e: Exception) {
                Log.e("MIDI", "Error connecting to sender", e)
            }
        }
    }
}