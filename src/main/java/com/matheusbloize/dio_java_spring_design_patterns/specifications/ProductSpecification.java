package com.matheusbloize.dio_java_spring_design_patterns.specifications;

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
}
