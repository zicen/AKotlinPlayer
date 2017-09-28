package com.zhenquan.player.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.itheima.player.R
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_texture.*


/**
 * ClassName:VideoPlayerActivity
 * Description:
 */
class TextureVideoPlayerActivity : BaseActivity(), TextureView.SurfaceTextureListener {
    override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
        //view大小变化
    }

    override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
        //视图更新
    }

    override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
        //关闭mediaplayer
        mediaPlayer?.let {
            mediaPlayer.stop()
            mediaPlayer.release()

        }
        //视图销毁
        return true
    }

    override fun onSurfaceTextureAvailable(p0: SurfaceTexture?, p1: Int, p2: Int) {
        videoPlayBean?.let {
            //视图可用

            mediaPlayer.setDataSource(it.url)
            mediaPlayer.setSurface(Surface(p0))//设置播放视频画面
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
                //旋转画面
                textureView.rotation = 100f
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_texture
    }

    var videoPlayBean: VideoPlayBean? = null
    val mediaPlayer by lazy { MediaPlayer() }
    override fun initData() {
        //获取传递的数据
        videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")

        textureView.surfaceTextureListener = this
    }
}