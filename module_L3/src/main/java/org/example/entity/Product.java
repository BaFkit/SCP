package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Product {

    private final int id;
    private final String title;
    private final float cost;

}
