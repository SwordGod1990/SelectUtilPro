package com.example.selectutilpro;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class PopupView {
    public PopupWindow mPopupWindow;

    /**
     * 显示popupwin
     *
     * @param context 上下文
     */
    public void showWindow(Context context, List<String> list,
                           HashMap<String, List<String>> cityMap,
                           HashMap<String, List<String>> provinceMap) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_layout, null);
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setContentView(view);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x9a0d0d0d));
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        View outView = view.findViewById(R.id.popup_out_view);
        outView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        //设置标题

        //点击取消
        view.findViewById(R.id.popup_select_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        final WheelView<String> wheelView = (WheelView<String>) view.findViewById(R.id.dialog_out_time_date);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.holoBorderColor = Color.parseColor("#D9D9D9");
        style.selectedTextColor = Color.BLACK;
        wheelView.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
        wheelView.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelView.setWheelData(list);  // 数据集合
        wheelView.setStyle(style);


        WheelView<String> mCityView = (WheelView<String>) view.findViewById(R.id.dialog_out_time_hour);
        mCityView.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
        mCityView.setSkin(WheelView.Skin.Holo); // common皮肤
        mCityView.setWheelData(provinceMap.get(list.get(wheelView.getSelection())));  // 数据集合
        mCityView.setStyle(style);
        wheelView.join(mCityView);
        wheelView.joinDatas(provinceMap);


        WheelView<String> mDistrictView = (WheelView<String>) view.findViewById(R.id.dialog_out_time_min);
        mDistrictView.setStyle(style);
        mDistrictView.setWheelAdapter(new ArrayWheelAdapter(context)); // 文本数据源
        mDistrictView.setSkin(WheelView.Skin.Holo); // common皮肤
        mDistrictView.setWheelData(cityMap.get(provinceMap.get(list.get(
                wheelView.getSelection())).get(mCityView.getSelection())));  // 数据集合
        mCityView.join(mDistrictView);
        mCityView.joinDatas(cityMap);


        //确定之后更改筛选的文字
        TextView yesText = (TextView) view.findViewById(R.id.popup_select_yes);
        yesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("选中的文字", "onClick: " + wheelView.getSelectionItem());
                mPopupWindow.dismiss();

            }
        });
    }

}
