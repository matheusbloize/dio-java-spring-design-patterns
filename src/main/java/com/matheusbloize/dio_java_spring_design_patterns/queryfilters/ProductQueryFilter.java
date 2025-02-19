package com.matheusbloize.dio_java_spring_design_patterns.queryfilters;

import static com.matheusbloize.dio_java_spring_design_patterns.specifications.ProductSpecification.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.matheusbloize.dio_java_spring_design_patterns.models.Product;

public class ProductQueryFilter {
    private String sort;
    private UUID userId;
    private BigDecimal priceGte;
    private BigDecimal priceLte;
    private boolean offersExpiring;

    public Specification<Product> toSpecification() {
        Optional<String> sortOpt = Optional.ofNullable(sort);
        if (!sortOpt.isPresent()) {
            setSort("asc");
        }
        return sortByCreationDate(sort)
                .and(priceGreaterThanOrEqualTo(priceGte))
                .and(priceLessThanOrEqualTo(priceLte))
                .and(userProducts(userId))
                .and(offersExpiringSoon(offersExpiring));
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public BigDecimal getPriceGte() {
        return priceGte;
    }

    public void setPriceGte(BigDecimal priceGte) {
        this.priceGte = priceGte;
    }

    public BigDecimal getPriceLte() {
        return priceLte;
    }

    public void setPriceLte(BigDecimal priceLte) {
        this.priceLte = priceLte;
    }

    public boolean isOffersExpiring() {
        return offersExpiring;
    }

    public void setOffersExpiring(boolean offersExpiring) {
        this.offersExpiring = offersExpiring;
    }

}
