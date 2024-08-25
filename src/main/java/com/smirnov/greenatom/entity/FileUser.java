package com.smirnov.greenatom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * Файл в формате base64.
 */
@Entity
@Table(name = "files")
@Getter
@Setter
@FieldNameConstants
public class FileUser {
    /**
     * Идентификатор файла.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Наименование файла
     */
    @Column(name = "title")
    @NotBlank(message = "title не должно быть null и не должно быть пустым")
    @Pattern(regexp = "[A-zА-я0-9~@#$%^-_()'`+=:;«,.|\\s]{1,250}",
            message = "Наименования файла должно соответствовать  [A-zА-я0-9~@#$%^-_()'`+=:;«,.|\\s]{1,250}")
    private String title;

    /**
     * Дата и время отправки документа на сервер.
     */
    @Column(name = "creation_date")
    @PastOrPresent(message = "creationDate не должен быть в будущем")
    private LocalDateTime creationDate;

    /**
     * Описание файла.
     */
    @Column(name = "description")
    @NotBlank(message = "Описание файла не должно быть null и не должно быть пустым")
    @Pattern(regexp = ".{1,250}", message = "Количество символов должно быть от 1 до 250")
    private String description;

    /**
     * Файл в формате base64.
     */
    @Column(name = "file")
    @NotNull
    @Pattern(regexp = "[A-z0-9:;,/+=]+", message = "файл должен содержать символы base64 или :;,/+=")
    private String baseFile;
}
