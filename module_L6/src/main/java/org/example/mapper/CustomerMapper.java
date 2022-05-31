package org.example.mapper;

import org.example.dto.CustomerDTO;
import org.example.dto.ProductDTO;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.example.mapper.ProductMapper.PRODUCT_MAPPER;

@Mapper
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    @Mapping(target = "productDTOs", expression = "java(productListToProductDtoList(customer.getProducts()))")
    CustomerDTO customerToCustomerDTO(Customer customer);

    default List<ProductDTO>  productListToProductDtoList(List<Product> products) {
        List<ProductDTO> list = new ArrayList<>();
        products.forEach(e -> list.add(PRODUCT_MAPPER.productToProductDTO(e)));
        return list;
    }

    List<CustomerDTO> customerListToCustomerDtoList(List<Customer> customers);
}
