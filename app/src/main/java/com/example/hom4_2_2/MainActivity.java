package com.example.hom4_2_2;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Генератор случайностей
    private Random random = new Random();
    // Наш адаптер
    private ItemsDataAdapter adapter;
    // Список картинок, которые мы будем брать для нашего списка
    private List<Drawable> images = new ArrayList<>();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_item);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        button = findViewById(R.id.btn);
        setSupportActionBar(toolbar);

        fillImages();

        // При тапе по кнопке добавим один новый элемент списка
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomItemData();
            }
        });

        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);

        // При долгом тапе по элементу списка будем удалять его
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.showItemData(position);
                return true;
            }
        });
    }

    // Заполним различными картинками, которые встроены в сам Android
    // ContextCompat обеспечит нам поддержку старых версий Android
    private void fillImages() {
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_report_image));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_add));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_agenda));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_camera));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_call));
    }

    // Создадим ну почти случайные данные для нашего списка.
    // random.nextInt(граница_последнего_элемента)
    // Для каждого элемента мы возьмем 1 случайную картинку
    // из 5, которые мы сделали вначале.
    private void generateRandomItemData() {

        adapter.addItem(new ItemData(
                images.get(random.nextInt(images.size())),
                "Hello" + adapter.getCount(),
                "It\'s me",
                button));
    }
}

