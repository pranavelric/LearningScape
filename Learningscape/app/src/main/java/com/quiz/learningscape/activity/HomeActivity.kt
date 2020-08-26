package com.quiz.learningscape.activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cocosw.bottomsheet.BottomSheet
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.quiz.learningscape.R
import com.quiz.learningscape.fragment.HomeFragment
import com.quiz.learningscape.fragment.ProfileFragment
import com.quiz.learningscape.utilities.OnSwipeTouchListener
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var frag: Fragment
    lateinit var fragmentClass: Class<*>
    private var firestore: FirebaseFirestore? = null
    val auth = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        firestore = FirebaseFirestore.getInstance();
        if (auth != null && intent != null) {
            val menuItem: MutableList<MenuItem> = ArrayList()
            menuItem.add(
                MenuItem(
                    "Home",
                    R.drawable.note
                )
            )
            menuItem.add(
                MenuItem(
                    "Profile",
                    R.drawable.stars2
                )
            )

            navigationDrawer.menuItemList = menuItem
            navigationDrawer.setAppbarTitleTV("Home")
            fragmentClass = HomeFragment::class.java
            try {
                frag = fragmentClass.newInstance() as Fragment
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(R.id.frameLayout, frag).commit()


            navigationDrawer.onMenuItemClickListener =
                SNavigationDrawer.OnMenuItemClickListener { position ->
                    when (position) {
                        0 -> {
                            fragmentClass = HomeFragment::class.java
                        }
                        1 -> {
                            fragmentClass = ProfileFragment::class.java
                        }

                    }



                    navigationDrawer.drawerListener = object : SNavigationDrawer.DrawerListener {

                        override fun onDrawerOpened() {

                        }


                        override fun onDrawerOpening() {
                            setStatusColor("#151515")

                        }

                        override fun onDrawerClosing() {
                            try {
                                frag = fragmentClass.newInstance() as Fragment

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val fragmentManager: FragmentManager = getSupportFragmentManager()
                            fragmentManager.beginTransaction().setCustomAnimations(
                                android.R.animator.fade_in,
                                android.R.animator.fade_out
                            ).replace(R.id.frameLayout, frag).commit()
                        }

                        override fun onDrawerClosed() {

                            setStatusColor("#5F0A87")
                        }

                        override fun onDrawerStateChanged(newState: Int) {

                        }
                    }


                }

            navigationDrawer.drawerListener = object : SNavigationDrawer.DrawerListener {

                override fun onDrawerOpened() {

                }


                override fun onDrawerOpening() {

                    setStatusColor("#151515")
                }

                override fun onDrawerClosing() {

                }

                override fun onDrawerClosed() {
                    setStatusColor("#5F0A87")

                }

                override fun onDrawerStateChanged(newState: Int) {

                }
            }

            frameLayout.setOnTouchListener(object : OnSwipeTouchListener(this@HomeActivity) {
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    if (navigationDrawer.isDrawerOpen)
                        navigationDrawer.closeDrawer()

                }

                override fun onSwipeRight() {
                    super.onSwipeRight()
                    if (!navigationDrawer.isDrawerOpen)
                        navigationDrawer.openDrawer()

                }

                override fun onSwipeUp() {
                    super.onSwipeUp()
                    setBottomSheet()
                }
            })

//to show bottomsheet only for fist time
            val sp: SharedPreferences = getSharedPreferences("mySharedPref", 0)
            val isFirstStart = sp.getBoolean("key", true)
            if (isFirstStart) {
                setBottomSheet()
                val e = sp.edit()
                e.putBoolean("key", false)
                e.apply()
            }


        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }


    }

    private fun setBottomSheet() {

        var appurl=""
        firestore?.collection("Share")?.document("id")
            ?.get()?.addOnSuccessListener {
               appurl= it.get("url").toString()
            }



        BottomSheet.Builder(this).title("Options").sheet(R.menu.home_menu)
            .listener { dialog, which ->
                when (which) {
                    R.id.action_logout -> {
                        AuthUI.getInstance().signOut(this)
                            .addOnSuccessListener {
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                    }
                    R.id.action_update -> {
                        try {
                            val i: Intent = Intent(Intent.ACTION_VIEW)
                            Uri.parse(appurl)
                            i.setData(Uri.parse(appurl))
                            startActivity(i)

                        } catch (e: ActivityNotFoundException) {
                            val i: Intent = Intent(Intent.ACTION_VIEW)
                            Uri.parse(appurl)
                            i.setData(Uri.parse(appurl))
                            startActivity(i)

                        }

                    }
                    R.id.action_share -> {




                        val i: Intent = Intent(Intent.ACTION_SEND)
                        i.type = "text/plain"
                        val shareBody: String = appurl
                        val shareSub: String = "Play amazing quizz and learn something new"
                        i.putExtra(Intent.EXTRA_TEXT, shareBody)
                        i.putExtra(Intent.EXTRA_SUBJECT, shareSub)
                        startActivity(i)

                    }
                    R.id.action_privacy -> {
                        startActivity(Intent(this@HomeActivity, PrivacyPolicyActivity::class.java))

                    }
                    R.id.action_rate -> {
                        try {
                            val i: Intent = Intent(Intent.ACTION_VIEW)
                            Uri.parse(appurl)
                            i.setData(Uri.parse(appurl))
                            startActivity(i)

                        } catch (e: ActivityNotFoundException) {
                            val i: Intent = Intent(Intent.ACTION_VIEW)
                            Uri.parse(appurl)
                            i.setData(Uri.parse(appurl))
                            startActivity(i)

                        }
                    }
                    R.id.action_exit -> {
                        showDialog()
                    }

                }
            }.show()

    }

    private fun showDialog() {
        FancyGifDialog.Builder(this)
            .setTitle("Do you really want to exit")
            .setNegativeBtnBackground("#77FF4081")
            .setNegativeBtnText("Cancel")
            .setPositiveBtnBackground("#df5d49")
            .setPositiveBtnText("Yes")

            .setGifResource(R.drawable.stars)
            .isCancellable(true)
            .OnPositiveClicked {
                finishAffinity()
            }
            .OnNegativeClicked {
            }
            .build()
    }


    private fun setStatusColor(color: String) {
        val toolbarcolor = Color.parseColor(color)

        val window: Window = this@HomeActivity.getWindow();
        window.setStatusBarColor(toolbarcolor);
    }

/*
    fun generateFBKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.quiz.learningscape",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }*/


}