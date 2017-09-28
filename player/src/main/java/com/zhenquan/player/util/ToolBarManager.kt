package com.zhenquan.player.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import com.itheima.player.R
import com.zhenquan.player.ui.activity.SettingActivity


/**
 * ClassName:ToolBarManager
 * Description:所有界面toolbar的管理类
 */
interface ToolBarManager {
    val toolbar: Toolbar
    /**
     * 初始化主界面中的toolbar
     */
    fun initMainToolBar() {
        toolbar.setTitle(R.string.app_name)
        toolbar.inflateMenu(R.menu.main)
        //kotlin 和java调用特性
        //如果java接口中只有一个未实现的方法  可以省略接口对象 直接用{}表示未实现的方法
        toolbar.setOnMenuItemClickListener {
            toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))
            true
        }
//        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId) {
//                    R.id.setting -> {
//                        //跳转到设置界面
//                        toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))
//                    }
//                }
//                return true
//            }
//
//        })

    }

    /**
     * 处理设置界面的toolbar
     */
    fun initSettingToolbar(){
        toolbar.setTitle("设置界面")
    }

}