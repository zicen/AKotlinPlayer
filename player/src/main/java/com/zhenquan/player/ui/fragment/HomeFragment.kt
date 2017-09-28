package com.zhenquan.player.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.itheima.player.model.bean.HomeItemBean
import com.zhenquan.player.adapter.HomeAdapter
import com.zhenquan.player.base.BaseListAdapter
import com.zhenquan.player.base.BaseListFragment
import com.zhenquan.player.base.BaseListPresenter
import com.zhenquan.player.model.VideoPlayBean
import com.zhenquan.player.presenter.impl.HomePresenterImpl
import com.zhenquan.player.ui.activity.JiecaoVideoPlayerActivity
import com.zhenquan.player.widget.HomeItemView
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>() {
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun getSpecialAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }

    override fun initListener() {
        super.initListener()
        //设置条目点击事件监听函数
        adapter.setMyListener {

            if (it?.url != null) {
                val videoPlayBean = VideoPlayBean(it.id, it.title, it?.url)
                //跳转到视频播放界面
                startActivity<JiecaoVideoPlayerActivity>("item" to videoPlayBean)
            }else{
                toast("获取视频url为空！")
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //解绑presenter
        presenter.destoryView()
    }
}