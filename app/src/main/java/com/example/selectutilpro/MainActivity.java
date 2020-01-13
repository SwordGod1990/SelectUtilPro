package com.example.selectutilpro;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.selectutilpro.city_data.CityModel;
import com.example.selectutilpro.city_data.DistrictModel;
import com.example.selectutilpro.city_data.ProvinceModel;
import com.example.selectutilpro.city_data.XmlParserHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProvinceDatas();
        Button wheelBtn = findViewById(R.id.wheel_btn);
        Button cityRecyclerBtn = findViewById(R.id.city_recycler_btn);
        Button loadBtn = findViewById(R.id.city_load_btn);
        wheelBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
        cityRecyclerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wheel_btn:
                PopupView popupView = new PopupView();
                popupView.showWindow(this, mList, mCityMap, mProvinceMap);
                break;
            case R.id.city_recycler_btn:
                startActivityForResult(new Intent(this, CityRecyclerActivity.class), 11);
                break;

            case R.id.city_load_btn:
                startActivity(new Intent(this, LoadActivity.class));

                break;
        }
    }

    private HashMap<String, List<String>> mProvinceMap;
    private HashMap<String, List<String>> mCityMap;
    private List<String> mList;

    /**
     * 解析城市
     */
    public void initProvinceDatas() {
        mProvinceMap = new HashMap<>();
        mCityMap = new HashMap<>();
        mList = new ArrayList<>();
        List<ProvinceModel> provinceList = null;
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
                String provinceName = provinceList.get(i).getName();
                mList.add(provinceName);
                List<String> cityList = new ArrayList<>();
                for (int j = 0; j < cityModels.size(); j++) {
                    // 遍历省下面的所有市的数据
                    String name = cityModels.get(j).getName();
                    cityList.add(name);
                    List<String> districtyList = new ArrayList<>();
                    List<DistrictModel> districtList = cityModels.get(j).getDistrictList();
                    for (int k = 0; k < districtList.size() - 1; k++) {
                        // 遍历市下面所有区/县的数据
                        districtyList.add(districtList.get(k).getName());
                    }
                    //关联市级和县级的数据
                    mCityMap.put(name, districtyList);
                }
                //关联省级和市级数据
                mProvinceMap.put(provinceName, cityList);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Log.e("----", "onActivityResult: ");
        }
    }
}
