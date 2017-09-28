package com.zhenquan.player.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.itheima.player.R
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * ClassName:BaseListFragment
 * Description:所有具有下拉刷新和上拉加载更多列表界面的基类
 * 基类抽取
 * HomeView->BaseView
 * Presenter->BaseListPresenter
 * Adapter->BaseListAdapter
 */
abstract class BaseListFragment<RESPONSE,ITEMBEAN,ITEMVIEW:View> : BaseFragment(), BaseView<RESPONSE> {
    override fun onError(message: String?) {
        myToast("加载数据失败")
    }

    override fun loadSuccess(response:RESPONSE?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        adapter.updateList(getList(response))
    }



    override fun loadMore(response: RESPONSE?) {
        adapter.loadMore(getList(response))
    }

    //适配
    val adapter by lazy { getSpecialAdapter() }
//    getAdapter()

    val presenter by lazy { getSpecialPresenter() }
    abstract fun getLayoutManager():RecyclerView.LayoutManager


    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        //初始化recycleview
        recycleView.layoutManager =getLayoutManager()

        recycleView.adapter = adapter

        //初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //刷新监听
        refreshLayout.setOnRefreshListener {
            //刷新监听
            presenter.loadDatas()
        }
        //监听列表滑动
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState== RecyclerView.SCROLL_STATE_IDLE){
                    //是否最后一条已经显示
                    val layoutManager = recyclerView.layoutManager
                    if(layoutManager is LinearLayoutManager){
                        val manager: LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if(lastPosition==adapter.itemCount-1){
                            //最后一条已经显示了
                            presenter.loadMore(adapter.itemCount-1)
                        }
                    }
                }
            }

        })
    }

    override fun initData() {
        //初始化数据
        presenter.loadDatas()
    }
    /**
     * 获取适配器adapter
     */
    abstract fun getSpecialAdapter(): BaseListAdapter<ITEMBEAN, ITEMVIEW>
    /**
     * 获取presenter
     */
    abstract fun getSpecialPresenter(): BaseListPresenter
    /**
     * 从返回结果中获取列表数据集合
     */
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>?
}