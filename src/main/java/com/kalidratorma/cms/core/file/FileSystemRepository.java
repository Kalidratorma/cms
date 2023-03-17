package com.kalidratorma.cms.core.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

@Repository
class FileSystemRepository {

    @Value("${RESOURCES_DIR}")
    private String RESOURCES_DIR;

    String save(String siteName, byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + "/" + siteName + "/" + imageName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath()
                .toString();
    }

    FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }

}