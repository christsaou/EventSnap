

package com.example.page1


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class ImageViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        val imageView: ImageView = findViewById(R.id.imageViewFullScreen)


        val imageResource = intent.getIntExtra("imageResource", 0)
        Log.d("ImageViewerActivity", "Received resource ID: $imageResource")


        imageView.setImageResource(imageResource)


        imageView.setOnClickListener {
            finish()
        }
    }
}
