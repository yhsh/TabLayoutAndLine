package kotlin.yhsh.cn.androidandjs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * @author DELL
 */
public class MainActivity extends Activity {

    private TextView tvShowData;
    private WebView wvLoadHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShowData = findViewById(R.id.tv_show_data);
        wvLoadHtml = findViewById(R.id.wv_load_html);
        WebSettings settings = wvLoadHtml.getSettings();
        settings.setJavaScriptEnabled(true);
        //方法一：androidJs这个方法必须和Js里面的方法保持一致
        wvLoadHtml.addJavascriptInterface(new OpenWebView(), "androidJs");
        //方法二：
//        wvLoadHtml.addJavascriptInterface(new GetWebViewData() {
//            @Override
//            @JavascriptInterface
//            public void getJsData(String data) {
//                getFinallyData(data);
//            }
//        }, "androidJs");

        //加载HTML
        wvLoadHtml.loadUrl("file:///android_asset/loadAndroid.html");
    }

    /**
     * Js的回调结果方法
     */
    class OpenWebView {
        /**
         * getJsData这个方法必须和Js里面的方法保持一致
         *
         * @param data Js返回的数据
         */
        @JavascriptInterface
        public void getJsData(final String data) {
            getFinallyData(data);
        }
    }

    /**
     * getJsData这个方法必须和Js里面的方法保持一致
     */
    interface GetWebViewData {
        /**
         * Js返回的数据
         *
         * @param data 数据
         */
        void getJsData(String data);
    }


    private void getFinallyData(final String data) {
        Log.e("打印拿到的数据", data);
        //当前是子线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvShowData.setText(data);
            }
        });
    }
}
