// android/app/src/main/java/dev/dioxus/main/MainActivity.kt  
package dev.dioxus.main

import android.os.Bundle
import com.google.androidgamesdk.GameActivity
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup

class MainActivity : GameActivity() {

   
    private fun findNativeSurfaceView(view: View): View? {
        if (view is SurfaceView) return view
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val found = findNativeSurfaceView(view.getChildAt(i))
                if (found != null) return found
            }
        }
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //focus hack that some devices still need. although gameactivity is far better than native.
        window.decorView.post {
            val nativeView = findNativeSurfaceView(window.decorView)
            nativeView?.apply {
                isFocusable = true
                isFocusableInTouchMode = true
                requestFocus()
            }
        }
    }
}
