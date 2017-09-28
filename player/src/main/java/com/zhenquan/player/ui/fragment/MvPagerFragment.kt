package com.zhenquan.player.ui.fragment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean
import com.lizhenquan.kotlinplayer.wight.SpaceItemDecoration
import com.zhenquan.player.adapter.MvListAdapter
import com.zhenquan.player.base.BaseListAdapter
import com.zhenquan.player.base.BaseListFragment
import com.zhenquan.player.base.BaseListPresenter
import com.zhenquan.player.model.VideoPlayBean
import com.zhenquan.player.presenter.impl.MvListPresenterImpl
import com.zhenquan.player.ui.activity.JiecaoVideoPlayerActivity
import com.zhenquan.player.view.MvListView
import com.zhenquan.player.widget.MvItemView
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * ClassName:MvPagerFragment
 * Description:mv界面每一个页面fragment
 */
class MvPagerFragment: BaseListFragment<MvPagerBean, VideosBean, MvItemView>(), MvListView {
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        recycleView.addItemDecoration(SpaceItemDecoration(10))
        return GridLayoutManager(context,2)
    }
    var code:String? = null
    override fun init() {
        code = arguments.getString("args")
    }
    override fun getSpecialAdapter(): BaseListAdapter<VideosBean, MvItemView> {
        return MvListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return MvListPresenterImpl(code!!, this)
    }

    override fun getList(response: MvPagerBean?): List<VideosBean>? {
        return response?.videos
    }

    override fun initListener() {
        super.initListener()
        //设置条目点击事件监听函数
        adapter.setMyListener{
            val videoPlayBean = VideoPlayBean(it.id, it.title, it.url)
            //跳转到视频播放界面
            startActivity<JiecaoVideoPlayerActivity>("item" to videoPlayBean)
        }
    }
}