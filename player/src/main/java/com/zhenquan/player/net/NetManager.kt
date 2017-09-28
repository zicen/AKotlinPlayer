package com.zhenquan.player.net

import com.zhenquan.player.util.ThreadUtil
import okhttp3.*
import java.io.IOException


/**
 * ClassName:NetManager
 * Description:发送网络请求类
 */
class NetManager private constructor(){
    private val client by lazy { OkHttpClient() }
    companion object {
        val manager by lazy { NetManager() }
    }

    /**
     * 发送网络请求
     */
    fun <RESPONSE> sendRequest(req: MRequest<RESPONSE>){

        val request = Request.Builder()
                .url(req.url)
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable {
                    //隐藏刷新控件
                    req.handler.onError(req.type,e?.message)
                })
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainThread(Runnable { req.handler.onSuccess(req.type,parseResult) })
            }
        })
    }
}