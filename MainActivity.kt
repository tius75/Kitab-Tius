package com.example.kitabmantrakotlin

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        setupWebView()
    }

    private fun setupWebView() {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        webSettings.loadsImagesAutomatically = true

        // Add the bridge interface to allow JavaScript to call Kotlin code
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null && url.startsWith("https://wa.me/")) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        return true
                    } catch (e: Exception) {
                        return false
                    }
                }
                return false
            }
        }

        webView.loadUrl("file:///android_asset/kitab.html")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes") { _, _ -> finish() }
                .setNegativeButton("No", null)
                .show()
        }
    }
}

// Create the bridge class for communication between JavaScript and Kotlin
class WebAppInterface(private val mContext: Context) {

    @JavascriptInterface
    fun getSavedEmail(): String {
        // You can use SharedPreferences to securely store the email
        // For demonstration purposes, a placeholder is used.
        return "user@example.com"
    }

    @JavascriptInterface
    fun getSavedPassword(): String {
        // **WARNING: Storing passwords insecurely is a bad practice.**
        // This is for demonstration only. A real app should use a secure method.
        return "password123"
    }
}
