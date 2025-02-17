package com.matheusbloize.dio_java_spring_design_patterns.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductDto(@NotBlank String name, @NotBlank @Min(value = 1) BigDecimal price, @NotBlank @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime expirationDate) {

}
