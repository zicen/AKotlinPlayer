package com.zhenquan.player.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType


/**
 * ClassName:MRequest
 * Description:所有请求基类
 */
open class MRequest<RESPONSE>(val type:Int,val url:String,val handler: ResponseHandler<RESPONSE>) {
    /**
     * 解析网络请求结果
     */
    fun parseResult(result: String?): RESPONSE {
        val gson = Gson()
        //获取泛型类型
        val type = (this.javaClass
                .genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return gson.fromJson<RESPONSE>(result, type)
    }

    /**
     * 发送网络请求
     */
    fun excute(){
        NetManager.manager.sendRequest(this)
    }
}