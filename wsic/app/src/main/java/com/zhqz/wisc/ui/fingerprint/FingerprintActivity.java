package com.zhqz.wisc.ui.fingerprint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhqz.wisc.R;
import com.zhqz.wisc.WiscApplication;
import com.zhqz.wisc.data.DbDao.FingerUsersDao;
import com.zhqz.wisc.data.WiscClient;
import com.zhqz.wisc.data.model.FingerUsers;
import com.zhqz.wisc.ui.FingerprintEntry.FingerprintEntryActivity;
import com.zhqz.wisc.ui.base.BaseActivity;
import com.zhqz.wisc.ui.fingerprint.fingerprintUtils.FingerUtil;
import com.zhqz.wisc.ui.main.MainActivity;
import com.zhqz.wisc.utils.DateUtil;
import com.zhqz.wisc.utils.ELog;
import com.zhqz.wisc.utils.PromptDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class FingerprintActivity extends BaseActivity implements FingerprintMvpView {

    @Inject
    FingerprintPresenter fingerprintPresenter;

    @BindView(R.id.m_txtStatus)
    TextView m_txtStatus;
    @BindView(R.id.m_FpImageViewer)
    ImageView m_FpImageViewer;


    @BindView(R.id.fingerprint_title)
    TextView fingerprint_title;

    @BindView(R.id.user_name)
    TextView user_name;

    @BindView(R.id.fingerprint_timeBack)
    TextView fingerprint_timeBack;
    @BindView(R.id.fingerprint_qiandao_LL)
    LinearLayout fingerprint_qiandao_LL;
    @BindView(R.id.fingerprint_lvru_LL)
    LinearLayout fingerprint_lvru_LL;

    private int stId;
    private int psType;
    private Scheduler.Worker fingerprintworker = Schedulers.io().createWorker();
    private Scheduler.Worker fingerEnrollworker = Schedulers.io().createWorker();
    private int timeBack;
    private FingerUtil fingerUtil;
    private PromptDialog kaoqinFingerDialog;
    private String imgUrl = null;

    Handler fingerprintHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case WiscClient.FINGER_NAME_HANDLER:
                    if (msg.obj != null) {
                        user_name.setText(msg.obj.toString());
                    }
                    if (msg.arg1 == PromptDialog.DAO) {
                        kaoqinFingerDialog = new PromptDialog(FingerprintActivity.this, PromptDialog.DAO);
                    } else if (msg.arg1 == PromptDialog.CHIDAO) {
                        kaoqinFingerDialog = new PromptDialog(FingerprintActivity.this, PromptDialog.CHIDAO);
                    } else if (msg.arg1 == PromptDialog.CHONGFU) {
                        kaoqinFingerDialog = new PromptDialog(FingerprintActivity.this, PromptDialog.CHONGFU);
                    } else if (msg.arg1 == PromptDialog.CUOWU) {
                        kaoqinFingerDialog = new PromptDialog(FingerprintActivity.this, PromptDialog.CUOWU);
                    }
                    kaoqinFingerDialog.show();
                    if (imgUrl != null) {
                        kaoqinFingerDialog.setImage(imgUrl);
                    }
                    if (!fingerprintworker.isDisposed()) {
                        fingerprintworker.dispose();
                    }
                    setFingerprintBackTimer();
                    break;
                case WiscClient.FINGER_HANDLER:
                    m_txtStatus.setText(msg.obj.toString());
                    break;
                case WiscClient.FINGER_EMPTY_NAME_HANDLER:
                    user_name.setText("");
                    m_txtStatus.setText(msg.obj.toString());
                    if (kaoqinFingerDialog != null && kaoqinFingerDialog.isShowing()) {
                        kaoqinFingerDialog.dismiss();
                    }
                    if (!fingerprintworker.isDisposed()) {
                        fingerprintworker.dispose();
                    }
                    setFingerprintBackTimer();
                    break;
                case WiscClient.FINGER_TIMEBACK_HANDLER:
                    fingerprint_timeBack.setVisibility(View.VISIBLE);
                    fingerprint_timeBack.setText(timeBack + " 秒后无操作自动退出");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        fingerprintPresenter.attachView(this);

        fingerUtil = new FingerUtil(this, fingerprintPresenter);


        if (WiscClient.isEnter == true) {
            fingerprint_title.setText("指纹录入");
            stId = getIntent().getIntExtra("stId", 0);
            psType = getIntent().getIntExtra("psType", 0);
            fingerprint_qiandao_LL.setVisibility(View.GONE);
            fingerprint_lvru_LL.setVisibility(View.GONE);
            fingerprint_timeBack.setVisibility(View.GONE);
            startLuruFinger();
        } else {
            fingerprint_title.setText("指纹识别");
            fingerprint_qiandao_LL.setVisibility(View.VISIBLE);
            fingerprint_lvru_LL.setVisibility(View.VISIBLE);
            fingerprint_timeBack.setVisibility(View.GONE);
            if (WiscClient.isDaKaQianDao) {
                if (DateUtil.compare_date(System.currentTimeMillis(), WiscApplication.prefs.getCourseStartTime()) <= 15 * 1000 * 60) {
                    if (WiscApplication.getDaoSession().getFingerUsersDao().loadAll().size() != 0) {
                        startIdentifyFinger();
                    } else {
                        m_txtStatus.setText("无指纹数据，请选择其他操作");
                        setFingerprintBackTimer();
                    }
                } else {
                    m_txtStatus.setText("请在上课前15分钟内考勤");
                    setFingerprintBackTimer();
                }
            } else {
                m_txtStatus.setText("现在不需要考勤，请选择其他操作");
                setFingerprintBackTimer();
            }
        }

    }

    private void startLuruFinger() {
        fingerUtil.Run_CmdDeleteAll();
        if (fingerEnrollworker.isDisposed()) {
            fingerEnrollworker = Schedulers.io().createWorker();
        }
        fingerEnrollworker.schedule(new Runnable() {
            @Override
            public void run() {
                fingerEnrollworker.dispose();
                fingerUtil.Run_CmdEnroll(stId);
            }
        }, 1000 * 3, TimeUnit.MILLISECONDS);

    }

    private void startIdentifyFinger() {
        fingerUtil.Run_CmdIdentifyFree();
    }


    private void setFingerprintBackTimer() {
        if (fingerprintworker.isDisposed()) {
            fingerprintworker = Schedulers.io().createWorker();
        }
        timeBack = 30;
        fingerprintworker.schedulePeriodically(new Runnable() {
            @Override
            public void run() {
                if (timeBack < 1) {
                    fingerprintworker.dispose();
                    startActivity(new Intent(FingerprintActivity.this, MainActivity.class));
                    finish();
                }
                timeBack--;
                fingerprintHander.sendEmptyMessage(WiscClient.FINGER_TIMEBACK_HANDLER);
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);

    }


    @OnClick(R.id.fingerprint_back_LL)
    void fingerprint_back_LL() {
        if (!fingerprintworker.isDisposed()) {
            fingerprintworker.dispose();
        }
        fingerUtil.Run_CmdCancel();
        if (WiscClient.isEnter == true) {
            startActivity(new Intent(FingerprintActivity.this, FingerprintEntryActivity.class));
        } else {
            startActivity(new Intent(FingerprintActivity.this, MainActivity.class));
        }
        finish();
    }


    @OnClick(R.id.fingerprint_lvru_LL)
    void fingerprint_lvru_LL() {
        if (!fingerprintworker.isDisposed()) {
            fingerprintworker.dispose();
        }
        fingerUtil.Run_CmdCancel();
        WiscApplication.prefs.setCardNumber(null);
        startActivity(new Intent(FingerprintActivity.this, FingerprintEntryActivity.class));
        finish();
    }

    @Override
    public void sendZhiwenDatas(String str) {
        fingerprintPresenter.sendTemplateData(psType, stId, str);
    }

    @Override
    public void sendTemplateDatas(boolean isSuccess) {
        if (!isSuccess) {
            Toast.makeText(this, "服务有问题或重新录入", Toast.LENGTH_SHORT).show();
        }
        if (!fingerprintworker.isDisposed()) {
            fingerprintworker.dispose();
        }
        fingerUtil.Run_CmdCancel();
        startActivity(new Intent(FingerprintActivity.this, FingerprintEntryActivity.class));
        finish();
    }

    @Override
    public void showMessage(int type, int fingerId) {
        Message msg = new Message();
        switch (type) {
            case 1:
                msg.obj = "录入成功";
                msg.what = WiscClient.FINGER_HANDLER;
                fingerUtil.Run_CmdReadTemplate(stId);
                break;
            case -1:
                msg.obj = "失败,请重新录入";
                msg.what = WiscClient.FINGER_HANDLER;
                break;
            case 2:
                if (fingerId != 0) {
                    msg.obj = "识别指纹成功";
                    msg.what = WiscClient.FINGER_HANDLER;
                    fingerprintPresenter.daka(fingerId);
                } else {
                    msg.obj = "失败,请重新识别或今天不需要在此教室上课";
                    msg.what = WiscClient.FINGER_EMPTY_NAME_HANDLER;
                }
                break;
            case 3:
                msg.obj = "请松开你的手指";
                msg.what = WiscClient.FINGER_HANDLER;
                break;
            case 4:
                msg.obj = "请按下你的手指";
                msg.what = WiscClient.FINGER_HANDLER;
                break;
            case 5:
                msg.obj = "请再次按下你的手指";
                msg.what = WiscClient.FINGER_HANDLER;
                break;
        }
        fingerprintHander.sendMessage(msg);

    }

    @Override
    protected void onDestroy() {
        fingerprintPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void fingerDaKaXinXi(String userImg, int integer, String name, boolean isQingjia) {
        if (kaoqinFingerDialog != null && kaoqinFingerDialog.isShowing()) {
            kaoqinFingerDialog.dismiss();
        }
        imgUrl = userImg;
        Message msg = new Message();
        msg.obj = name;
        msg.arg1 = integer;
        msg.what = WiscClient.FINGER_NAME_HANDLER;
        fingerprintHander.sendMessage(msg);
    }

    @Override
    public void fingerDakaOK() {

    }

    @Override
    public void fingerErrorMessage(Throwable e) {
        boolean isSocketException = e.getClass().equals(SocketTimeoutException.class);
        boolean isConnectException = e.getClass().equals(ConnectException.class);
        boolean isHttpException = e.getClass().equals(HttpException.class);
        if (isSocketException || isConnectException || isHttpException) {
            Toast.makeText(FingerprintActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FingerprintActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
