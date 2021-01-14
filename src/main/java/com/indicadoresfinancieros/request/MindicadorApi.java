package com.indicadoresfinancieros.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MindicadorApi {

    private String codigo;
    private ArrayList<Serie> serie;
}
