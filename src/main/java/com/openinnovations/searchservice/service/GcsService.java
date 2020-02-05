package com.openinnovations.searchservice.service;

import com.google.api.client.util.DateTime;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.model.FileProcessing;
import com.openinnovations.searchservice.repository.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class GcsService {

    @Autowired
    private IBook bookRepository;
    @Autowired
    private FileProcessingService fileProcessing_S;

    private Storage storage;

    public GcsService() throws IOException {
        storage = StorageOptions.newBuilder()
                .setProjectId("bibliotecavirtual-266119")
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("static/credenciales.json").getInputStream()))
                .build()
                .getService();
    }


    public Book upload(MultipartFile filePart, String author, String description, String category) throws IOException {

        final String fileName = new DateTime(new Date()).toString() + "-" + filePart.getOriginalFilename();
        final String bucketName = "biblioteca-virtual";


        BlobInfo blobInfo = storage.create(
                BlobInfo
                        .newBuilder(bucketName, fileName)
                        .setContentType(filePart.getContentType())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                        .build(),
                filePart.getBytes());

        System.out.println(blobInfo.getBlobId());

        Book book = new Book();

        book.setTitle(filePart.getOriginalFilename());
        book.setBlobId(blobInfo.getBlobId());
        book.setAuthor(author);
        book.setDescription(description);
        book.setUrl(blobInfo.getMediaLink());

        FileProcessing fileProcessing = fileProcessing_S.sedUrl(fileName);
        book.setIsbn(fileProcessing.getIsbn());
        book.setNumberPages(fileProcessing.getNumberPages());
        book.setSentiment(fileProcessing.getSentiment());
        book.setKeywords(fileProcessing.getKeywords());
        book.setInfo(fileProcessing.getInfo());
        book.setSize(fileProcessing.getSize());

        book.setUploadedDate(new Date());
        book.setCategory(category);
        book.setHidden(false);

        bookRepository.save(book);

        return bookRepository.save(book);
    }

    public ResponseEntity<Void> delete(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (!book.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        book.ifPresent(x -> {
            storage.delete(x.getBlobId());
            bookRepository.deleteById(id);

        });

        return ResponseEntity.ok().build();

    }

}