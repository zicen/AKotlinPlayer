package com.zhenquan.player.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.util.ToolBarManager
import org.jetbrains.anko.find


/**
 * ClassName:SettingActivity
 * Description:设置界面
 */
class SettingActivity: BaseActivity(), ToolBarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
//        PreferenceFragment
//        PreferenceActivity
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolbar()
        //获取推送通知有没有选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push", false)
        println("push=$push")
    }
}