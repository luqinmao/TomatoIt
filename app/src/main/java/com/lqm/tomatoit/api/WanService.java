package com.lqm.tomatoit.api;

import com.lqm.tomatoit.app.AppConst;
import com.lqm.tomatoit.helper.JsonConvert;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.ArticleBean;
import com.lqm.tomatoit.model.pojo.BannerBean;
import com.lqm.tomatoit.model.pojo.HotKeyBean;
import com.lqm.tomatoit.model.pojo.UserBean;
import com.lqm.tomatoit.model.pojoVO.HomeVO;
import com.lqm.tomatoit.model.pojoVO.TypeTagVO;
import com.lqm.tomatoit.model.pojoVO.TypeVO;
import com.lzy.okgo.OkGo;
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
    private static String getClollectData = AppConst.BASE_URL + "lg/collect/list/0/json";

    /**
     * 首页Banner
     *
     * @GET("/banner/json")
     */
    public static Observable<ResponseData<List<BannerBean>>> getBannerData() {
        return OkGo.<ResponseData<List<BannerBean>>>get(homeBannerData)
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
    public static Observable<ResponseData<HomeVO>> getHomeData(int page) {
        String url = AppConst.BASE_URL + "article/list/" + page + "/json";
        return OkGo.<ResponseData<HomeVO>>get(url)
                .converter(new JsonConvert<ResponseData<HomeVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<HomeVO>>());
    }

    /**
     * 知识体系 (类别tag)
     * http://www.wanandroid.com/tree/json
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
     * @param page page
     * @param cid cid
     * @GET("/article/list/{page}/json")
     */
    public static Observable<ResponseData<TypeVO>> getTypeDataById(int page, int cid) {
        String url = AppConst.BASE_URL + "article/list/" + page + "/json";
        return OkGo.<ResponseData<TypeVO>>get(url)
                .params("cid",cid)
                .converter(new JsonConvert<ResponseData<TypeVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<TypeVO>>());
    }


    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     * @GET("/friend/json")
     */

    /**
     * 大家都在搜
     * http://www.wanandroid.com/hotkey/json
     * @GET("/hotkey/json")
     */
    public static Observable<ResponseData<List<HotKeyBean>>> getHotKey() {
        return OkGo.<ResponseData<List<HotKeyBean>>>get(hotKeyUrl)
                .converter(new JsonConvert<ResponseData<List<HotKeyBean>>>() {})
                .adapt(new ObservableBody<ResponseData<List<HotKeyBean>>>());
    }

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page page
     * @param k  POST search key
     * @POST("/article/query/{page}/json")
     */

    public static Observable<ResponseData<List<HotKeyBean>>> getSearchData(int page,String key) {
        String url = AppConst.BASE_URL +"article/query/"+page+"/json";
        return OkGo.<ResponseData<List<HotKeyBean>>>post(url)
                .params("k",key)
                .converter(new JsonConvert<ResponseData<List<HotKeyBean>>>() {})
                .adapt(new ObservableBody<ResponseData<List<HotKeyBean>>>());
    }


    /**
     * 登录
     * @param username username
     * @param password password
     * @POST("/user/login")
     */
    public static Observable<ResponseData<UserBean>> login(String username, String password) {
        return OkGo.<ResponseData<UserBean>>post(loginUrl)
                .params("username",username)
                .params("password",password)
                .converter(new JsonConvert<ResponseData<UserBean>>() {})
                .adapt(new ObservableBody<ResponseData<UserBean>>());
    }

    /**
     * 注册
     * @param username username
     * @param password password
     * @param repassword 确认密码
     * @POST("/user/register")
     */
    public static Observable<ResponseData<UserBean>> regist(String username, String password) {
        return OkGo.<ResponseData<UserBean>>post(loginUrl)
                .params("username",username)
                .params("password",password)
                .params("repassword",password)
                .converter(new JsonConvert<ResponseData<UserBean>>() {})
                .adapt(new ObservableBody<ResponseData<UserBean>>());
    }

    /**
     * 获取自己收藏的文章列表
     * @param page page
     * @GET("/lg/collect/list/{page}/json")
     */
    public static Observable<ResponseData<List<ArticleBean>>> getCollectData(int page) {
        String url = AppConst.BASE_URL +"lg/collect/list/"+page+"/json";
        return OkGo.<ResponseData<List<ArticleBean>>>
                get(url)
                .converter(new JsonConvert<ResponseData<List<ArticleBean>>>() {})
                .adapt(new ObservableBody<ResponseData<List<ArticleBean>>>());
    }

    /**
     * 收藏文章
     * @param id id
     * @POST("/lg/collect/{id}/json")
     */
    public static Observable<ResponseData<String>> collectArticle(int id) {
        String url = AppConst.BASE_URL +"lg/collect/"+id+"/json";
        return OkGo.<ResponseData<String>>
                post(url)
                .converter(new JsonConvert<ResponseData<String>>() {})
                .adapt(new ObservableBody<ResponseData<String>>());
    }


    /**
     * 收藏站外文章
     * @param title title
     * @param author author
     * @param link link
     * @POST("/lg/collect/add/json")
     */


    /**
     * 删除收藏文章
     * @param id id
     * @param originId -1
     * POST("/lg/uncollect/{id}/json")
     */
    public static Observable<ResponseData<String>> unCollectArticle(int id) {
        String url = AppConst.BASE_URL +"lg/uncollect/"+id+"/json";
        return OkGo.<ResponseData<String>>
                post(url)
                .params("originId",-1)
                .converter(new JsonConvert<ResponseData<String>>() {})
                .adapt(new ObservableBody<ResponseData<String>>());
    }

    /**
     * 我的常用网址
     * @GET("/lg/collect/usertools/json")
     */

}
