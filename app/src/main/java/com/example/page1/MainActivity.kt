package com.example.page1


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var isMuted = false

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigateToSecondButton = findViewById<Button>(R.id.navButton)
        navigateToSecondButton.setOnClickListener {
            // Navigate to the SecondActivity when the button is clicked
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        // Initialize the MediaPlayer with your audio file
     /**   mediaPlayer = MediaPlayer.create(this, R.raw.lifelike)

        val muteButton = findViewById<Button>(R.id.muteButton)



        // Set the initial text based on the muted state
        updateMuteButtonText(muteButton)

        muteButton.setOnClickListener {
            if (isMuted) {
                // Unmute the audio
                mediaPlayer?.setVolume(1f, 1f)
                isMuted = false
            } else {
                // Mute the audio
                mediaPlayer?.setVolume(0f, 0f)
                isMuted = true
            }

            // Update the text on the mute button
            updateMuteButtonText(muteButton)
        }

    }

    private fun updateMuteButtonText(button: Button) {
        if (isMuted) {
            button.text = "Unmute"
        } else {
            button.text = "Mute"
        }
    }

    override fun onStart() {
        super.onStart()

        // Start audio playback
        mediaPlayer?.start()
    }

    override fun onStop() {
        super.onStop()

        // Comment out the code that pauses the audio when the activity stops
        // mediaPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release the MediaPlayer
        mediaPlayer?.release()
        mediaPlayer = null**/
    }
}




