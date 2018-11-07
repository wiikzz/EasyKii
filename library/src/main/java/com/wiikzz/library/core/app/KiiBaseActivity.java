package com.wiikzz.library.core.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.wiikzz.library.core.utils.ThreadPoolUtils;

import java.lang.ref.SoftReference;

/**
 * Created by wiikii on 2018/8/20.
 * Copyright (C) 2018. wiikii. All rights reserved.
 */
public class KiiBaseActivity extends FragmentActivity {
    protected final String TAG = getClass().getName();
    protected KiiHandler mHandler = new KiiHandler(this);

    protected ThreadPoolUtils mThreadPoolUtils;

    private Toast mToast;

    // 创建Handler
    private static class KiiHandler extends Handler {
        private final SoftReference<KiiBaseActivity> mInstance;

        KiiHandler(KiiBaseActivity baseActivity) {
            mInstance = new SoftReference<>(baseActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            KiiBaseActivity entityInstance = mInstance.get();
            if (entityInstance != null) {
                entityInstance.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeAllCallbacksAndMessages();
    }

    // 处理Handler发来的Message
    protected void handleMessage(Message message) {
    }

    // 发送Message
    public void sendMessage(int what) {
        sendMessage(what, null);
    }

    public void sendMessage(int what, long delayTime) {
        sendMessage(what, null, delayTime);
    }

    public void sendMessage(int what, Bundle bundle) {
        sendMessage(what, bundle, 0L);
    }

    public void sendMessage(int what, Bundle bundle, long delayTime) {
        Message message = new Message();
        message.what = what;
        message.setData(bundle);

        if (delayTime > 0L) {
            mHandler.sendMessageDelayed(message, delayTime);
        } else {
            mHandler.sendMessage(message);
        }
    }

    // 取消Message
    public void removeMessages(int what) {
        mHandler.removeMessages(what);
    }

    // 发送Runnable
    public void postRunnable(Runnable runnable) {
        postRunnable(runnable, 0L);
    }

    public void postRunnable(Runnable runnable, long delayTime) {
        if (runnable != null) {
            if (delayTime > 0L) {
                mHandler.postDelayed(runnable, delayTime);
            } else {
                mHandler.post(runnable);
            }
        }
    }

    // 取消Runnable
    public void removeCallbacks(Runnable runnable) {
        mHandler.removeCallbacks(runnable);
    }

    public void removeAllCallbacksAndMessages() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    // 线程执行
    public void executeRunnableInThread(Runnable runnable) {
        if (mThreadPoolUtils == null) {
            mThreadPoolUtils = ThreadPoolUtils.instance();
        }

        mThreadPoolUtils.execute(runnable);
    }

    public void showToast(String toastMessage) {
        showToast(toastMessage, 0L);
    }

    public void showToast(final String toastMessage, long delayTime) {
        if (TextUtils.isEmpty(toastMessage)) {
            return;
        }

        if (delayTime > 0L) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(),
                                toastMessage, Toast.LENGTH_SHORT);
                        mToast.show();
                    } else {
                        mToast.setText(toastMessage);
                        mToast.show();
                    }
                }
            }, delayTime);
        } else {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(),
                        toastMessage, Toast.LENGTH_SHORT);
                mToast.show();
            } else {
                mToast.setText(toastMessage);
                mToast.show();
            }
        }
    }
}
