package com.zhenquan.player.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * ClassName:SplashActivity
 * Description:
 */
class SplashActivity: BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        startActivityAndFinish<MainActivity>()

    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(img_splash).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }
}