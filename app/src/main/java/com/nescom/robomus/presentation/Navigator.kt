package com.nescom.robomus.presentation

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nescom.robomus.domain.MidiController
import com.nescom.robomus.presentation.home.HomeScreen
import com.nescom.robomus.presentation.receiver.ReceiverScreen

@Composable
fun MainNavigator(
    navController: NavHostController,
    midiController: MidiController,
    fileResult: ActivityResultLauncher<String>
) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
                onFileSelectClicked = { fileResult.launch("audio/midi") },
                onMidiReceiverClicked = { navController.navigate(Routes.MidiReceiver.route) },
                onMidiSenderClicked = { navController.navigate(Routes.MidiSender.route) })
        }
        composable(Routes.MidiReceiver.route) {
            ReceiverScreen(
                onSearchDevicesClicked = midiController::logDevices,
                devices = midiController.deviceInfos ?: emptyArray(),
                onBackPressed = { navController.navigateUp() }
            )
        }

        composable(Routes.MidiSender.route) {

        }
    }
}