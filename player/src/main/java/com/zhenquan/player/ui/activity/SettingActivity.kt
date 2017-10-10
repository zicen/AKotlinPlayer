package com.zhenquan.player.ui.activity

import android.content.Intent
import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.event.NightEvent
import com.zhenquan.player.util.ToolBarManager
import de.greenrobot.event.EventBus
import org.jetbrains.anko.find


/**
 * ClassName:SettingActivity
 * Description:设置界面
 */
class SettingActivity : BaseActivity(), ToolBarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    private var oldIsNight: Boolean? = null
    private val sp by lazy { PreferenceManager.getDefaultSharedPreferences(this) }
    override fun getLayoutId(): Int {

        oldIsNight = sp.getBoolean("night", false)
        println("oldIsNight:" + oldIsNight)
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolbar()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun goBack() {
        EventBus.getDefault().post(NightEvent())
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.activity_enter_alpha, R.anim.activity_exit)
        finish()
    }

}