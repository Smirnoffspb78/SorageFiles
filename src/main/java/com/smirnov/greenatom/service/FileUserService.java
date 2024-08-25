package com.smirnov.greenatom.service;

import com.smirnov.greenatom.dto.FileUserDTO;
import com.smirnov.greenatom.entity.FileUser;
import com.smirnov.greenatom.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.smirnov.greenatom.repository.FileUserRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервисный слой для работы с файлами.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FileUserService {
    /**
     * Репозиторий файлов.
     */
    private final FileUserRepository fileUserRepository;

    /**
     * Маппер объектов.
     */
    private final ModelMapper modelMapper;

    /**
     * Добавляет файл в базу данных.
     *
     * @param fileUserDTO DTO файла
     * @return идентификатор добавленного файла
     */
    public Integer addFile(FileUserDTO fileUserDTO) {
        return fileUserRepository.save(modelMapper.map(fileUserDTO, FileUser.class))
                .getId();
    }

    /**
     * Возвращает файл из базы данных по его идентификатору.
     *
     * @param id идентификатор файла
     * @return DTO файла
     */
    public FileUserDTO getFile(Integer id) {
        return modelMapper.map(fileUserRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException(FileUser.class, id)), FileUserDTO.class);
    }

    /**
     * Возвращает частично список записей.
     *
     * @return Список записей.
     */
    public Page<FileUserDTO> getPageFiles(Pageable pageable) {
        return fileUserRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.by(Sort.Direction.ASC, FileUser.Fields.creationDate)))
                .map(fileUser -> modelMapper.map(fileUser, FileUserDTO.class));
    }
}
