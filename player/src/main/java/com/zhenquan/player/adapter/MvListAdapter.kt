package com.zhenquan.player.adapter

import android.content.Context
import com.zhenquan.player.base.BaseListAdapter
import com.itheima.player.model.bean.VideosBean
import com.zhenquan.player.widget.MvItemView


/**
 * ClassName:MvListAdapter
 * Description:mv界面每一个list列表的适配器
 */
class MvListAdapter: BaseListAdapter<VideosBean, MvItemView>() {
    override fun refreshItemView(itemView: MvItemView, data: VideosBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): MvItemView {
        return MvItemView(context)
    }
}