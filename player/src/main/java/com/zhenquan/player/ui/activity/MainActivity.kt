package com.zhenquan.player.ui.activity

import android.support.v7.widget.Toolbar
import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.event.NightEvent
import com.zhenquan.player.util.FragmentUtil
import com.zhenquan.player.util.ToolBarManager
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {
    //惰性加载
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        EventBus.getDefault().register(this)
        return R.layout.activity_main
    }



    override fun initData() {
        initMainToolBar()
    }
    /**
     * 接收eventbus方法
     */
    fun onEventMainThread(itemBean: NightEvent) {
        print("mainactivityonEventMainThread")
        finish()
    }
    override fun initListener() {
        //设置tab切换监听
        bottomBar.setOnTabSelectListener{
//            it 代表参数tabId
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it),it.toString())
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
