package org.example.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;
    private String title;
    private BigDecimal cost;

}
