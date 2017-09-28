package com.zhenquan.player.ui.activity

import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player.*


/**
 * ClassName:VideoPlayerActivity
 * Description:
 */
class VideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player
    }

    override fun initData() {
        //获取传递的数据
        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        videoView.setVideoPath(videoPlayBean.url)//异步准备
//        videoView.start()
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }
}