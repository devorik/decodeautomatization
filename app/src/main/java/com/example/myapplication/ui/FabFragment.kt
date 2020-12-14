package com.example.myapplication.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.*
import com.example.myapplication.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_fab.*

private const val CAMERA_REQUEST_CODE=101

class FabFragment : Fragment() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var fbButton: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupPermissions()
        bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar)
        bottomAppBar.performHide()
        fbButton = requireActivity().findViewById(R.id.fab)
        fbButton.hide()
        return inflater.inflate(R.layout.fragment_fab, container, false)
    }
    override fun onResume() {
        super.onResume()

        codeScanner()
        back_button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame1, TopEmployeeFragment())
                .commit()
        }
        codeScanner.startPreview()
    }
    override fun onPause() {

        super.onPause()
        codeScanner.releaseResources()
    }



    fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission((requireContext()),android.Manifest.permission.CAMERA)
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
        requireActivity().startActivity(intents)
    }

    fun codeScanner() {
        codeScanner = CodeScanner(requireContext().applicationContext,scanner_view)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                requireActivity().runOnUiThread{
                    qr_code_text.text = it.text
                    openQrCodeUrl(it.text)
                }
            }
            errorCallback = ErrorCallback {
                requireActivity().runOnUiThread{
                    Log.e("Main","Camera initialization error: ${it.message}")
                }
            }
        }
        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
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
                    Toast.makeText(requireContext(), "BLYA OTINISH KABYLDAI SALW A TO KOLDANA ALMAISYN MYNA CAMERANY ANASSS", Toast.LENGTH_SHORT).show()
                }else{

                }
            }
        }
    }
}