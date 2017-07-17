package com.eri.wechat2.task;

import android.content.Context;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.eri.wechat2.ui.dialog.LoadingDialog;
import com.eri.wechat2.utils.DebugLog;

@SuppressWarnings("unused")
public abstract class AsyncLoadingTask<Params, Progress, Result> {

    private static final int MESSAGE_IN_BACKGROUND = 0;
    private static final int MESSAGE_POST_EXECUTE = 1;
    private static final int MESSAGE_PROGRESS = 2;
    private  LoadingDialog.Builder loadBuilder;

    private HandlerThread mHandlerThread;
    private TaskHandler mWorkThreadHandler;
    private TaskHandler mUIThreadHandler;
    private Params[] mParams;
    private DialogDisplayRunnable mDialogDisplayRunnable;
    private boolean isDialogEnable;
    private String msg;
    protected long showLoadingDelay = 0;
    private LoadingDialog dialog;
    private Context mContext;

    public AsyncLoadingTask(Context mContext) {
        this(mContext, true);
    }

    public AsyncLoadingTask(Context mContext, boolean isDialogEnable) {
        this.isDialogEnable = isDialogEnable;
        this.mContext = mContext;
        mHandlerThread = new HandlerThread("AsyncLoadingTask", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        mWorkThreadHandler = new TaskHandler(mHandlerThread.getLooper());
        mUIThreadHandler = new TaskHandler(Looper.getMainLooper());
        if (isDialogEnable) {
           loadBuilder = new LoadingDialog.Builder(mContext)
                    .setMessage("loading...")
                    .setCancelable(false)
                    .setCancelOutside(false);
            dialog = loadBuilder.create();
        }
    }
    protected void onPreExecute(LoadingDialog dialog) {

    }

    protected void onPreExecute() {
    }


    protected abstract Result doInBackground(Params... params);


    @SuppressWarnings("All")
    protected void onProgressUpdate(Progress... progress) {
        if (null == progress || progress.length == 0) {
            return;
        }
//        if (progress[0] instanceof Integer && isDialogEnable) {
//            dialog.setMessage(msg + String.format("(%s%)", progress[0]));
//        }
    }


    @SuppressWarnings("All")
    protected final void publishProgress(Progress... values) {
        mUIThreadHandler.obtainMessage(MESSAGE_PROGRESS, values).sendToTarget();
    }


    protected void onPostExecute(Result result) {
    }

    public final boolean isCancelled() {
        return mHandlerThread.isInterrupted();
    }


    public final void setMessage(String msg) {
        this.msg = msg;
        loadBuilder.setMessage(msg);
        dialog = loadBuilder.create();
    }

    public final void setDialogCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }

    public final void cancel(boolean mayInterruptIfRunning) {
        if (null != dialog) {
            try {
                mUIThreadHandler.removeCallbacks(mDialogDisplayRunnable);
                dialog.dismiss();
            } catch (Throwable e) {
                e.printStackTrace();
                DebugLog.e("Exception when dismiss dialog:" + e.getMessage());
            }
        }
        if (!mHandlerThread.isInterrupted()) {
            try {
                mHandlerThread.quit();
                mHandlerThread.interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onCancelled();
    }

    protected void onCancelled() {
    }

    @SuppressWarnings("All")
    public void execute(Params... params) {
        mParams = params;
        if (isDialogEnable) {
            onPreExecute(dialog);
        } else {
            onPreExecute();
        }
        // show dialog after 500 ms
        mDialogDisplayRunnable = new DialogDisplayRunnable();
        mUIThreadHandler.postDelayed(mDialogDisplayRunnable, showLoadingDelay);
        mWorkThreadHandler.sendEmptyMessage(MESSAGE_IN_BACKGROUND);
    }

    private class TaskHandler extends Handler {

        public TaskHandler(Looper looper) {
            super(looper);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_IN_BACKGROUND:
                    Result result = doInBackground(mParams);
                    mUIThreadHandler.obtainMessage(MESSAGE_POST_EXECUTE, result).sendToTarget();
                    break;
                case MESSAGE_POST_EXECUTE:
                    // remove callback and dismiss dialog
                    mUIThreadHandler.removeCallbacks(mDialogDisplayRunnable);
                    if (isDialogEnable) {
                        dialog.dismiss();
                    }
                    onPostExecute((Result) msg.obj);
                    mHandlerThread.quit();
                    break;
                case MESSAGE_PROGRESS:
                    onProgressUpdate((Progress[]) msg.obj);
                    break;
            }
        }
    }

    private class DialogDisplayRunnable implements Runnable {
        @Override
        public void run() {
            if (null != dialog && isDialogEnable) {
                dialog.show();
            }
        }
    }
}
