package com.kalidratorma.cms.core.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("site")
@RequiredArgsConstructor
class ContentFileController {

    private final FileLocationService fileLocationService;

    @PostMapping("/{siteName}/files")
    String uploadImage(
            @PathVariable String siteName,
            @RequestParam MultipartFile file) throws Exception {
        return fileLocationService.save(siteName, file.getBytes(), file.getOriginalFilename());
    }

    @GetMapping(value = "/{siteName}/files/{fileName}.{ext}")
    ResponseEntity<FileSystemResource> downloadImage(@PathVariable String siteName
            , @PathVariable String fileName
            , @PathVariable String ext
    ) {
        FileSystemResource fileResource = fileLocationService.find(siteName, fileName+"."+ext);

        HttpHeaders responseHeaders = new HttpHeaders();
        String headerValue;
        switch (ext.toLowerCase()) {
            case "jpg":
            case "jpeg":
            case "png":
            case "svg":
                headerValue = "image/*";
                break;
            case "webm":
            case "ogv":
            case "mp4":
                headerValue = "video/*";
                break;
            default:
                headerValue = "multipart/form-data";
                break;
        }

        responseHeaders.add("Content-Type"
                , headerValue);
        return new ResponseEntity<>(fileResource, responseHeaders, HttpStatus.OK);
    }
}