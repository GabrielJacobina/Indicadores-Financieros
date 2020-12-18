package com.indicadoresfinancieros.document;

import java.util.ArrayList;


public class MindicadorApi {

    private String codigo;

    private ArrayList<Serie> serie;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Serie> getSerie() {
        return serie;
    }

    public void setSerie(ArrayList<Serie> serie) {
        this.serie = serie;
    }
}
