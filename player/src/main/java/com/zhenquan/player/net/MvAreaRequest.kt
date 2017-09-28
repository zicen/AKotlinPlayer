package com.zhenquan.player.net

import com.itheima.player.model.bean.MvAreaBean
import com.zhenquan.player.util.URLProviderUtils


/**
 * ClassName:MvAreaRequest
 * Description:mv区域数据请求
 */
class MvAreaRequest(handler: ResponseHandler<List<MvAreaBean>>)
    : MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), handler) {
}