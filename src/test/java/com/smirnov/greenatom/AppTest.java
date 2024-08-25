package com.smirnov.greenatom;

import com.smirnov.greenatom.dto.FileUserDTO;
import com.smirnov.greenatom.entity.FileUser;

import java.time.LocalDateTime;
import java.time.Month;

public final class AppTest {
    private AppTest() {
    }

    public static final Integer TEST_ID = 1;


    public static FileUser getFileUser() {
        FileUser fileUser = new FileUser();
        fileUser.setId(1);
        fileUser.setTitle("Имя");
        fileUser.setCreationDate(LocalDateTime.of(2024, Month.MAY, 5, 20, 20));
        fileUser.setDescription("Описание");
        fileUser.setBaseFile("QWERTY");
        return fileUser;
    }

    public static FileUserDTO getFileUserDTO() {
        FileUser fileUser = getFileUser();
        FileUserDTO fileUserDTO = new FileUserDTO();
        fileUserDTO.setTitle(fileUser.getTitle());
        fileUser.setCreationDate(fileUser.getCreationDate());
        fileUser.setDescription(fileUser.getDescription());
        fileUser.setBaseFile(fileUser.getBaseFile());
        return fileUserDTO;
    }
}
