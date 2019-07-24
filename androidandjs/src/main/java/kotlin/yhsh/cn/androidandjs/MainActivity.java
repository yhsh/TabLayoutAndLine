package kotlin.yhsh.cn.androidandjs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author DELL
 * 2019年7月23日19:34:25
 */
public class MainActivity extends Activity {

    private TextView tvShowData;
    private WebView wvLoadHtml;
    private EditText etInputToJs;
    private Button btClickInputToJs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShowData = findViewById(R.id.tv_show_data);
        wvLoadHtml = findViewById(R.id.wv_load_html);
        etInputToJs = findViewById(R.id.et_input_to_js);
        btClickInputToJs = findViewById(R.id.bt_click_input_to_js);
        btClickInputToJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputToJs = etInputToJs.getText().toString().trim();
                //if(window.writeInputText)判断Js里面的writeInputText是否存在，存在就传递inputToJs这个值到Js里面
                wvLoadHtml.loadUrl("javascript:if(window.writeInputText){window.writeInputText('" + inputToJs + "')}");
            }
        });
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
