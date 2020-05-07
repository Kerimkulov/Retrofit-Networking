package com.example.profitnetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_image.*

class DisplayImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)

        val uri = intent.getStringExtra("uri")
        loadImage(uri)
    }
    private fun loadImage(uri: String){
        Picasso
            .get()
            .load(uri)
            .into(image_view)
    }
}
