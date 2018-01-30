package com.lqm.tomatoit.ui.presenter;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.model.ResponseData;
import com.lqm.tomatoit.model.pojo.UserBean;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.LoginRegistView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：登录注册
 */

public class LoginRegistPresenter extends BasePresenter<LoginRegistView>{

    //登录
    public void toLogin(String username ,String password) {

        WanService.login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().showProgress("正在登陆...");
                    }

                    @Override
                    public void onNext(ResponseData<UserBean> responseData) {
                        if (responseData.getErrorCode() == 0){
                            getView().loginSuccess(responseData.getData());
                        }else{
                            getView().loginFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().loginFail();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideProgress();
                    }
                });
    }

    //注册
    public void toRegist(String username ,String password){
        WanService.regist(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().showProgress("正在注册...");
                    }

                    @Override
                    public void onNext(ResponseData<UserBean> responseData) {
                        if (responseData.getErrorCode() == 0){
                            getView().registerSuccess(responseData.getData());
                        }else{
                            getView().registerFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().registerFail();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideProgress();
                    }
                });
    }
}
