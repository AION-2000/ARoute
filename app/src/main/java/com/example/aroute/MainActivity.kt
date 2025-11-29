package com.example.aroute

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MotionEvent // Corrected the typo here
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.Color
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.core.exceptions.*
import android.util.Log
import java.util.concurrent.CompletableFuture

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val TAG = "MainActivity"
    }

    private lateinit var arFragment: ArFragment
    private lateinit var cubeRenderable: ModelRenderable
    private var isCubeRenderableReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check and request camera permissions
        if (!checkCameraPermission()) {
            requestCameraPermission()
        } else {
            initializeAR()
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initializeAR()
                } else {
                    Toast.makeText(
                        this,
                        "Camera permission is required for AR functionality",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
            }
        }
    }

    private fun initializeAR() {
        // Add ArFragment programmatically
        arFragment = ArFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, arFragment)
            .commit()

        // Initialize the 3D cube renderable
        initializeCubeRenderable()

        // Set up tap listener
        setupTapListener()
    }

    private fun initializeCubeRenderable() {
        MaterialFactory.makeOpaqueWithColor(this, Color(android.graphics.Color.RED))
            .thenApply { material ->
                ShapeFactory.makeCube(
                    com.google.ar.sceneform.math.Vector3(0.1f, 0.1f, 0.1f),
                    com.google.ar.sceneform.math.Vector3.zero(),
                    material
                )
            }
            .thenAccept { renderable ->
                cubeRenderable = renderable
                isCubeRenderableReady = true
                runOnUiThread {
                    Toast.makeText(this, "Tap on a detected plane to place a cube", Toast.LENGTH_SHORT).show()
                }
            }
            .exceptionally { throwable ->
                runOnUiThread {
                    Toast.makeText(this, "Error creating 3D cube: ${throwable.message}", Toast.LENGTH_LONG).show()
                }
                null
            }
    }

    private fun setupTapListener() {
        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            if (!isCubeRenderableReady) {
                Toast.makeText(this, "3D model is still loading, please wait", Toast.LENGTH_SHORT).show()
                return@setOnTapArPlaneListener
            }

            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                Toast.makeText(this, "Please tap on a horizontal surface", Toast.LENGTH_SHORT).show()
                return@setOnTapArPlaneListener
            }

            placeCube(hitResult)
        }
    }

    private fun placeCube(hitResult: HitResult) {
        val anchor: Anchor = hitResult.createAnchor()
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(arFragment.arSceneView.scene)

        val node = TransformableNode(arFragment.transformationSystem)
        node.setParent(anchorNode)
        node.renderable = cubeRenderable
        node.select()
    }

    private fun handleException(exception: Exception): Boolean {
        return when (exception) {
            is UnavailableArcoreNotInstalledException -> {
                Toast.makeText(this, "Please install ARCore", Toast.LENGTH_LONG).show()
                true
            }
            is UnavailableDeviceNotCompatibleException -> {
                Toast.makeText(this, "This device does not support AR", Toast.LENGTH_LONG).show()
                true
            }
            is CameraNotAvailableException -> {
                Toast.makeText(this, "Camera not available", Toast.LENGTH_LONG).show()
                true
            }
            is UnavailableSdkTooOldException -> {
                Toast.makeText(this, "Please update ARCore", Toast.LENGTH_LONG).show()
                true
            }
            is UnavailableApkTooOldException -> {
                Toast.makeText(this, "Please update this app", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                Log.e(TAG, "Exception: ${exception.message}")
                false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            arFragment.onResume()
        } catch (e: Exception) {
            if (!handleException(e)) {
                Toast.makeText(this, "Error starting AR: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            arFragment.onPause()
        } catch (e: Exception) {
            Log.e(TAG, "Error pausing AR: ${e.message}")
        }
    }
}