package com.zhenquan.player.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.preference.SwitchPreference
import android.support.v7.app.AppCompatDelegate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itheima.player.R
import com.zhenquan.player.ui.activity.AboutActivity
import com.zhenquan.player.ui.activity.SettingActivity
import org.jetbrains.anko.defaultSharedPreferences


/**
 * ClassName:SettingFragment
 * Description:
 */
class SettingFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addPreferencesFromResource(R.xml.setting)
        val switchPreference = findPreference("night") as SwitchPreference
        switchPreference.onPreferenceChangeListener = this
        return super.onCreateView(inflater, container, savedInstanceState)
    }

   override fun onPreferenceChange(preference: Preference, objValue: Any): Boolean {
        val key = preference.key
        if ("night" == key) {
            var nightMode = defaultSharedPreferences.getBoolean("night", false)
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            startActivity(Intent(activity, SettingActivity::class.java))
            activity.overridePendingTransition(R.anim.activity_enter_alpha,R.anim.activity_enter_alpha)
            activity.finish()
        }
        return true
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if ("about" == key) {
            //点击了关于
            activity.startActivity(Intent(activity, AboutActivity::class.java))
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}