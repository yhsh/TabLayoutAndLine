package kotlin.yhsh.cn.tablayoutandline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author 下一页5（轻飞扬）
 *         创建时间：2019/6/12 17:37
 *         个人小站：http://yhsh.wap.ai(已挂)
 *         最新小站：http://www.iyhsh.icoc.in
 *         空间名称：group-wallet-android
 */
public class OneYearMoneyFragment extends Fragment {

    private View oneYearView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        oneYearView = View.inflate(getContext(), R.layout.fragment_one_year_money, null);
        return oneYearView;
    }

    public static Fragment newInstance() {
        return new OneYearMoneyFragment();
    }
}
