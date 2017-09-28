# AKotlinPlayer

##效果图
###1、整体效果图 
    ![](https://github.com/zicen/AKotlinPlayer/blob/master/image/GIF2.gif)
###2、视频播放效果图
        
###3、音乐播放效果图
    

##使用的技術点
    1、6.0权限机制，自定义弹窗
    2、BaseListFragment封装（一个集成了上拉加载更多以及下拉刷新的fragment）
    使用时只需继承BaseListFragment传入相应的泛型即可。
    class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>() {
    3、OKHttp框架自定义封装 类似volly，使用时只需要继承自定义的MRequest创建出一个对应的Request类，调用其excute方法即可。
    4、MediaPlayer+Service+Notification歌曲播放功能实现
    5、jiecaovideoplayer实现视频播放
    6、AsyncQueryHandler查询本地的歌曲文件
    