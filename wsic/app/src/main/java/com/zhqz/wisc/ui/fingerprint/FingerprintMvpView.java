package com.zhqz.wisc.ui.fingerprint;

import com.zhqz.wisc.ui.base.MvpView;

public interface FingerprintMvpView extends MvpView {


    void showMessage(int msg, int id);

    void sendTemplateDatas(boolean isSuccess);

    void sendZhiwenDatas(String str);

    void fingerDaKaXinXi(String userImg, int dao, String name, boolean isQingjia);

    void fingerErrorMessage(Throwable e);

    void fingerDakaOK();
}
