package com.example.selectutilpro;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.selectutilpro.city_data.CityModel;
import com.example.selectutilpro.city_data.ProvinceModel;
import com.example.selectutilpro.city_data.XmlParserHandler;
import com.example.selectutilpro.city_recycler.CityAdapter;
import com.example.selectutilpro.city_recycler.NavigationView;
import com.example.selectutilpro.city_recycler.PinYinComparator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2018/1/26.
 */

public class CityRecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mLetterText;
    private CityAdapter mCityAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCity();
        setContentView(R.layout.activity_city_recycler);
        mLetterText = findViewById(R.id.city_recycler_letter_text);
        NavigationView navigationView = findViewById(R.id.city_recycler_navigation_view);
        mRecyclerView = findViewById(R.id.city_recycler_recycler);
        final LinearLayoutManager layout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layout);
        Collections.sort(mCityList, new PinYinComparator());
        mCityAdapter = new CityAdapter(mCityList, this);
        mRecyclerView.setAdapter(mCityAdapter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                letterScroll();
            }
        });
        TextView dialogText = findViewById(R.id.city_recycler_dialog);
        navigationView.setDialog(dialogText);
        navigationView.setOnTouchItemListener(new NavigationView.OnTouchItemListener() {
            @Override
            public void onTouch(String var1) {
                int position = mCityAdapter.getPositionForSelection(var1.charAt(0));
                layout.scrollToPositionWithOffset(position, 0);
            }
        });

    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 监听滑动，使头字母停留
     */
    private void letterScroll() {
        View letterView = mRecyclerView.findChildViewUnder(mLetterText.getMeasuredWidth() / 2, 3);
        if (letterView != null && letterView.getContentDescription() != null) {
            mLetterText.setText(String.valueOf(letterView.getContentDescription()));
        }

        View recyclerItem = mRecyclerView.findChildViewUnder(mLetterText.getMeasuredWidth() / 2,
                mLetterText.getMeasuredHeight() + 1);

        if (recyclerItem != null && recyclerItem.getTag() != null) {
            int deltaY = recyclerItem.getTop() - mLetterText.getMeasuredHeight();

            if ((int) recyclerItem.getTag() == mCityAdapter.HAS_STICKY_VIEW) {
                if (recyclerItem.getTop() > 0) {
                    mLetterText.setTranslationY(deltaY);
                } else {
                    mLetterText.setTranslationY(0);
                }
            } else if ((int) recyclerItem.getTag() == mCityAdapter.NONE_STICKY_VIEW) {
                mLetterText.setTranslationY(0);
            }
        }
    }


    private List<PinYinComparator.CityBean> mCityList;

    private void getCity() {
        List<ProvinceModel> provinceList = null;
        mCityList = new ArrayList<>();
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                List<CityModel> cityModels = provinceList.get(i).getCityList();
                for (int j = 0; j < cityModels.size(); j++) {
                    // 遍历省下面的所有市的数据
                    String name = cityModels.get(j).getName();
                    String pinYin = PinYinComparator.getPinYin(name);
                    if (pinYin.length() > 0) {
                        String sortLetter = pinYin.substring(0, 1).toUpperCase();
                        PinYinComparator.CityBean cityBean = new PinYinComparator.CityBean();
                        cityBean.setName(name);
                        cityBean.setSortLetter(sortLetter);
                        mCityList.add(cityBean);
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
}
