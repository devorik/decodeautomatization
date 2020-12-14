package com.example.myapplication.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_fab.*

private const val CAMERA_REQUEST_CODE=101
class QrCodeScanner : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var fbButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_scanner)
        setupPermissions()
    }

    override fun onResume() {
        super.onResume()
        codeScanner()
        back_button.setOnClickListener {
            startActivity(Intent(this,MainActivity().javaClass))
        }
        codeScanner.startPreview()
    }
    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission((applicationContext),android.Manifest.permission.CAMERA)
        if(permission!= PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }
    private fun openQrCodeUrl(url:String) {
        val uris = Uri.parse(url)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        startActivity(intents)
    }


    fun codeScanner() {
        codeScanner = CodeScanner(applicationContext.applicationContext,scanner_view)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread{
                    qr_code_text.text = it.text
                    openQrCodeUrl(it.text)
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread{
                    Log.e("Main","Camera initialization error: ${it.message}")
                }
            }
        }
        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            com.example.myapplication.ui.CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "BLYA OTINISH KABYLDAI SALW A TO KOLDANA ALMAISYN MYNA CAMERANY ANASSS", Toast.LENGTH_SHORT).show()
                }else{

                }
            }
        }
    }


}