package com.matheusbloize.dio_java_spring_design_patterns.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.matheusbloize.dio_java_spring_design_patterns.models.Product;

public class ProductSpecification {
    public static Specification<Product> sortByCreationDate(String sort) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(sort)) {
                return null;
            }
            if (sort.equalsIgnoreCase("asc")) {
                return builder.conjunction();
            }
            query.orderBy(builder.desc(root.get("creationDate")));
            return builder.conjunction();
        };
    }

    public static Specification<Product> priceLessThanOrEqualTo(BigDecimal priceLte) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(priceLte)) {
                return null;
            }
            return builder.lessThanOrEqualTo(root.get("price"), priceLte);
        };
    }

    public static Specification<Product> priceGreaterThanOrEqualTo(BigDecimal priceGte) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(priceGte)) {
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("price"), priceGte);
        };
    }
}
