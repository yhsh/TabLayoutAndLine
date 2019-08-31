package kotlin.yhsh.cn.okhttprequest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author DELL
 */
public class MainActivity extends Activity {
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpClient = new OkHttpClient();
    }

    public void getMethod(View view) {
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        int maxNumPoolSize = corePoolSize + 1;
        long keepAliveTime = 1;
        TimeUnit unit = TimeUnit.HOURS;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maxNumPoolSize,
                keepAliveTime,
                unit, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();
                try {
                    final Response response = okHttpClient.newCall(request).execute();
                    Log.e("打印数据：", response.body().string() + "=");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void postMethodFrom(View view) {

    }

    public void postMethodJson(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), "{\"data\":{\"employNo\":\"RC-28700\",\"memberNo\":\"200100000186888\"},\"merchant_id\":\"100000000000147\",\"sign\":\"123456\",\"sign_type\":\"RSA\"}");
                Request request = new Request.Builder()
                        .url("http://10.168.17.91:9019/rongfu/api/licai/getToken")
                        .post(body)
                        .addHeader("X-Token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNpkyj0OwyAMQOG7eA4SdgBDbtC9F6BgJPobBTpEVe9eMnd8n94Hrr3CAuxYktNakfZJmcisvBRSiUqOpsxZpwATtPdlzGgZUQe0RMNqa_8WOyxo3WwwIPsJVtnqKx-fs14TDwzkxnmT_XS4McaO7LI96jPez_sqQwm-PwAAAP__.2alLnnrFgXJAFseNr6t-35Zd8apK7NaJzQON7uu_-5Ugr5anmnZZy9E9yZfz8gsHqrbes1zUgDZzirt-zvJxTA")
                        .build();
                try {
                    final Response response = okHttpClient.newCall(request).execute();
                    Log.e("打印数据", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void uploadFile(View view) {
    }
}
