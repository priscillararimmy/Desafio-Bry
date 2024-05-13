package backend.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name, @NotBlank String cpf, String image) {
}