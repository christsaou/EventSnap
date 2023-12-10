package com.example.page1

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import android.view.WindowManager
import android.view.animation.AnimationUtils


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // HERE WE ARE TAKING THE REFERENCE OF OUR IMAGE
        // SO THAT WE CAN PERFORM ANIMATION USING THAT IMAGE
        val backgroundImage: ImageView = findViewById(R.id.splashLogo)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        backgroundImage.startAnimation(slideAnimation)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}


/**class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        val splashLayout = findViewById<ConstraintLayout>(R.id.splashLayout)
        val logo = findViewById<ImageView>(R.id.splashLogo)

        // Show the logo
        logo.alpha = 1f // Make it transparent initially
        logo.animate().alpha(1f).duration = 2000

        Handler().postDelayed({
            // Create fade-out animation
            val fadeOut = AlphaAnimation(1f, 0f)
            fadeOut.duration = 2000 // 1 second
            fadeOut.fillAfter = true

            // Set the animation listener
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationRepeat(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    // This method will be executed once the animation is over
                    // Start your app's main activity
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)


                    finish()
                }
            })


            splashLayout.startAnimation(fadeOut)

        }, SPLASH_TIME_OUT)
    }
}*/