package com.zhenquan.player.presenter.impl

import com.itheima.player.model.bean.MvAreaBean
import com.zhenquan.player.net.MvAreaRequest
import com.zhenquan.player.net.ResponseHandler
import com.zhenquan.player.presenter.interf.MvPresenter
import com.zhenquan.player.view.MvView


/**
 * ClassName:MvPresenterImpl
 * Description:
 */
class MvPresenterImpl(var mvView: MvView): MvPresenter, ResponseHandler<List<MvAreaBean>> {
    override fun onError(type: Int, msg: String?) {
        mvView.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<MvAreaBean>) {
        mvView.onSuccess(result)
    }

    /**
     * 加载区域数据
     */
    override fun loadDatas() {
        MvAreaRequest(this).excute()
    }
}