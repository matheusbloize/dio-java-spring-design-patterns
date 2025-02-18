package com.matheusbloize.dio_java_spring_design_patterns.queryfilters;

import static com.matheusbloize.dio_java_spring_design_patterns.specifications.ProductSpecification.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.matheusbloize.dio_java_spring_design_patterns.models.Product;

public class ProductQueryFilter {
    private String sort;
    private UUID userId;
    private BigDecimal price;
    private LocalDateTime offerExpirationDate;

    public Specification<Product> toSpecification() {
        Optional<String> sortOpt = Optional.ofNullable(sort);
        if (!sortOpt.isPresent()) {
            setSort("asc");
        }
        return sortByCreationDate(sort);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getOfferExpirationDate() {
        return offerExpirationDate;
    }

    public void setOfferExpirationDate(LocalDateTime offerExpirationDate) {
        this.offerExpirationDate = offerExpirationDate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
