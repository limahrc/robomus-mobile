package com.nescom.robomus.presentation.receiver

import android.media.midi.MidiDeviceInfo
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReceiverScreen(
    devices: Array<MidiDeviceInfo>,
    onSearchDevicesClicked: () -> Unit,
    onBackPressed: () -> Unit
) {
    val deviceList = remember { devices.toList() }
    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = "MIDI Receiver",
                modifier = Modifier.padding(16.dp)
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (deviceList.isEmpty()) {
                Text(
                    text = "No devices found",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                DeviceList(devices = deviceList)
            }

        }
    }
}

@Composable
fun DeviceList(devices: List<MidiDeviceInfo>) {
    LazyColumn(modifier = Modifier.padding(20.dp)) {
        devices.forEach { device ->
            Log.d("MIDI", device.toString())
            items(devices) {
                DeviceListItem(
                    deviceName = it.properties.getString("name") ?: "Unknown",
                    isPrivate = it.isPrivate,
                    onConnectClicked = {}
                )
            }
        }
    }
}

@Composable
fun DeviceListItem(
    deviceName: String,
    isPrivate: Boolean,
    onConnectClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onConnectClicked() }
    ) {
        Column {
            Text(
                text = "Name: $deviceName",
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
                color = Color.Black
            )

            Text(
                text = "Can connect? $isPrivate",
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewDeviceCard() {
    DeviceListItem(
        deviceName = "Device name",
        false,
        onConnectClicked = {}
    )
}
