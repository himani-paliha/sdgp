package com.example.sdgp_welcomescreen

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class MainActivity : Activity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_FROM_GALLERY = 2

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        val takePictureButton = findViewById<Button>(R.id.capture_button)
        val choosePictureButton = findViewById<Button>(R.id.upload_Button)

        takePictureButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        choosePictureButton.setOnClickListener {
            dispatchChoosePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun dispatchChoosePictureIntent() {
        val choosePictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (choosePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(choosePictureIntent, REQUEST_IMAGE_FROM_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imageView.setImageBitmap(imageBitmap)
                }
                REQUEST_IMAGE_FROM_GALLERY -> {
                    val selectedImageUri: Uri? = data?.data
                    val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                    imageView.setImageBitmap(imageBitmap)
                }
            }
        }
    }
}
