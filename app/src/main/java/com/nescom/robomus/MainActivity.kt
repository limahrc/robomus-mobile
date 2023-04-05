package com.nescom.robomus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import com.nescom.robomus.domain.MidiController
import com.nescom.robomus.domain.MediaPlayerWrapper
import com.nescom.robomus.presentation.MainNavigator
import com.nescom.robomus.presentation.theme.RoboMusTheme
import java.io.File
import androidx.navigation.compose.rememberNavController as rememberNavController

class MainActivity : ComponentActivity() {

    private val midiPlayer = MediaPlayerWrapper(this)
    private val midiController = MidiController(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        midiController.startMidiService()

        setContent {
            RoboMusTheme {
                MainNavigator(
                    navController = rememberNavController(),
                    midiController = midiController,
                    fileResult = fileResult
                )
            }
        }
    }

    private val fileResult = registerForActivityResult(GetContent()) { uri ->
        uri?.let {
            try {
                if (it.scheme == "content") {
                    val inputStream = contentResolver.openInputStream(it)
                    val file = File.createTempFile("temp", "mid")
                    file.outputStream().use { output ->
                        inputStream?.copyTo(output)
                    }
                    midiPlayer.playFromFile(file.path)
                    return@registerForActivityResult
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Could not play your file.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

}



