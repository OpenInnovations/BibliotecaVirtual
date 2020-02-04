package com.openinnovations.searchservice.rest;

import com.google.api.client.util.DateTime;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.service.UploadToGcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
public class UploadToGcpRest {

    @Autowired
    private UploadToGcpService upload;

    @PostMapping(value = "/uploadFile")
    public Book uploadFile(
            @RequestParam("file") MultipartFile filePart,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            @RequestParam("category") String category
    ) throws IOException {

        return upload.upload(filePart, author, description, category);

    }
}
