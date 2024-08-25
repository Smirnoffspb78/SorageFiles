package com.smirnov.greenatom.repository;

import com.smirnov.greenatom.entity.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий файлов.
 */
public interface FileUserRepository extends JpaRepository<FileUser, Integer> {
}
