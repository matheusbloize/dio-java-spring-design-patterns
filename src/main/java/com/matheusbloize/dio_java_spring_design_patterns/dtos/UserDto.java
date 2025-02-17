package com.matheusbloize.dio_java_spring_design_patterns.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String name) {

}
