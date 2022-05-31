package org.example.components;

import lombok.Data;
import org.example.dto.ProductDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Scope("singleton")
public class Cart {

    private BigDecimal total;
    private List<ProductDTO> productDTOList;

    public Cart() {
        total = new BigDecimal("0.0");
        productDTOList = new ArrayList<>();
    }

    public void add(ProductDTO productDTO) {
        productDTOList.add(productDTO);
        total = total.add(productDTO.getCost());
    }

    public void remove(ProductDTO productDTO) {
        productDTOList.remove(productDTO);
        total = total.subtract(productDTO.getCost());
    }
}
