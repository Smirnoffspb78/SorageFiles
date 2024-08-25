package com.smirnov.greenatom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public final class FileUserDTO {
    private @NotBlank(message = "title не должно быть null и не должно быть пустым")
    String title;
    private @PastOrPresent(message = "creationDate не должен быть в будущем")
    LocalDateTime creationDate;
    private @NotBlank(message = "Описание файла не должно быть null и не должно быть пустым")
    String description;
    private @NotNull @Pattern(regexp = "[A-z0-9:;,/+=]+", message = "файл должен содержать символы base64 или :;,/+=")
    String baseFile;
}
