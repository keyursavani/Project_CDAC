package com.myfile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myfile.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long>{

	Optional<FileData> findByName(String fileName);
	boolean existsByName(String fileName);
}
