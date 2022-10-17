package com.example.exammov2.service;

public class apis {
    public static final String URL_001="http://172.17.1.1:3000/libro/";
    public static final String URL_002="http://172.17.1.1:3000/categoria/";

    public static com.example.exammov2.service.LibroService getLibroService(){
        return  Cliente.getClient(URL_001).create(com.example.exammov2.service.LibroService.class);
    }
}
