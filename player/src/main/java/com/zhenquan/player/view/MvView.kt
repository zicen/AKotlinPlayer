package com.zhenquan.player.view

import com.itheima.player.model.bean.MvAreaBean


/**
 * ClassName:MvView
 * Description:
 */
interface MvView {
    fun  onError(msg: String?)
    fun  onSuccess(result: List<MvAreaBean>)
}