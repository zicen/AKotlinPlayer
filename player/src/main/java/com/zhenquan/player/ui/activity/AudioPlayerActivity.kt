package com.zhenquan.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.itheima.player.R
import com.zhenquan.player.adapter.PopAdapter
import com.zhenquan.player.base.BaseActivity
import com.zhenquan.player.model.AudioBean
import com.zhenquan.player.service.AudioService
import com.zhenquan.player.service.Iservice
import com.zhenquan.player.util.StringUtil
import com.zhenquan.player.widget.PlayListPopWindow
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*


/**
 * ClassName:AudioPlayerActivity
 * Description:
 */
class AudioPlayerActivity : BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemClickListener {
    /**
     * 弹出的播放列表条目点击事件
     */
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //播放当前的歌曲
        iService?.playPosition(p2)
    }

    /**
     * 进度改变回调
     * p1:改变之后的进度
     * p2:true 通过用户手指拖动改变进度  false代表通过代码方式改变进度
     */
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        //判断是否是用户操作
        if (!p2) return
        //更新播放进度
        iService?.seekTo(p1)
        //更新界面进度显示
        updateProgress(p1)
    }

    /**
     * 手指触摸seekbar回调
     */
    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    /**
     * 手指离开seekbar回调
     */
    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    var audioBean: AudioBean? = null
    var drawable: AnimationDrawable? = null
    var duration: Int = 0
    //    companion object {
    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MSG_PROGRESS -> startUpdateProgress()
            }
        }
    }
//    }


    val MSG_PROGRESS = 0
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.state -> updatePlayState()
            R.id.mode -> updatePlayMode()
            R.id.pre->iService?.playPre()
            R.id.next->iService?.playNext()
            R.id.playlist->showPlayList()
        }
    }

    /**
     * 显示播放列表
     */
    private fun showPlayList() {
        val list = iService?.getPlayList()
        list?.let {
        //创建adapter
            val adapter = PopAdapter(list)
            //获取底部高度
            val bottomH = audio_player_bottom.height
            val popWindow = PlayListPopWindow(this, adapter, this, window)
            popWindow.showAsDropDown(audio_player_bottom,0,-bottomH)
        }
    }

    /**
     * 更新播放模式
     */
    private fun updatePlayMode() {
        //修改service中的mode
        iService?.updatePlayMode()
        //修改界面模式图标
        updatePlayModeBtn()
    }

    /**
     * 根据播放模式修改播放模式图标
     */
    private fun updatePlayModeBtn() {
        iService?.let {
            //获取播放模式
            val modeI: Int = it.getPlayMode()
            //设置图标
            when(modeI){
                AudioService.MODE_ALL->mode.setImageResource(R.drawable.selector_btn_playmode_order)
                AudioService.MODE_SINGLE->mode.setImageResource(R.drawable.selector_btn_playmode_single)
                AudioService.MODE_RANDOM->mode.setImageResource(R.drawable.selector_btn_playmode_random)
            }
        }
    }

    /**
     * 接收eventbus方法
     */
    fun onEventMainThread(itemBean: AudioBean) {
        //设置播放歌曲名称
        lyricView.setSongName(itemBean.display_name)
        //记录播放歌曲bean
        this.audioBean = itemBean
        //歌曲名
        audio_title.text = itemBean.display_name
        //歌手名
        artist.text = itemBean.artist
        //更新播放状态按钮
        updatePlayStateBtn()
        //动画播放
//        val drawable = audio_anim.background as AnimationDrawable
        drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()
        //获取总进度
        duration = iService?.getDuration() ?: 0
        //设置歌词播放总进度
        lyricView.setSongDuration(duration)
        //进度条设置进度最大值
        progress_sk.max = duration
        //更新播放进度
        startUpdateProgress()
        //更新播放模式图标
        updatePlayModeBtn()
    }

    /**
     * 开始更新进度
     */
    private fun startUpdateProgress() {
        //获取当前进度
        val progress: Int = iService?.getProgress() ?: 0
        //更新进度数据
        updateProgress(progress)
        //定时获取进度
        handler.sendEmptyMessage(MSG_PROGRESS)
    }

    /**
     * 根据当前进度数据更新界面
     */
    private fun updateProgress(pro: Int) {
        //更新进度数值
        progress.text = StringUtil.parseDuration(pro) + "/" + StringUtil.parseDuration(duration)
        //更新进度条
        progress_sk.setProgress(pro)
        //更新歌词播放进度
        lyricView.updateProgress(pro)
    }

    /**
     * 更新播放状态
     */
    private fun updatePlayState() {
        //更新播放状态
        iService?.updatePlayState()
        //更新播放状态图标
        updatePlayStateBtn()
    }

    /**
     * 根据播放状态更新图标
     */
    private fun updatePlayStateBtn() {
        //获取当前播放状态
        val isPlaying = iService?.isPlaying()
        isPlaying?.let {
            //根据状态更新图标
            if (isPlaying) {
                //播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
                //开始播放动画
                drawable?.start()
                //开始更新进度
                handler.sendEmptyMessage(MSG_PROGRESS)
            } else {
                //暂停
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                //停止播放动画
                drawable?.stop()
                //停止更新进度
                handler.removeMessages(MSG_PROGRESS)
            }
        }
    }

    override fun initListener() {
        //播放状态切换
        state.setOnClickListener(this)
        back.setOnClickListener { finish() }
        //进度条变化监听
        progress_sk.setOnSeekBarChangeListener(this)
        //播放模式点击事件
        mode.setOnClickListener(this)
        //上一曲和下一曲点击事件
        pre.setOnClickListener(this)
        next.setOnClickListener(this)
        //播放列表
        playlist.setOnClickListener(this)
        //歌词拖动进度更新监听
        lyricView.setProgressListener {
            //更新播放进度
            iService?.seekTo(it)
            //更新进度显示
            updateProgress(it)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val conn by lazy { AudioConnection() }
    override fun initData() {
        //注册EventBus
        EventBus.getDefault().register(this)

//        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position",-1)

        //通过audioservice播放音乐
        val intent = intent
        //修改
        intent.setClass(this, AudioService::class.java)
        //通过intent将list以及position传递过去
//        intent.putExtra("list",list)
//        intent.putExtra("position",position)
        //先绑定
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        //再开启
        startService(intent)

//        //播放音乐
//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener {
//            //开始播放
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list.get(position).data)
//        mediaPlayer.prepareAsync()
    }

    var iService: Iservice? = null

    inner class AudioConnection : ServiceConnection {
        /**
         * service连接时
         */
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            iService = p1 as Iservice
        }

        /**
         * 意外断开连接时
         */
        override fun onServiceDisconnected(p0: ComponentName?) {

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(conn)
        //反注册EventBus
        EventBus.getDefault().unregister(this)
        //清空handler发送的所有消息
        handler.removeCallbacksAndMessages(null)
    }
}