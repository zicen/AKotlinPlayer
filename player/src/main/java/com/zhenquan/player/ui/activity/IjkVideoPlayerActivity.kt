package com.zhenquan.player.ui.activity

import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*


/**
 * ClassName:VideoPlayerActivity
 * Description:
 */
class IjkVideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_ijk
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

    override fun onDestroy() {
        super.onDestroy()
        //停止播放
        videoView.stopPlayback()
    }
}