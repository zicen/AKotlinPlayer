package com.zhenquan.player.adapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.View
import android.view.ViewGroup
import com.zhenquan.player.model.AudioBean
import com.zhenquan.player.widget.VbangItemView


/**
 * ClassName:VbangAdapter
 * Description:v榜界面列表适配器
 */
class VbangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    /**
     * 创建条目view
     */
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VbangItemView(context)
    }

    /**
     * view+data
     */
    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        //view
        val itemView = view as VbangItemView
        //data
        val itemBean = AudioBean.getAudioBean(cursor)
        //view+data
        itemView.setData(itemBean)
    }
}