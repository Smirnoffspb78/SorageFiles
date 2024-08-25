package com.smirnov.greenatom.service;

import com.smirnov.greenatom.dto.FileUserDTO;
import com.smirnov.greenatom.entity.FileUser;
import com.smirnov.greenatom.exception.EntityNotFoundException;
import com.smirnov.greenatom.repository.FileUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.smirnov.greenatom.AppTest.TEST_ID;
import static com.smirnov.greenatom.AppTest.getFileUser;
import static com.smirnov.greenatom.AppTest.getFileUserDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FileUserServiceTest {

    private static final String SORT_FIELD = "creationDate";
    /**
     * Репозиторий файла.
     */
    @Mock
    FileUserRepository fileUserRepository;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    FileUserService fileUserService;

    @Test
    void addFile() {
        FileUserDTO fileUserDTO = getFileUserDTO();
        FileUser fileUser = getFileUser();

        doReturn(fileUser).when(modelMapper).map(fileUserDTO, FileUser.class);
        doReturn(fileUser).when(fileUserRepository).save(fileUser);

        assertEquals(fileUser.getId(), fileUserService.addFile(fileUserDTO));
    }

    @Test
    void getFile_EntityNotFoundException() {
        doReturn(Optional.empty()).when(fileUserRepository).findById(TEST_ID);
        assertThrows(EntityNotFoundException.class, () -> fileUserService.getFile(TEST_ID));
    }

    @Test
    void getFile_valid() {
        FileUser fileUser = getFileUser();
        FileUserDTO fileUserDTO = getFileUserDTO();

        doReturn(Optional.of(fileUser)).when(fileUserRepository).findById(TEST_ID);
        doReturn(fileUserDTO).when(modelMapper).map(fileUser, FileUserDTO.class);

        assertEquals(fileUserDTO, fileUserService.getFile(TEST_ID));
    }

    @Test
    void getPageFiles_valid() {
        FileUser fileUser = getFileUser();
        FileUserDTO fileUserDTO = getFileUserDTO();

        doReturn(new PageImpl<>(List.of(fileUser))).when(fileUserRepository)
                .findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, FileUser.Fields.creationDate)));
        doReturn(fileUserDTO).when(modelMapper).map(fileUser, FileUserDTO.class);
        assertEquals(fileUserService.getPageFiles(PageRequest.of(0, 1,
                Sort.by(Sort.Direction.ASC, FileUser.Fields.creationDate))).get().toList().get(0), fileUserDTO);
    }
}