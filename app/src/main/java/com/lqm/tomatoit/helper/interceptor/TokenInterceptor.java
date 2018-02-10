package com.lqm.tomatoit.helper.interceptor;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * user：lqm
 * desc：token验证令牌失效拦截器，token过期时刷新token或弹dialog跳转到登录界面
 *  src:https://www.jianshu.com/p/62ab11ddacc8
 */

public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // try the request
        Response originalResponse = chain.proceed(request);

        /**通过如下的办法曲线取到请求完成的数据
         *
         * 原本想通过  originalResponse.body().string()
         * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
         *
         * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
         */
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        Log.d("body---------->", bodyString);

        /***************************************/

        JSONObject extrasJson = null;
        try {
            if (extrasJson == null){
                extrasJson = new JSONObject(bodyString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int code = Integer.parseInt(extrasJson.optString("errorCode")); //根据后台返回数据执行修改

//        if (response shows expired token){//根据和服务端的约定判断token过期
        if (code == 401){  //假设服务端返回码401为token过期

            // TODO 弹出全局的dialog或者用以下代码刷新token (全局dialog可以用WindowManager相应实现)

            //取出本地的refreshToken
            String refreshToken = "sssgr122222222";
            // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//            ApiService service = ServiceManager.getService(ApiService.class);
//            Call<String> call = service.refreshToken(refreshToken);
            //要用retrofit的同步方式
//            String newToken = call.execute().body();
            String newToken = "sssssssss  ne wtoken";

            // create a new request and modify it accordingly using the new token
            Request newRequest = request.newBuilder().header("token", newToken)
                    .build();

            // retry the request

            originalResponse.body().close();
            return chain.proceed(newRequest);
        }

        // otherwise just pass the original response on
        return originalResponse;
    }

}
