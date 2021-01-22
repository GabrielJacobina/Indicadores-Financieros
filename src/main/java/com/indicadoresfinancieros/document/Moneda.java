package com.indicadoresfinancieros.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Moneda {

    @Id
    private String date;
    private Indicators indicators;
}
