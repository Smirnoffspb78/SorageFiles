package com.smirnov.greenatom.controller;

import com.smirnov.greenatom.dto.FileUserDTO;
import com.smirnov.greenatom.service.FileUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с файлами.
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
@Validated
public class FileUserController {

    /**
     * Сервисный слой для работы с файлами.
     */
    private final FileUserService fileUserService;

    /**
     * Возвращает файл по идентификатору.
     *
     * @param id Идентификатор записи
     * @return DTO файла.
     */
    @GetMapping("/{id}")
    public FileUserDTO getFile(@PathVariable("id") Integer id) {
        log.info("GET: /file/{}", id);
        FileUserDTO fileUserDTO = fileUserService.getFile(id);
        log.info("{}. Получен файл из базы данных с id {}", HttpStatus.OK, id);
        return fileUserDTO;
    }

    /**
     * Возвращает страницу записей.
     *
     * @return Список DTO записей
     */
    @GetMapping
    public Page<FileUserDTO> getPageFiles(@RequestParam("page") int page,
                                          @RequestParam("size") int size, Pageable pageable) {
        log.info("GET: /file?page={}&size={}", page, size);
        Page<FileUserDTO> pageFiles = fileUserService.getPageFiles(pageable);
        log.info("{}. Получена страница {} размером {} элементов", HttpStatus.OK, pageable.getPageNumber(), pageable.getPageSize());
        return pageFiles;
    }

    /**
     * Добавляет файл в базу данных.
     *
     * @param fileUserDTO DTO файла
     * @return id Идентификатор добавленного файла
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addFile(@RequestBody @Valid FileUserDTO fileUserDTO) {
        log.info("POST: /file");
        Integer id = fileUserService.addFile(fileUserDTO);
        log.info("{}. Файлу с именем {} присвоен id {}. Файл добавлен на сервер", HttpStatus.CREATED, fileUserDTO.getTitle(), id);
        return id;
    }
}
