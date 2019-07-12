package kotlin.yhsh.cn.tablayoutandline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2019/6/12 17:37
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 空间名称：group-wallet-android
 */
public class SevenDayMoneyFragment extends Fragment {

    private View sevenDayView;
    //x轴数据集合
    List<String> xValues;
    //y轴数据集合
    List<Integer> yValues;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sevenDayView = View.inflate(getContext(), R.layout.fragment_seven_day_money, null);
        initViewID();
        return sevenDayView;
    }

    private void initViewID() {
        RadioButton rbMoneySevenDay = sevenDayView.findViewById(R.id.rb_money_seven_day);
        //默认选中7天
        rbMoneySevenDay.setChecked(true);
        MyLineChartView mlvFinancingLine = sevenDayView.findViewById(R.id.mlv_financing_line);
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Random random = new Random();
            xValues.add(i + "号");
            yValues.add(random.nextInt(1000));
        }
        // xy轴集合自己添加数据
        mlvFinancingLine.setXValues(xValues);
        mlvFinancingLine.setYValues(yValues);
    }

    public static Fragment newInstance() {
        return new SevenDayMoneyFragment();
    }
}
