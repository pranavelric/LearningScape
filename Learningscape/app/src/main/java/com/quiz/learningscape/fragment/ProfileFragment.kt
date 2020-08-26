package com.quiz.learningscape.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.quiz.learningscape.R
import com.quiz.learningscape.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    val auth = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        createUI(v)
        return v
    }

    fun createUI(v:View) {

        val list = auth?.providerData
        var providerData: String = ""
        list?.let {
            for (provider in list) {
                providerData = providerData + " " + provider.providerId
            }
        }

        auth?.let {
            v.txtName.text = auth.displayName
            v.txtEmail.text = auth.email
            v.txtPhone.text = auth.phoneNumber

            var photoUrl: String = auth.photoUrl.toString()
            photoUrl = "$photoUrl?height=500"
            Glide
                .with(this)
                .load(photoUrl)
                .fitCenter()
                .placeholder(R.drawable.profilepic)
                .into(v.profile_image)


        }




        v.btnLogOut.setOnClickListener {
            AuthUI.getInstance().signOut(activity!!)
                .addOnSuccessListener {
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    activity!!.finish()

                }

        }
    }

}