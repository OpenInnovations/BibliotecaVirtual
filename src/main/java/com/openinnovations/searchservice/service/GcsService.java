package com.openinnovations.searchservice.service;

import com.google.api.client.util.DateTime;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.repository.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class GcsService {

    @Autowired
    private IBook bookRepository;


    private Storage storage;

    public GcsService() throws IOException {
        storage = StorageOptions.newBuilder()
                .setProjectId("bibliotecavirtual-266119")
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("static/credenciales.json").getInputStream()))
                .build()
                .getService();
    }


    public Book upload(MultipartFile filePart, String author, String description, String category) throws IOException {

        final String FILENAME = new DateTime(new Date()).toString() + "-" + filePart.getOriginalFilename();
        final String BUCKETNAME = "biblioteca-virtual";


        BlobInfo blobInfo = storage.create(
                BlobInfo
                        .newBuilder(BUCKETNAME, FILENAME)
                        .setContentType(filePart.getContentType())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                        .build(),
                filePart.getBytes());


        System.out.println(blobInfo.getBlobId());

        Book book = new Book();

        book.setTitle(Objects.requireNonNull(filePart.getOriginalFilename()).toLowerCase());
        book.setBlobId(blobInfo.getBlobId());
        book.setAuthor(author.toLowerCase());
        book.setDescription(description.toLowerCase());
        book.setUrl(blobInfo.getMediaLink());


        PdfService pdfService = new PdfService();

        pdfService.extraerDatos(this.convert(filePart), book);

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

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}