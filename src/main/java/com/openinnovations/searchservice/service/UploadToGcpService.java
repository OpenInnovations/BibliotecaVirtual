package com.openinnovations.searchservice.service;

import com.google.api.client.util.DateTime;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.repository.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UploadToGcpService {

    @Autowired
    private Storage storage;
    @Autowired
    IBook bookRepository;

    private Book book;

    public Book upload(MultipartFile filePart, String author, String description, String category) throws IOException {
        DateTime dt = new DateTime(new Date());
        String dtString = dt.toString();
        final String fileName = dtString +"-"+ filePart.getOriginalFilename();
        final String bucketName = "biblioteca-virtual";

        BlobInfo blobInfo = storage.create(
                BlobInfo
                        .newBuilder(bucketName, fileName)
                        .setContentType(filePart.getContentType())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                        .build(),
                filePart.getBytes());

        book = new Book();
        book.setTitle(filePart.getOriginalFilename());
        book.setFilename(fileName);
        book.setUrl(blobInfo.getMediaLink());
        book.setAuthor(author);
        book.setDescription(description);
        book.setCategory(category);
        book.setHidden(false);

        /** Hacer peticion FileProcesing **/

        List<String> keywords = new ArrayList<String>();
        keywords.add("algo");
        keywords.add("mas");
        keywords.add("bueno");
        book.setKeywords(keywords);

        /** END Hacer peticion FileProcesing**/

        book.setUploadedDate(new Date());

        bookRepository.save(book);

        return bookRepository.save(book);
    }

}