package kotlin.yhsh.cn.tablayoutandline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author DELL
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout tbMonetary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tbMonetary = findViewById(R.id.tb_monetary);
        tbMonetary.addTab(tbMonetary.newTab().setText("近七日年化"));
        tbMonetary.addTab(tbMonetary.newTab().setText("万份收益"));
        //默认七日收益
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_monetary_replace, SevenDayMoneyFragment.newInstance()).commit();
        initListener();
    }

    private void initListener() {
        tbMonetary.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (tab.getPosition() == 0) {
                    //七日年化
                    transaction.replace(R.id.fl_monetary_replace, SevenDayMoneyFragment.newInstance()).commit();
                } else if (tab.getPosition() == 1) {
                    //万份收益
                    transaction.replace(R.id.fl_monetary_replace, OneYearMoneyFragment.newInstance()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
