package com.matheusbloize.dio_java_spring_design_patterns.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(@NotBlank String name, @NotNull @Min(value = 1) BigDecimal price, @NotNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime expirationDate,
                @NotNull UUID userId) {

}
