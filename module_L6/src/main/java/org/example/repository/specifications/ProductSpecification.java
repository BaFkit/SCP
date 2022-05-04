package org.example.repository.specifications;

import org.example.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> greatOrEquals(BigDecimal min) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("cost"), min);
    }

    public static Specification<Product> lessOrEquals(BigDecimal max) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("cost"), max);
    }
}
