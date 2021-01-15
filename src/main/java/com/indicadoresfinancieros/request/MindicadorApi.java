package com.indicadoresfinancieros.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MindicadorApi {

    private String codigo;
    private ArrayList<Serie> serie;
}
