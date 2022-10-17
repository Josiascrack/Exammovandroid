package com.example.exammov2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exammov2.model.Editorial;
import com.example.exammov2.model.Libro;
import com.example.exammov2.service.LibroService;
import com.example.exammov2.service.apis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibroActivity extends AppCompatActivity {
        LibroService libroService;
        List<Editorial> listeditorial=new ArrayList<>();
        Spinner spinner;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_libro);


                EditText txtnombre = (EditText)findViewById(R.id.txtnombre);
                TextView nombre = (TextView) findViewById(R.id.nombre);
                EditText txtautor = (EditText)findViewById(R.id.txtAutorp);
                TextView autor = (TextView) findViewById(R.id.Autorp);
                EditText txtpaginas = (EditText)findViewById(R.id.txtPaginasp);
                TextView paginas = (TextView) findViewById(R.id.Paginasp);
                spinner=findViewById(R.id.spinner);



                Button btnSave=(Button)findViewById(R.id.btnSave);
                Button btnVolver=(Button)findViewById(R.id.btnVolver);
                Button btnEliminar=(Button)findViewById(R.id.btnEliminar);

                Bundle bundle=getIntent().getExtras();
                String ide = bundle.getString("idlibro");
                String nom= bundle.getString("Titulo");
                String aut = bundle.getString("Autor");
                String pag = bundle.getString("Paginas");
                String idedit = bundle.getString("IdEditorial");
                String nombreedit = bundle.getString("Nombre");



                txtnombre.setText(nom);
                txtautor.setText(aut);
                txtpaginas.setText(pag);
                listspinner();

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                                ((TextView) adapterView.getChildAt(0)).setTextSize(18);


                                String clave = adapterView.getSelectedItem().toString();
                                int id = Integer.parseInt(clave.split(" ")[0]);

                                btnSave.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                Libro p=new Libro();
                                                p.setTitulo(txtnombre.getText().toString());
                                                p.setAutor(txtautor.getText().toString());
                                                p.setPaginas(Integer.parseInt(txtpaginas.getText().toString()));
                                                p.setIdeditorial(id);
                                                if(ide.trim().length()==0||ide.equals("")){
                                                        addLibro(p);
                                                        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                                                        startActivity(intent);
                                                }else{
                                                        updateLibro(p,Integer.valueOf(ide));
                                                        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                                                        startActivity(intent);
                                                }

                                        }
                                });
                                Toast.makeText(getApplicationContext(), String.valueOf(id) , Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                });

                btnEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                deleteLibro(Integer.valueOf(ide));
                                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                                startActivity(intent);
                        }
                });
                btnVolver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                                startActivity(intent);
                        }
                });



        }
        public void deleteLibro(int id){
                libroService = apis.getLibroService();
                Call<Libro> call=libroService.deleteLibro(id);
                call.enqueue(new Callback<Libro>() {
                        @Override
                        public void onResponse(Call<Libro> call, Response<Libro> response) {
                                if(response.isSuccessful()){
                                        Toast.makeText(LibroActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                                }
                        }
                        @Override
                        public void onFailure(Call<Libro> call, Throwable t) {
                                Log.e("Error:",t.getMessage());
                        }
                });

        }
        public void updateLibro(Libro p,int id){
                libroService= apis.getLibroService();
                Call<Libro>call=libroService.updateLibro(p,id);
                call.enqueue(new Callback<Libro>() {
                        @Override
                        public void onResponse(Call<Libro> call, Response<Libro> response) {
                                if(response.isSuccessful()){
                                        Toast.makeText(LibroActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                                }
                        }
                        @Override
                        public void onFailure(Call<Libro> call, Throwable t) {
                                Log.e("Error:",t.getMessage());
                        }
                });
                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                startActivity(intent);
        }

        public void addLibro(Libro p){
                libroService= apis.getLibroService();
                Call<Libro> call=libroService.addLibro(p);
                call.enqueue(new Callback<Libro>() {
                        @Override
                        public void onResponse(Call<Libro> call, Response<Libro> response) {
                                if(response.isSuccessful()){
                                        Toast.makeText(LibroActivity.this,"Se agrego conéxito",Toast.LENGTH_LONG).show();
                                }
                        }
                        @Override
                        public void onFailure(Call<Libro> call, Throwable t) {
                                Log.e("Error:",t.getMessage());
                        }
                });
                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                startActivity(intent);
        }



        private void listspinner(){
                libroService= apis.getLibroService();
                Call<List<Editorial>> call=libroService.getEditorial();
                call.enqueue(new Callback<List<Editorial>>() {
                        @Override
                        public void onResponse(Call<List<Editorial>> call, Response<List<Editorial>> response) {
                                listeditorial = response.body();
                                String[] s =new String[listeditorial.size()];
                                for(int i=0;i<listeditorial.size();i++)
                                {

                                        s[i]= String.valueOf(listeditorial.get(i).getIdeditorial())+" "+listeditorial.get(i).getNombre();
                                        final ArrayAdapter a = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, s);
                                        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spinner.setAdapter(a);
                                }
                        }

                        @Override
                        public void onFailure(Call<List<Editorial>> call, Throwable t) {

                        }
                });

        }
        }
