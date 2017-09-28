package com.zhenquan.player.adapter

import android.content.Context
import com.zhenquan.player.base.BaseListAdapter
import com.itheima.player.model.bean.HomeItemBean
import com.zhenquan.player.widget.HomeItemView


/**
 * ClassName:HomeAdapter
 * Description:
 */
class HomeAdapter : BaseListAdapter<HomeItemBean, HomeItemView>() {
    override fun refreshItemView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }
    override fun getItemView(context: Context?): HomeItemView {

        return HomeItemView(context)
    }
}