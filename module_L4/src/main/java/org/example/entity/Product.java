package org.example.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    private int id;
    private String title;
    private float cost;

}
