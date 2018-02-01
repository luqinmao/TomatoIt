package com.lqm.tomatoit.model.pojoVO;

import com.lqm.tomatoit.model.pojo.ArticleBean;

import java.io.Serializable;
import java.util.List;

/**
 * user：lqm
 * desc：获取一个文章列表（首页，分类，收藏 等通用）
 */

public class ArticleListVO implements Serializable {

    /**
     * datas : [{"id":2207,"title":"慢啄的Xposed文章","chapterId":239,"chapterName":"Xposed","envelopePic":null,"link":"https://www.wrbug.com/categories/xposed%E5%BC%80%E5%8F%91/","author":"慢啄","origin":null,"publishTime":1516332536000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-19","courseId":13,"collect":false},{"id":2206,"title":"QMUI Android","chapterId":301,"chapterName":"快速开发","envelopePic":null,"link":"http://qmuiteam.com/android/page/index.html","author":"小编","origin":null,"publishTime":1516328190000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-19","courseId":13,"collect":false},{"id":2205,"title":"awesome-design","chapterId":299,"chapterName":"创意&素材","envelopePic":null,"link":"https://github.com/gztchan/awesome-design/","author":"小编","origin":null,"publishTime":1516289555000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2204,"title":"Material Design设计模板与素材","chapterId":299,"chapterName":"创意&素材","envelopePic":null,"link":"https://www.uplabs.com/android","author":"小编","origin":null,"publishTime":1516289539000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2203,"title":"iconstore","chapterId":299,"chapterName":"创意&素材","envelopePic":null,"link":"https://iconstore.co/","author":"小编","origin":null,"publishTime":1516289486000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2202,"title":"iconfont","chapterId":299,"chapterName":"创意&素材","envelopePic":null,"link":"http://www.iconfont.cn/","author":"小编","origin":null,"publishTime":1516289466000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2201,"title":"《广研Android卡顿监控系统》","chapterId":78,"chapterName":"性能优化","envelopePic":null,"link":"https://mp.weixin.qq.com/s/MthGj4AwFPL2JrZ0x1i4fw","author":"腾讯Bugly","origin":null,"publishTime":1516286424000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2200,"title":"WebView截长图解决方案","chapterId":98,"chapterName":"WebView","envelopePic":null,"link":"https://www.jianshu.com/p/0faa70e88441","author":"贝聊科技","origin":null,"publishTime":1516280223000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2199,"title":"【Xposed】Android-Hook初探","chapterId":239,"chapterName":"Xposed","envelopePic":null,"link":"http://liompei.com/2018/01/02/%E3%80%90Xposed%E3%80%91Android-Hook%E5%88%9D%E6%8E%A2/","author":"Liompei","origin":null,"publishTime":1516276750000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2198,"title":"Xposed开发初体验","chapterId":239,"chapterName":"Xposed","envelopePic":null,"link":"http://www.jowanxu.top/2017/09/21/Xposed%E5%BC%80%E5%8F%91%E5%88%9D%E4%BD%93%E9%AA%8C/","author":"叫我旺仔","origin":null,"publishTime":1516275347000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2197,"title":"叫我旺仔","chapterId":274,"chapterName":"个人博客","envelopePic":null,"link":"http://www.jowanxu.top/archives/","author":"小编","origin":null,"publishTime":1516275071000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2196,"title":"Android Studio 掌握这些调试技巧，Debug能力不能再高啦","chapterId":60,"chapterName":"Android Studio相关","envelopePic":null,"link":"https://www.jianshu.com/p/985f788fae2c","author":"亦枫","origin":null,"publishTime":1516270173000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2194,"title":"百度移动统计","chapterId":300,"chapterName":"互联网统计","envelopePic":null,"link":"https://mtj.baidu.com/data/mobile/device","author":"小编","origin":null,"publishTime":1516266460000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2193,"title":"猎豹大数据","chapterId":300,"chapterName":"互联网统计","envelopePic":null,"link":"http://cn.data.cmcm.com/rank","author":"小编","origin":null,"publishTime":1516266420000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2192,"title":"无版权素材站","chapterId":299,"chapterName":"创意&素材","envelopePic":null,"link":"https://unsplash.com/","author":"小编","origin":null,"publishTime":1516252267000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2189,"title":"Android 混淆查缺补漏","chapterId":295,"chapterName":"混淆","envelopePic":null,"link":"https://mp.weixin.qq.com/s/g7AxmLHvJTX-JyGqs4SaHg","author":"Othershe","origin":null,"publishTime":1516241162000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-18","courseId":13,"collect":false},{"id":2188,"title":"Android实现修改状态栏背景 字体 图标颜色","chapterId":186,"chapterName":"沉浸式","envelopePic":null,"link":"https://juejin.im/post/5a30f1535188251c11409d77","author":"jlanglang","origin":null,"publishTime":1516203303000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-17","courseId":13,"collect":false},{"id":2187,"title":"搭建Android上的服务器 实现隔空取物","chapterId":298,"chapterName":"我的博客","envelopePic":null,"link":"http://www.wanandroid.com/blog/show/2020","author":"鸿洋","origin":null,"publishTime":1516153307000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-17","courseId":13,"collect":false},{"id":2088,"title":"Xposed 框架解析","chapterId":239,"chapterName":"Xposed","envelopePic":null,"link":"https://www.jianshu.com/p/2b8343c774df","author":"王永迪 ","origin":null,"publishTime":1516094972000,"zan":null,"desc":null,"visible":1,"niceDate":"2018-01-16","courseId":13,"collect":false},{"id":985,"title":"Android Studio 使用Gradle多渠道打包","chapterId":169,"chapterName":"gradle","envelopePic":"","link":"https://mp.weixin.qq.com/s/yKfesG8lodfhJVA-rPfyRg","author":"loonggg","origin":"非著名程序员","publishTime":1516003287000,"zan":0,"desc":"我们都知道国内应用市场非常多，为了统计各个应用市场的app下载量和使用情况，我们需要多渠道的打包。如果一个一个的手动去打包岂不烦死了，要多麻烦就有多麻烦。这就要求我们学会使用Gradle进行多渠道打包。","visible":1,"niceDate":"2018-01-15","courseId":13,"collect":false}]
     * offset : 20
     * size : 20
     * total : 1014
     * pageCount : 51
     * curPage : 2
     * over : false
     */

    private int offset;
    private int size;
    private int total;
    private int pageCount;
    private int curPage;
    private boolean over;
    private List<ArticleBean> datas;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }

}
