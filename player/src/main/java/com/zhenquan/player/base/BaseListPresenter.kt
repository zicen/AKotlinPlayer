package com.zhenquan.player.base


/**
 * ClassName:BaseListPresenter
 * Description:所有下拉刷新和上拉加载更多列表界面presenter的基类
 */
interface BaseListPresenter {
    companion object {
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }
    fun loadDatas()
    fun loadMore(offset: Int)
    //解绑presenter和view
    fun destoryView()
}