package com.matheusbloize.dio_java_spring_design_patterns.specifications;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public static Specification<Product> userProducts(UUID userId) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(userId)) {
                return null;
            }
            return root.join("user").get("id").in(userId);
        };
    }

    public static Specification<Product> offerExpiringSoon(boolean offersExpiring) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(offersExpiring)) {
                return null;
            }
            if (!offersExpiring) {
                return builder.conjunction();
            }
            LocalDateTime date = LocalDateTime.now().plusDays(7);
            return builder.lessThan(root.get("offerExpirationDate"), date);
        };
    }
}
