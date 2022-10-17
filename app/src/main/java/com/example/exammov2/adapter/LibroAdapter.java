package com.example.exammov2.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exammov2.LibroActivity;
import com.example.exammov2.R;
import com.example.exammov2.model.Libro;

import java.util.List;

public class LibroAdapter  extends ArrayAdapter<Libro> {

        private Context context;

        private List<Libro> libros;
        public LibroAdapter(@NonNull Context context, int resource, @NonNull List<Libro> objects) {
            super(context, resource, objects);
            this.context=context;
            this.libros=objects;
        }

        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView=layoutInflater.inflate(R.layout.list_layout,parent,false);
            TextView txtlibro=(TextView)rowView.findViewById(R.id.Libro);
            TextView txtautor=(TextView)rowView.findViewById(R.id.Autor);
            TextView txtpaginas= (TextView)rowView.findViewById(R.id.Paginas);
            TextView txteditorial= (TextView)rowView.findViewById(R.id.Editorial);

            txtlibro.setText(String.format("Libro:%s",libros.get(position).getTitulo()));
            txtautor.setText(String.format("Autor:%s",libros.get(position).getAutor()));
            txtpaginas.setText(String.format("Paginas:%s",libros.get(position).getPaginas()));
            txteditorial.setText(String.format("Editorial:%s",libros.get(position).getNombre()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, LibroActivity.class);
                    intent.putExtra("idlibro",String.valueOf(libros.get(position).getIdlibro()));
                    intent.putExtra("Titulo",libros.get(position).getTitulo());
                    intent.putExtra("Autor",libros.get(position).getAutor());
                    intent.putExtra("Paginas",String.valueOf(libros.get(position).getPaginas()));
                    intent.putExtra("IdEditorial",String.valueOf(libros.get(position).getIdeditorial()));
                    intent.putExtra("Nombre",libros.get(position).getNombre());
                    context.startActivity(intent);
                }
            });
            return rowView;
        }

    }

