package com.etnow.etnowlegends

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.databinding.ActivityHomeBinding
import com.etnow.etnowlegends.drawer_content.AboutActivity
import com.etnow.etnowlegends.drawer_content.KetentuanLayananActivity
import com.etnow.etnowlegends.drawer_content.PetunjukUmumActivity
import com.etnow.etnowlegends.drawer_content.SettingActivity
import com.etnow.etnowlegends.legends_adventure.LegendsAdventureDashboardActivity
import com.etnow.etnowlegends.materi.SubMateriActivity
import com.etnow.etnowlegends.utils.BottomSheetFragment
import com.google.android.material.navigation.NavigationView
import java.util.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var binding: ActivityHomeBinding? = null
    private lateinit var prefs: SharedPreferences
    private var mp: MediaPlayer? = null

    override fun onResume() {
        super.onResume()

        val music = prefs.getBoolean("music", false)
        if(music) {
            mp = MediaPlayer.create(this, R.raw.sound)
            mp?.isLooping = true
            mp?.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        prefs = getSharedPreferences(
            "com.etnow.etnowlegends", Context.MODE_PRIVATE
        )
        val name = prefs.getString("key", "")
        val className = prefs.getString("class", "")
        val school = prefs.getString("school", "")

        binding?.menu?.setOnClickListener {
            val navDrawer = binding?.drawerLayout
            if (!navDrawer?.isDrawerOpen(GravityCompat.START)!!) navDrawer.openDrawer(GravityCompat.START)
            else navDrawer.closeDrawer(GravityCompat.END)
        }

        binding?.name?.text = name

        populateHeader(name, className, school)

        binding?.imageView3?.let {
            Glide
                .with(this)
                .load(R.drawable.header)
                .into(it)
        }

        binding?.imageView5?.let {
            Glide
                .with(this)
                .load(R.drawable.materi_kd_ki_i)
                .into(it)
        }

        binding?.imageView6?.let {
            Glide
                .with(this)
                .load(R.drawable.materi_candi)
                .into(it)
        }

        binding?.imageView7?.let {
            Glide
                .with(this)
                .load(R.drawable.materi_bangun_datar)
                .into(it)
        }

        binding?.imageView8?.let {
            Glide
                .with(this)
                .load(R.drawable.legends_adventure)
                .into(it)
        }

        populateBottomSheetFragment()
        populateMenu()
       // searchMenu()

        binding?.navView?.setNavigationItemSelectedListener(this)

    }

    private fun searchMenu() {
        binding?.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isEmpty()) {
                    binding?.constraintLayout?.visibility = View.VISIBLE
                    binding?.constraintLayout2?.visibility = View.VISIBLE
                    binding?.constraintLayout3?.visibility = View.VISIBLE
                    binding?.constraintLayout4?.visibility = View.VISIBLE
                } else {

                    val toLower = p0.toString().lowercase(Locale.getDefault())
                    Log.d("tah", toLower)
                    if("".contains(toLower)) {
                        binding?.constraintLayout?.visibility = View.VISIBLE
                    }
                    if("sejarah dan informasi candi sanggrahan".contains(toLower)) {
                        binding?.constraintLayout2?.visibility = View.VISIBLE
                    }
                    if("materi bangun datar".contains(toLower)) {
                        binding?.constraintLayout3?.visibility = View.VISIBLE
                    }
                    if("legends adventure!".contains(toLower)) {
                        binding?.constraintLayout4?.visibility = View.VISIBLE
                    }
                    else {
                        binding?.constraintLayout?.visibility = View.GONE
                        binding?.constraintLayout2?.visibility = View.GONE
                        binding?.constraintLayout3?.visibility = View.GONE
                        binding?.constraintLayout4?.visibility = View.GONE
                    }
                }
            }

        })
    }

    private fun populateMenu() {
        binding?.candi?.setOnClickListener {
            startActivity(Intent(this, TempleHistoryActivity::class.java))
        }

        binding?.kdKi?.setOnClickListener {
            startActivity(Intent(this, CompetencyActivity::class.java))
        }

        binding?.bangunDatar?.setOnClickListener {
            startActivity(Intent(this, SubMateriActivity::class.java))
        }

        binding?.legendsAdventure?.setOnClickListener {
            startActivity(Intent(this, LegendsAdventureDashboardActivity::class.java))
        }

    }

    private fun populateBottomSheetFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        binding?.help?.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
        }
    }

    private fun populateHeader(name: String?, className: String?, school: String?) {
        val navView = binding?.navView
        val hView = navView?.getHeaderView(0)
        val nameHeader = hView?.findViewById<View>(R.id.name) as TextView
        nameHeader.text = name

        val classNameHeader = hView.findViewById<View>(R.id.className) as TextView
        classNameHeader.text = className

        val schoolHeader = hView.findViewById<View>(R.id.school) as TextView
        schoolHeader.text = school
    }

    override fun onBackPressed() {
        val navDrawer = binding?.drawerLayout
        if (navDrawer!!.isDrawerOpen(GravityCompat.START)) {
            navDrawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_help -> {
                startActivity(Intent(this, PetunjukUmumActivity::class.java))
            }
            R.id.nav_settings -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_about_apps -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            R.id.nav_advice -> {
                val saran = Intent(Intent.ACTION_VIEW, Uri.parse("bit.ly/feedbackel"))
                startActivity(saran)
            }
            R.id.nav_faq -> {
                startActivity(Intent(this, KetentuanLayananActivity::class.java))
            }
            R.id.nav_logout -> {
                val intent = Intent(this, OnboardingActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}