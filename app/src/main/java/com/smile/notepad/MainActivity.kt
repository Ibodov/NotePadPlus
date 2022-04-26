package com.smile.notepad

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smile.notepad.databinding.ActivityMainBinding
import com.smile.notepad.utilites.APP_ACTIVITY

class   MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY =
            this //иниц. APP_ACTIVITY с const - этот контекст можно получить в любом месте прил.
        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar) //Установка ToolBar
        title = getString(R.string.title)

//        supportActionBar?.apply {
//            // set action bar background color
//            setBackgroundDrawable(
//                ColorDrawable(
//                    Color.parseColor("#FFEBCD")
//                )
//            )
//
//
//            // we can also show gradient background in action bar
//            // uncomment below line to see gradient background
////            setBackgroundDrawable(gradientDrawable())
//        }

    }

//    // method to generate gradient drawable
//    private fun gradientDrawable(): GradientDrawable {
//        return GradientDrawable().apply {
//            colors = intArrayOf(
//                Color.parseColor("#FF6347"),
//                Color.parseColor("#DEAA88"),
//                Color.parseColor("#EEE600")
//            )
//            gradientType = GradientDrawable.LINEAR_GRADIENT
//            shape = GradientDrawable.RECTANGLE
//            setStroke(2, Color.parseColor("#CD5700"))
//        }
//
//    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}