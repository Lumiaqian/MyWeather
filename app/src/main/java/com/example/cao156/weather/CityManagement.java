package com.example.cao156.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by cao15 on 2016/12/20.
 */

public class CityManagement extends AppCompatActivity {
    private static final List<String> citylist = new ArrayList<>();
    //private static final List<String> shortCity=new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private String weatherId;
    private Button backCity;
    private SwipeMenuListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citym);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CityManagement.this, AddCity.class);
                startActivity(intent1);
                //finish();
            }
        });

        backCity = (Button) findViewById(R.id.back_city);
        if (citylist.size() > 0) {
            citylist.clear();
        }
        if (prefs.getString("CityName", null) != null) {
            weatherId = prefs.getString("CityName", null);
            char[] citys = weatherId.toCharArray();
            String a = "";
            for (int i = 0; i < citys.length; i++) {
                if (citys[i] != ',') {
                    a += citys[i];

                } else {
                    //Toast.makeText(CityManagement.this,a,Toast.LENGTH_SHORT).show();
                    citylist.add(a);
                    a = "";
                }
            }

        }

        if (citylist.size() <= 0) {
            weatherId = getIntent().getStringExtra("weather_id");
            citylist.add(weatherId);
        }
        /*for (int i=0;i<citylist.size();i++){
            shortCity.add(citylist.get(i).substring(0,1));
        }*/
        mAdapter = new ArrayAdapter<String>(CityManagement.this, android.R.layout.simple_list_item_1, citylist);
        listView = (SwipeMenuListView) findViewById(R.id.ManageMent);
        initCreator();
        listView.setAdapter(mAdapter);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long id) {
                Intent intent = new Intent(CityManagement.this, WeatherActivity.class);
                intent.putExtra("weather_id", citylist.get(position));
                startActivity(intent);
                finish();
            }
        });
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        Intent intent = new Intent(CityManagement.this, WeatherActivity.class);
                        intent.putExtra("weather_id", citylist.get(position));
                        startActivity(intent);
                        finish();
                        break;
                    case 1:

                       /* String cities=prefs.getString("CityName", null);
                        List<String> hotCityes=new ArrayList<String>();
                        char[] citys = cities.toCharArray();
                        String a = "";
                        for (int i = 0; i < citys.length; i++) {
                            if (citys[i] != ',') {
                                a += citys[i];

                            } else {
                                //Toast.makeText(CityManagement.this,a,Toast.LENGTH_SHORT).show();
                                //citylist.add(a);
                                hotCityes.add(a);
                                a = "";
                            }
                        }
                        for (int j=0;j<hotCityes.size();j++){

                        }*/
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(CityManagement.this).edit();
                        editor.remove("CityName");
                        citylist.remove(position);
                        String[] hotcity = new String[citylist.size()];
                        StringBuilder sb = new StringBuilder();
                        citylist.toArray(hotcity);
                        for (int i = 0; i < hotcity.length; i++) {
                            sb.append(hotcity[i] + ",");
                        }
                        editor.putString("CityName",sb.toString());
                        editor.apply();
                        mAdapter.notifyDataSetChanged();
                        break;

                }
                return true;
            }
        });
        backCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(CityManagement.this, WeatherActivity.class);
                intent.putExtra("weather_id", weatherId);
                startActivity(intent);*/
                finish();
            }
        });
    }

    private void initCreator() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setWidth(dp2px(70));
                openItem.setTitle("打开");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(70));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        weatherId = getIntent().getStringExtra("weather_id");
        for (int i = 0; i < citylist.size(); i++) {
            if (weatherId.equals(citylist.get(i)))
                weatherId = "";
        }
        if (weatherId != "") {
            citylist.add(weatherId);
            //shortCity.add(weatherId.substring(0,1));
        }
        mAdapter = new ArrayAdapter<String>(CityManagement.this, android.R.layout.simple_list_item_1, citylist);
        listView = (SwipeMenuListView) findViewById(R.id.ManageMent);
        initCreator();
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        listView.setAdapter(mAdapter);
        //SharedPreferences.Editor editor=getSharedPreferences("hotcity",);
        String[] hotcity = new String[citylist.size()];
        StringBuilder sb = new StringBuilder();
        citylist.toArray(hotcity);
        for (int i = 0; i < hotcity.length; i++) {
            sb.append(hotcity[i] + ",");
        }

        Toast.makeText(CityManagement.this, sb.toString(), Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(CityManagement.this).edit();
        editor.putString("CityName", sb.toString());
        editor.apply();
        //editor.clear();
    }
}
