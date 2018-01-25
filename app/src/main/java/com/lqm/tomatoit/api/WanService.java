package com.lqm.tomatoit.api;

import com.lqm.tomatoit.app.AppConst;
import com.lqm.tomatoit.helper.JsonConvert;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojoVO.HomeVO;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;

/**
 * user：lqm
 * desc：玩Android提供的api接口
 * www.wanandroid.com
 */

public class WanService {

    private static String homeDataList = AppConst.BASE_URL + "/article/list/{page}/json";

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page
     * @GET("/article/list/{page}/json")
     */
    public static Observable<ResponseData<HomeVO>> getHomeData(int page) {
        String url = AppConst.BASE_URL + "/article/list/" + page + "/json";
        return OkGo.<ResponseData<HomeVO>>get(url)
                .converter(new JsonConvert<ResponseData<HomeVO>>() {
                })
                .adapt(new ObservableBody<ResponseData<HomeVO>>());
    }

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     * @GET("/tree/json")
     */

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page page
     * @param cid cid
     * @GET("/article/list/{page}/json")
     */

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

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page page
     * @param k POST search key
     * @POST("/article/query/{page}/json")
     */


    /**
     * 登录
     * @param username username
     * @param password password
     * @POST("/user/login")
     */

    /**
     * 注册
     * @param username username
     * @param password password
     * @param repassword repassword
     * @POST("/user/register")
     */

    /**
     * 获取自己收藏的文章列表
     * @param page page
     * @GET("/lg/collect/list/{page}/json")
     */


    /**
     * 收藏文章
     * @param id id
     * @POST("/lg/collect/{id}/json")
     */


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

    /**
     * 首页Banner
     * @GET("/banner/json")
     */

    /**
     * 我的常用网址
     * @GET("/lg/collect/usertools/json")
     */

}
