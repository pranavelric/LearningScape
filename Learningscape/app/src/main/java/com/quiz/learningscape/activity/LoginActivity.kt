package com.quiz.learningscape.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.quiz.learningscape.R

class LoginActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        createSignInIntent()
    }


    private fun createSignInIntent() {
        val providers = arrayListOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .setTheme(R.style.LoginUIStyle)
                .setLogo(R.drawable.splahlogo)
                .build(),
            RC_SIGN_IN
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                FirebaseAuth.getInstance().currentUser
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            } else {

                if (response == null) {
                    finish()
                }
                if (response?.error?.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this@LoginActivity, "No internet",Toast.LENGTH_LONG)
                        .show()
                    return
                }

                if (response?.error?.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, response.error?.errorCode.toString(), Toast.LENGTH_LONG)
                        .show()
                    Log.d("ERRORCODE", response.error?.errorCode.toString())
                    return
                }
            }
        }
    }


}