package com.example.seet.listapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Данные для отображения
    String[] countries_dict = {"Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
    ArrayList<String> countries = new ArrayList();
    ArrayAdapter<String> adapter;
    ArrayList<String> selectedCountries = new ArrayList();
    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0; i < countries_dict.length; i++) {
            Log.d("country: ", countries_dict[i]);
            countries.add(countries_dict[i]);
        }

        countriesList = (ListView) findViewById(R.id.countriesList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, countries);
        countriesList.setAdapter(adapter);

        //Обаботчик выбора элемента из списка
        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Получаем нажатый элемент
                String country = adapter.getItem(position);
                Log.d("selectedCountry", country + " " + countriesList.isItemChecked(position));
                if (!selectedCountries.contains(country)) {
                    selectedCountries.add(country);
                } else {
                    selectedCountries.remove(country);
                }
                Log.d("selectedCountries", selectedCountries.toString());
            }
        });
    }

    public void add(View view) {
        //Добавление элементов
        EditText countryEditText = (EditText) findViewById(R.id.country);
        String country = countryEditText.getText().toString();
        if (!country.isEmpty() && countries.contains(country) == false) {
            adapter.add(country);
            countryEditText.setText("");
            adapter.notifyDataSetChanged();
        }
    }

    public void remove(View view) {
        //Получаем и удаляем элементы
        for (int i=0; i < selectedCountries.size(); i++) {
            adapter.remove(selectedCountries.get(i));
        }
        //Снимаем ранее выбранные отметки
        countriesList.clearChoices();
        //очищаем массив выбрпанных элементов
        selectedCountries.clear();
        adapter.notifyDataSetChanged();
    }
}
