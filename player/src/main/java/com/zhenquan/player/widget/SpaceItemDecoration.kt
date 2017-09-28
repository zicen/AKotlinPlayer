package com.lizhenquan.kotlinplayer.wight

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zhenquan on 2017/9/26.
 */
class SpaceItemDecoration : RecyclerView.ItemDecoration {
    var space:Int?=null

    constructor(space: Int?) : super() {
        this.space = space
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.left = space
        outRect?.bottom = space
        //由于每行都只有2个，所以第一个都是2的倍数，把左边距设为0
        if (parent?.getChildLayoutPosition(view) %2==0) {
            outRect?.left = 0
        }
    }



}