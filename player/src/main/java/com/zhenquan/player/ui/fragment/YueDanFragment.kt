package com.zhenquan.player.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.itheima.player.model.bean.YueDanBean
import com.zhenquan.player.adapter.YueDanAdapter
import com.zhenquan.player.base.BaseListAdapter
import com.zhenquan.player.base.BaseListFragment
import com.zhenquan.player.base.BaseListPresenter
import com.zhenquan.player.presenter.impl.YueDanPresenterImpl
import com.zhenquan.player.widget.YueDanItemView


/**
 * ClassName:HomeFragment
 * Description:悦单界面
 */
class YueDanFragment: BaseListFragment<YueDanBean, YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }
    override fun getSpecialAdapter(): BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView> {
        return YueDanAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getList(response: YueDanBean?): List<YueDanBean.PlayListsBean>? {
        return response?.playLists
    }

}