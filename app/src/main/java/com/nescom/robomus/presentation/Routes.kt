package com.nescom.robomus.presentation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object FileSelect : Routes("file_select")
    object MidiReceiver : Routes("midi_receiver")
    object MidiSender : Routes("midi_sender")
}
