package org.example.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private float cost;

}
