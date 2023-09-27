package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class ListaBandas extends AppCompatActivity {
    SearchView searchView_;
    ListView listView;

    ArrayList<Bandas> arrayList = new ArrayList<>();
    ArrayList<Bandas> arrayListCopia;
    ArrayList<String> estiloArray;
    ArrayAdapter<Bandas> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bandas);

        Intent intent = getIntent();
        estiloArray = (ArrayList<String>) intent.getSerializableExtra("esse");

        searchView_ = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView1);

        searchView_.setIconified(false);

        arrayList.add(new Bandas("Metallica", "Rock"));
        arrayList.add(new Bandas("Sepultura", "Metal"));
        arrayList.add(new Bandas("Slayer", "Thrash metal"));
        arrayList.add(new Bandas("Black Sabbath", "Roque"));
        arrayList.add(new Bandas("Beatles", "Rock classico"));
        arrayList.add(new Bandas("Led Zeppelin", "Pop Rock"));
        arrayList.add(new Bandas("Sandy e Junior", "lo-fi"));

        arrayListCopia = new ArrayList<>(arrayList);

        arrayAdapter = new ArrayAdapter<Bandas>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                arrayList
        );
        listView.setAdapter(arrayAdapter);

        //filtrando a lista conforme estilo informado na tela 1.
        fazerBuscaTela1();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        searchView_.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //forma 1
                //MainActivity3.this.arrayAdapter.getFilter().filter(s);

                //forma 2
                fazerBusca(s);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void fazerBusca(String s) {

        arrayList.clear();

        s = s.toLowerCase();

        for(Bandas item: arrayListCopia){
            if(item.toString().toLowerCase().contains(s)){
                arrayList.add(item);
            }
        }
    }

    private void fazerBuscaTela1() {

        arrayList.clear();

        for(String estilo: estiloArray){
            for(Bandas item: arrayListCopia){
                if(item.getEstilo().contains(estilo)){
                    arrayList.add(item);
                }
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }
}