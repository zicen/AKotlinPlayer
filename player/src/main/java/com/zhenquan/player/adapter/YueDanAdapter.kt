package com.zhenquan.player.adapter

import android.content.Context
import com.zhenquan.player.base.BaseListAdapter
import com.itheima.player.model.bean.YueDanBean
import com.zhenquan.player.widget.YueDanItemView


/**
 * ClassName:YueDanAdapter
 * Description:悦单界面适配器
 */
class YueDanAdapter : BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun refreshItemView(itemView: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }
}