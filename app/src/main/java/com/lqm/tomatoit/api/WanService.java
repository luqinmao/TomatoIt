package com.lqm.tomatoit.api;

import com.lqm.tomatoit.app.AppConst;
import com.lqm.tomatoit.helper.JsonConvert;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.BannerBean;
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
     * 我的常用网址
     * @GET("/lg/collect/usertools/json")
     */

}
