package com.lqm.tomatoit.api;

import com.lqm.tomatoit.app.AppConst;
import com.lqm.tomatoit.helper.JsonConvert;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.model.pojo.HotKeyBean;
import com.lqm.tomatoit.model.pojo.UserBean;
import com.lqm.tomatoit.model.pojoVO.ArticleListVO;
import com.lqm.tomatoit.model.pojoVO.TypeTagVO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okrx2.adapter.ObservableBody;

import java.util.List;

import io.reactivex.Observable;

/**
 * user：lqm
 * desc：玩Android提供的api接口
 * www.wanandroid.com
 */

public class WanService {

    private static String homeDataList = AppConst.BASE_URL + "article/list/{page}/json";
    private static String homeBannerData = AppConst.BASE_URL + "banner/json";
    private static String hotKeyUrl = AppConst.BASE_URL + "hotkey/json";
    private static String loginUrl = AppConst.BASE_URL + "user/login";
    private static String registUrl = AppConst.BASE_URL + "user/register";
    private static String getClollectData = AppConst.BASE_URL + "lg/collect/list/0/json";

    /**
     * 首页Banner
     *
     * @GET("/banner/json")
     */
    public static Observable<ResponseData<List<BannerBean>>> getBannerData() {
        return OkGo.<ResponseData<List<BannerBean>>>get(homeBannerData)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .converter(new JsonConvert<ResponseData<List<BannerBean>>>() {
                })
                .adapt(new ObservableBody<ResponseData<List<BannerBean>>>());
    }

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page
     * @GET("/article/list/{page}/json")
     */
    public static Observable<ResponseData<ArticleListVO>> getHomeData(int page) {
        String url = AppConst.BASE_URL + "article/list/" + page + "/json";
        return OkGo.<ResponseData<ArticleListVO>>get(url)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //使用缓存
                .converter(new JsonConvert<ResponseData<ArticleListVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<ArticleListVO>>());
    }

    /**
     * 知识体系 (类别tag)
     * http://www.wanandroid.com/tree/json
     *
     * @GET("/tree/json")
     */
    public static Observable<ResponseData<List<TypeTagVO>>> getTypeTagData() {
        String url = AppConst.BASE_URL + "tree/json";
        return OkGo.<ResponseData<List<TypeTagVO>>>get(url)
                .converter(new JsonConvert<ResponseData<List<TypeTagVO>>>() {
                })
                .adapt(new ObservableBody<ResponseData<List<TypeTagVO>>>());
    }


    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     *
     * @param page page
     * @param cid  cid
     * @GET("/article/list/{page}/json")
     */
    public static Observable<ResponseData<ArticleListVO>> getTypeDataById(int page, int cid) {
        String url = AppConst.BASE_URL + "article/list/" + page + "/json";
        return OkGo.<ResponseData<ArticleListVO>>get(url)
                .params("cid", cid)
                .converter(new JsonConvert<ResponseData<ArticleListVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<ArticleListVO>>());
    }

    /**
     * 大家都在搜
     * http://www.wanandroid.com/hotkey/json
     *
     * @GET("/hotkey/json")
     */
    public static Observable<ResponseData<List<HotKeyBean>>> getHotKey() {
        return OkGo.<ResponseData<List<HotKeyBean>>>get(hotKeyUrl)
                .converter(new JsonConvert<ResponseData<List<HotKeyBean>>>() {
                })
                .adapt(new ObservableBody<ResponseData<List<HotKeyBean>>>());
    }

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page page
     * @param k    POST search key
     * @POST("/article/query/{page}/json")
     */
    public static Observable<ResponseData<ArticleListVO>> searchArticle(int page,String key) {
        String url = AppConst.BASE_URL + "article/query/" + page + "/json";
        return OkGo.<ResponseData<ArticleListVO>>post(url)
                .params("k",key)
                .converter(new JsonConvert<ResponseData<ArticleListVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<ArticleListVO>>());
    }

    /**
     * 登录
     * @param username username
     * @param password password
     * @POST("/user/login")
     */
    public static Observable<ResponseData<UserBean>> login(String username, String password) {
        return OkGo.<ResponseData<UserBean>>post(loginUrl)
                .params("username", username)
                .params("password", password)
                .converter(new JsonConvert<ResponseData<UserBean>>() {
                })
                .adapt(new ObservableBody<ResponseData<UserBean>>());
    }

    /**
     * 注册
     *
     * @param username   username
     * @param password   password
     * @param repassword 确认密码
     * @POST("/user/register")
     */
    public static Observable<ResponseData<UserBean>> regist(String username, String password) {
        return OkGo.<ResponseData<UserBean>>post(registUrl)
                .params("username", username)
                .params("password", password)
                .params("repassword", password)
                .converter(new JsonConvert<ResponseData<UserBean>>() {
                })
                .adapt(new ObservableBody<ResponseData<UserBean>>());
    }

    /**
     * 获取自己收藏的文章列表
     *
     * @param page page
     * @GET("/lg/collect/list/{page}/json")
     */
    public static Observable<ResponseData<ArticleListVO>> getCollectData(int page) {
        String url = AppConst.BASE_URL + "lg/collect/list/" + page + "/json";
        return OkGo.<ResponseData<ArticleListVO>>
                get(url)
                .converter(new JsonConvert<ResponseData<ArticleListVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<ArticleListVO>>());
    }

    /**
     * 收藏文章
     *
     * @param id id
     * @POST("/lg/collect/{id}/json")
     */
    public static Observable<ResponseData<String>> collectArticle(int id) {
        String url = AppConst.BASE_URL + "lg/collect/" + id + "/json";
        return OkGo.<ResponseData<String>>
                post(url)
                .converter(new JsonConvert<ResponseData<String>>() {
                })
                .adapt(new ObservableBody<ResponseData<String>>());
    }

    /**
     * 取消收藏文章
     *
     * @param id id
     *  POST("/lg/uncollect/{id}/json")
     *  http://www.wanandroid.com/lg/uncollect/99/json
     *  POST 参数：originId:1165，没有则写-1
     *           (注意获取首页接口的时候接口没有返回originId字段，获取我的收藏接口的时候有返回originId字段，
     *            收藏接口的id 跟 首页接口的id 是不一样的，收藏接口的originId 跟首页接口的id 一样，
     *            这个接口有点神奇啊，url也不一样)
     */
    public static Observable<ResponseData<String>> unCollectArticle(int id,int originId, boolean hasOriginId) {
        String url;
        if (hasOriginId){
            url = AppConst.BASE_URL + "lg/uncollect/" + id + "/json";
        }else{
            url = AppConst.BASE_URL + "lg/uncollect_originId/" + id + "/json";
        }

        if (originId == 0){
            originId = -1;
        }
        return OkGo.<ResponseData<String>>
                post(url)
                .params("originId",originId)
                .converter(new JsonConvert<ResponseData<String>>() {
                })
                .adapt(new ObservableBody<ResponseData<String>>());
    }
}
