package com.nescom.robomus.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onFileSelectClicked: () -> Unit,
    onMidiReceiverClicked: () -> Unit,
    onMidiSenderClicked: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onFileSelectClicked,
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Select a MIDI file to play"
                )
            }

            Button(onClick = onMidiReceiverClicked) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Use as MIDI receiver"
                )
            }

            Button(onClick = onMidiSenderClicked) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Play as a MIDI sender"
                )
            }
        }
    }
}