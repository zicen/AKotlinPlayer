package com.zhenquan.player.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.itheima.player.model.bean.HomeItemBean
import com.zhenquan.player.adapter.HomeAdapter
import com.zhenquan.player.base.BaseListAdapter
import com.zhenquan.player.base.BaseListFragment
import com.zhenquan.player.base.BaseListPresenter
import com.zhenquan.player.presenter.impl.HomePresenterImpl
import com.zhenquan.player.widget.HomeItemView


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

    override fun onDestroyView() {
        super.onDestroyView()
        //解绑presenter
        presenter.destoryView()
    }
}