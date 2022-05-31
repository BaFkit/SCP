package org.example.dto;

import lombok.*;

import java.util.List;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private List<ProductDTO> productDTOs;
}
