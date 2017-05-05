package com.example.cao156.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by cao15 on 2016/12/22.
 */

public class AddCity extends AppCompatActivity {
    private String[] citys=new String[]{
      "南昌","北京","上海","天津","重庆","南京","武汉","杭州","长沙","广州","深圳","成都"
    };
    private String[] descs=new String[]{
      "江西","北京","上海","天津","重庆","江苏","湖北","浙江","湖南","广东","广东","四川"
    };
    private ListView cityList;
    private List<Map<String,Object>> listItems;
    private Button back;
    private EditText query;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
       cityList=(ListView)findViewById(R.id.city_names);
        back=(Button)findViewById(R.id.back_querycity);
        //query=(EditText) findViewById(R.id.query_city);
       // query.addTextChangedListener(watcher);
       listItems=new ArrayList<Map<String, Object>>();
        for (int i=0;i<citys.length;i++){
            Map<String,Object> listItem= new HashMap<String, Object>();
            listItem.put("city",citys[i]);
            listItem.put("parentCity",descs[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,
                R.layout.simple_item,
                new String[]{"city","parentCity"},
                new int[]{R.id.city,R.id.parentCity});
        cityList.setAdapter(simpleAdapter);
       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long id) {
                String cityName=listItems.get(position).toString();
                cityName=cityName.substring(cityName.length()-3,cityName.length()-1);
                //Toast.makeText(AddCity.this,cityName,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddCity.this,CityManagement.class);
                intent.putExtra("weather_id",cityName);
                startActivity(intent);
                finish();
            }
        });
    }
    public void Choose(View view){
        Intent intent=new Intent(AddCity.this,ChooseCity.class);
        startActivity(intent);
    }
    /*private TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
           Toast.makeText(AddCity.this,editable,Toast.LENGTH_SHORT).show();
            QueryCity(editable);
            //listItems.clear();

        }
    };

    private void QueryCity(Editable editable) {

    }*/


}
