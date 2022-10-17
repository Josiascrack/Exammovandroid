package com.example.exammov2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.exammov2.adapter.LibroAdapter;
import com.example.exammov2.model.Libro;
import com.example.exammov2.service.LibroService;
import com.example.exammov2.service.apis;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    LibroService libroService;
    List<Libro> listLibro=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listView);


        listProducto();
        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LibroActivity.class);
                intent.putExtra("idlibro","");
                intent.putExtra("titulo","");
                intent.putExtra("Autor","");
                intent.putExtra("Paginas","");
                intent.putExtra("IdEditorial","");
                intent.putExtra("Editorial","");

                startActivity(intent);
            }
        });

    }
    public void listProducto(){
        libroService= apis.getLibroService();
        Call<List<Libro>> call=libroService.getlibrosconeditorial();
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(response.isSuccessful()) {
                    listLibro = response.body();
                    listView.setAdapter(new LibroAdapter(MainActivity.this,R.layout.list_layout,listLibro));

                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

}