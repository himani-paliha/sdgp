package com.example.sdgp_welcomescreen
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class WelcomeScreen : AppCompatActivity() {
    private val PermissionCode = 1000
    private val ImageCaptureCode = 1001

    var vFilename: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val btn_takephoto = null
        btn_takephoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                openCamera()
            } else {
                longToast("Sorry you're version android is not support, Min Android 5.0 (Lollipop)")
            }
        }
    }

    private fun longToast(s: String) {

    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // set filename
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        vFilename = "FOTO_" + timeStamp + ".jpg"


        // set direcory folder
        val file = File("/sdcard/niabsen/", vFilename);
        val image_uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, ImageCaptureCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //called when user presses ALLOW or DENY from Permission Request Popup
        val PERMISSION_CODE = null
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera()
                } else{
                    //permission from popup was denied
                    longToast("Permission denied")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            //File object of camera image
            val file = File("/sdcard/niabsen/", vFilename);
            longToast(file.toString())

            //Uri of camera image
            val uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
            val myImageView
            myImageView.setImageURI(uri)
        }
    }
}

private fun Nothing?.setOnClickListener(function: () -> Unit) {

}
