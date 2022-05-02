package org.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "product_title")
    private String product_title;

    @Column(name = "order_cost")
    private BigDecimal order_cost;


    public Order(Long customer_id, String customer_name, Long product_id, String product_title, BigDecimal order_cost) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.product_id = product_id;
        this.product_title = product_title;
        this.order_cost = order_cost;
    }
}
