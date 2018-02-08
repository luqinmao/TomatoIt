package com.lqm.tomatoit.ui.presenter;

import com.lqm.tomatoit.api.WanService;
import com.lqm.tomatoit.helper.rxjavahelper.RxObserver;
import com.lqm.tomatoit.helper.rxjavahelper.RxResultHelper;
import com.lqm.tomatoit.helper.rxjavahelper.RxSchedulersHelper;
import com.lqm.tomatoit.model.pojo.UserBean;
import com.lqm.tomatoit.ui.base.BasePresenter;
import com.lqm.tomatoit.ui.view.LoginRegistView;

import io.reactivex.disposables.Disposable;

/**
 * user：lqm
 * desc：登录注册
 */

public class LoginRegistPresenter extends BasePresenter<LoginRegistView> {

    //登录
    public void toLogin(String username, String password) {

        WanService.login(username, password)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<UserBean>() {

                    @Override
                    public void _onSubscribe(Disposable d) {
                        getView().showProgress("正在登陆...");
                    }

                    @Override
                    public void _onNext(UserBean userBean) {
                        getView().loginSuccess(userBean);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().loginFail();

                    }

                    @Override
                    public void _onComplete() {
                        getView().hideProgress();
                    }

                });
    }

    //注册
    public void toRegist(String username, String password) {
        WanService.regist(username, password)
            .compose(RxSchedulersHelper.io_main())
            .compose(RxResultHelper.handleResult())
            .subscribe(new RxObserver<UserBean>() {
                @Override
                public void _onSubscribe(Disposable d) {
                    getView().showProgress("正在注册...");
                }

                @Override
                public void _onNext(UserBean userBean) {
                    getView().registerSuccess(userBean);
                }

                @Override
                public void _onError(String errorMessage) {
                    getView().registerFail();
                }

                @Override
                public void _onComplete() {
                    getView().hideProgress();
                }
            });
    }
}
