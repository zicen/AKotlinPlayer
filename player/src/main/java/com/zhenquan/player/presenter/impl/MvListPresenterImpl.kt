package com.zhenquan.player.presenter.impl

import com.zhenquan.player.base.BaseListPresenter
import com.itheima.player.model.bean.MvPagerBean
import com.zhenquan.player.net.MvListRequest
import com.zhenquan.player.net.ResponseHandler
import com.zhenquan.player.presenter.interf.MvListPresenter
import com.zhenquan.player.view.MvListView


/**
 * ClassName:MvListPresenterImpl
 * Description:
 */
class MvListPresenterImpl(var code:String, var mvListView: MvListView?): MvListPresenter, ResponseHandler<MvPagerBean> {
    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: MvPagerBean) {
        if(type== BaseListPresenter.TYPE_INIT_OR_REFRESH){
            mvListView?.loadSuccess(result)
        }else if(type== BaseListPresenter.TYPE_LOAD_MORE){
            mvListView?.loadMore(result)
        }
    }

    override fun loadDatas() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, code, 0, this).excute()
    }

    override fun loadMore(offset: Int) {
        MvListRequest(BaseListPresenter.TYPE_LOAD_MORE, code, offset, this).excute()
    }

    override fun destoryView() {
        if(mvListView!=null){
            mvListView=null
        }
    }
}