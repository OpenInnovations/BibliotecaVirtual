package com.openinnovations.searchservice.rest;

import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.service.GcsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/file")
public class GcsRest {

    @Autowired
    private GcsService GCSService;

    @PostMapping(value = "/upload")
    public Book uploadFile(
            @RequestParam("file") MultipartFile filePart,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            @RequestParam("category") String category
    ) throws IOException {
        return GCSService.upload(filePart, author, description, category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(
            @PathVariable("id") String id
    ) {
        return GCSService.delete(id);
    }
}
