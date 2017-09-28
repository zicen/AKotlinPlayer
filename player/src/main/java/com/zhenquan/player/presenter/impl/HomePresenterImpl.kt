package com.zhenquan.player.presenter.impl

import com.zhenquan.player.base.BaseListPresenter
import com.zhenquan.player.base.BaseView
import com.itheima.player.model.bean.HomeItemBean
import com.zhenquan.player.net.HomeRequest
import com.zhenquan.player.net.ResponseHandler
import com.zhenquan.player.presenter.interf.HomePresenter


/**
 * ClassName:HomePresenterImpl
 * Description:
 */
class HomePresenterImpl(var homeView: BaseView<List<HomeItemBean>>?): HomePresenter, ResponseHandler<List<HomeItemBean>> {
    /**
     * 解绑view和presenter
     */
    override fun destoryView(){
        if(homeView!=null){
            homeView = null
        }
    }
    //失败
    override fun onError(type:Int,msg: String?) {
        homeView?.onError(msg)
    }

    /**
     * 加载数据成功
     */
    override fun onSuccess(type:Int,result: List<HomeItemBean>) {
        //区分初始化  加载更多
        when(type){
            BaseListPresenter.TYPE_INIT_OR_REFRESH->homeView?.loadSuccess(result)
            BaseListPresenter.TYPE_LOAD_MORE->homeView?.loadMore(result)
        }
    }

    /**
     * 初始化数据或者刷新
     */
    override fun loadDatas() {
        //定义request
        HomeRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).excute()
//        NetManager.manager.sendRequest(request)
        //发送request
//        val path = URLProviderUtils.getHomeUrl(0, 20)
//        val client = OkHttpClient()
//        val request = Request.Builder()
//                .url(path)
//                .get()
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            /**
//             * 子线程调用
//             */
//            override fun onFailure(call: Call?, e: IOException?) {
//                ThreadUtil.runOnMainThread(object : Runnable {
//                    override fun run() {
//                        //回调到view层处理
//                        homeView.onError(e?.message)
//                    }
//                })
//            }
//
//            /**
//             * 子线程调用
//             */
//            override fun onResponse(call: Call?, response: Response?) {
//                val result = response?.body()?.string()
//                val gson = Gson()
//                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
//                ThreadUtil.runOnMainThread(object : Runnable {
//                    override fun run() {
//                       //将正确的结果回调到view层
//                        homeView.loadSuccess(list)
//                    }
//                })
//            }
//        })
    }
    //volley
    override fun loadMore(offset: Int) {
        //定义request
        HomeRequest(BaseListPresenter.TYPE_LOAD_MORE, offset, this).excute()
//        NetManager.manager.sendRequest(request)
//        val path = URLProviderUtils.getHomeUrl(offset, 20)
//        val client = OkHttpClient()
//        val request = Request.Builder()
//                .url(path)
//                .get()
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            /**
//             * 子线程调用
//             */
//            override fun onFailure(call: Call?, e: IOException?) {
//                ThreadUtil.runOnMainThread(object : Runnable {
//                    override fun run() {
//                        //隐藏刷新控件
//                        homeView.onError(e?.message)
//                    }
//                })
//            }
//
//            /**
//             * 子线程调用
//             */
//            override fun onResponse(call: Call?, response: Response?) {
//                val result = response?.body()?.string()
//                val gson = Gson()
//                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
//                ThreadUtil.runOnMainThread(object : Runnable {
//                    override fun run() {
//                        homeView.loadMore(list)
//                    }
//                })
//            }
//        })
    }

}