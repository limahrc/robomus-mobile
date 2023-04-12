package com.nescom.robomus.presentation.receiver

import android.media.midi.MidiDeviceInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nescom.robomus.R
import com.nescom.robomus.common.LabelValueColoredText

@Composable
fun ReceiverScreen(
    devices: Array<MidiDeviceInfo>,
    onConnectClicked: (MidiDeviceInfo) -> Unit,
    onBackPressed: () -> Unit
) {
    val virtualDevices =
        remember { devices.toList().filter { it.type == MidiDeviceInfo.TYPE_VIRTUAL } }

    val connectedDevices =
        remember { devices.toList().filter { it.type != MidiDeviceInfo.TYPE_VIRTUAL } }

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
            if (virtualDevices.isEmpty()) {
                Text(
                    text = "No devices found",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                DeviceList(
                    virtualDevices = virtualDevices,
                    realDevices = connectedDevices,
                    onConnectClicked = onConnectClicked
                )
            }

        }
    }
}

@Composable
fun DeviceList(
    virtualDevices: List<MidiDeviceInfo>,
    realDevices: List<MidiDeviceInfo>,
    onConnectClicked: (MidiDeviceInfo) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Virtual devices",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )

                virtualDevices.forEach { device ->
                    DeviceListItem(
                        painter = painterResource(id = R.drawable.ic_mixer),
                        deviceName = device.properties.getString("name") ?: "Unknown",
                        manufacturer = device.properties.getString("manufacturer") ?: "Unknown",
                        isPrivate = device.isPrivate
                    ) { onConnectClicked(device) }
                }
                Divider()
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Real devices",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                if (realDevices.isEmpty()) {
                    Text(
                        text = "No devices found",
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    realDevices.forEach { device ->
                        DeviceListItem(
                            painter = painterResource(id = R.drawable.ic_usb),
                            deviceName = device.properties.getString("name") ?: "Unknown",
                            manufacturer = device.properties.getString("manufacturer") ?: "Unknown",
                            isPrivate = device.isPrivate
                        ) { onConnectClicked(device) }
                    }
                }
            }
        }
    }
}

@Composable
fun DeviceListItem(
    painter: Painter,
    deviceName: String,
    manufacturer: String = "Unknown",
    isPrivate: Boolean,
    onConnectClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onConnectClicked() }
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Device icon",
                    modifier = Modifier
                        .size(18.dp)
                )
                Text(text = "Can connect?")
                Box(modifier = Modifier.clip(RoundedCornerShape(4.dp))) {
                    if (isPrivate) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Private device",
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Public device",
                            tint = Color.Green
                        )
                    }
                }
            }

            LabelValueColoredText(
                label = "Name",
                value = deviceName,
                highlightColor = Color.Red
            )

            LabelValueColoredText(
                label = "Manufacturer",
                value = manufacturer,
                highlightColor = Color.Red
            )
        }
    }
}

@Preview
@Composable
fun PreviewDeviceCard() {
    DeviceListItem(
        deviceName = "Porta USB",
        painter = painterResource(id = R.drawable.ic_usb),
        isPrivate = false,
        onConnectClicked = {}
    )
}
