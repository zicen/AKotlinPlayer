##AKotlinPlayer

效果图
-----------

#### 整体效果图
![](https://github.com/zicen/AKotlinPlayer/blob/master/image/%E6%95%B4%E4%BD%93%E6%95%88%E6%9E%9C%E5%9B%BE.gif)

#### 视频播放效果图
![](https://github.com/zicen/AKotlinPlayer/blob/master/image/%E8%A7%86%E9%A2%91%E6%92%AD%E6%94%BE%E6%95%88%E6%9E%9C%E5%9B%BE.gif)

#### 音乐播放效果图
![](https://github.com/zicen/AKotlinPlayer/blob/master/image/%E6%9C%AC%E5%9C%B0%E9%9F%B3%E4%B9%90%E6%92%AD%E6%94%BE.gif)

使用的技术点
----------
    - 6.0权限机制，自定义弹窗
    - BaseListFragment封装（一个集成了上拉加载更多以及下拉刷新的fragment）
    使用时只需继承BaseListFragment传入相应的泛型即可。
    class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>() {
    - OKHttp框架自定义封装 类似volly，使用时只需要继承自定义的MRequest创建出一个对应的Request类，调用其excute方法即可。
    - MediaPlayer+Service+Notification歌曲播放功能实现
    - jiecaovideoplayer实现视频播放
    - AsyncQueryHandler查询本地的歌曲文件
    - 夜间模式切换
    - 。。。。。。
   