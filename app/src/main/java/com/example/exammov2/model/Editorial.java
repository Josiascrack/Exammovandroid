package com.example.exammov2.model;

public class Editorial {

    private int ideditorial;
    private String nombre;

    public Editorial(int ideditorial, String nombre) {
        this.ideditorial = ideditorial;
        this.nombre = nombre;
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
