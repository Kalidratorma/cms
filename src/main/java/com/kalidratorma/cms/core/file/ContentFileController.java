package com.kalidratorma.cms.core.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("site")
@RequiredArgsConstructor
class ContentFileController {

    private final FileLocationService fileLocationService;

    @GetMapping(value = "/{siteName}/files")
    public List<ContentFile> readUsers(@PathVariable String siteName) {
        return fileLocationService.findAllFilesBySiteName(siteName);
    }

    @PostMapping("/{siteName}/files")
    String uploadImage(
            @PathVariable String siteName,
            @RequestParam MultipartFile file) throws Exception {
        long maxSize;
        if (file.getContentType() == null) {
            throw new Exception("Unknown file format");
        } else if  (file.getContentType().compareToIgnoreCase("video") >= 0) {
            maxSize = 1024L * 1024L * 100L; // 100 Mb
        } else if (file.getContentType().compareToIgnoreCase("image") >= 0) {
            maxSize = 1024L * 1024L; // 1 Mb
        } else {
            throw new Exception("Unknown file format");
        }
        if (file.getSize() > maxSize) {
            throw new Exception("File size is too large");
        }
        return fileLocationService.save(siteName, file.getBytes(), file.getOriginalFilename());
    }

    @GetMapping(value = "/{siteName}/files/{fileName}.{ext}")
    ResponseEntity<FileSystemResource> downloadImage(@PathVariable String siteName
            , @PathVariable String fileName
            , @PathVariable String ext
    ) {
        FileSystemResource fileResource = fileLocationService.find(siteName, fileName + "." + ext);

        HttpHeaders responseHeaders = new HttpHeaders();
        String headerValue = switch (ext.toLowerCase()) {
            case "jpg", "jpeg", "png", "svg" -> "image/*";
            case "webm", "ogv", "mp4" -> "video/*";
            default -> "multipart/form-data";
        };

        responseHeaders.add("Content-Type"
                , headerValue);
        return new ResponseEntity<>(fileResource, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/{siteName}/files/{fileName}")
    public HttpStatus deleteFile(@PathVariable String siteName,
                                 @PathVariable String fileName) {
        fileLocationService.deleteFileBySiteName(siteName, fileName);
        return HttpStatus.OK;
    }
}