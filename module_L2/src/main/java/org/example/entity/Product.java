package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private final int id;
    private final String title;
    private final float cost;

}
