package com.example.exammov2.model;

public class Libro {

    private int idlibro;
    private String titulo;
    private String autor;
    private int paginas;
    private String ediciom;
    private int ideditorial;
    private String nombre;

    public Libro(int idlibro, String titulo, String autor, int paginas, String ediciom, int ideditorial, String nombre) {
        this.idlibro = idlibro;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.ediciom = ediciom;
        this.ideditorial = ideditorial;
        this.nombre = nombre;
    }

    public Libro() {
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEdiciom() {
        return ediciom;
    }

    public void setEdiciom(String ediciom) {
        this.ediciom = ediciom;
    }

    public int getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(int ideditorial) {
        this.ideditorial = ideditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
