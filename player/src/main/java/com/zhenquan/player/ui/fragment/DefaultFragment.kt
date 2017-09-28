package com.zhenquan.player.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.zhenquan.player.base.BaseFragment


/**
 * ClassName:DefaultFragment
 * Description:视频播放界面
 */
class DefaultFragment: BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.text = javaClass.simpleName
        return tv
    }
}