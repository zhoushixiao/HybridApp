package com.suning.hybrid.js;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.suning.hybrid.R;
import com.suning.hybrid.tools.JSONTools;
import com.suning.hybrid.tools.KLog;

import java.util.Date;

/**
 * Created by 16060822 on 2016/7/13.
 * js调用本地方法window.hybrid.+ 方法名
 */
public class JsInteration {

    public JsInteration(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    private Context context;
    private WebView webView;

    /**
     * showToast:弹出提示
     * @param msg
     */
    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 模拟登陆
     * @return
     */
    @JavascriptInterface
    public void getLocalData()
    {
        webView.post(new Runnable() {
            @Override
            public void run() {
                String json= JSONTools.readJSON(context, R.raw.datajson);
                webView.loadUrl("javascript:$.webapp.Api.setStructureValue('"+json+"')");
            }
        });
    }

    /**
     * openDialog:打开对话框
     */
    @JavascriptInterface
    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("点击确认：Native调用JS");
        builder.setTitle("我是Native，So easy!");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:$.webapp.Api.showAlert()");
                    }
                });
            }
        });
        builder.create().show();
    }
}
