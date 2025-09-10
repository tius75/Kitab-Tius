package com.example.kitabmantra

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebAppInterface(private val mContext: Context) {

    /**
     * Shows a toast message from the web page.
     */
    @JavascriptInterface
    fun showToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Retrieves a saved email for auto-filling the login form.
     * In a real application, this should be fetched from a secure storage.
     * This example provides a hardcoded placeholder for demonstration purposes.
     */
    @JavascriptInterface
    fun getSavedEmail(): String {
        // Implementasi untuk mengambil email yang disimpan secara aman.
        // Contoh: return sharedPreferences.getString("saved_email", "");
        return "user@example.com"
    }

    /**
     * Retrieves a saved password for auto-filling.
     *
     * WARNING: Storing passwords in plain text is a significant security risk.
     * This method is for demonstration only. Avoid this in production apps.
     */
    @JavascriptInterface
    fun getSavedPassword(): String {
        // Implementasi untuk mengambil kata sandi yang disimpan secara aman.
        // Sangat tidak disarankan untuk menyimpan kata sandi.
        return "password123"
    }
}
