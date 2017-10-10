package com.zhenquan

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import org.jetbrains.anko.defaultSharedPreferences

/**
 * Created by zhenquan on 2017/10/10.
 */
class App  : Application() {

    override fun onCreate() {
        super.onCreate()
        setNightMode()
    }

    /**
     *
     */
     fun setNightMode(){
        val nightMode = defaultSharedPreferences.getBoolean("night", false)
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}