package com.openinnovations.fileprocessinglite.rest;

import com.openinnovations.fileprocessinglite.model.Book;
import com.openinnovations.fileprocessinglite.service.PdfService;
import com.openinnovations.fileprocessinglite.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class BookRest {

    @PostMapping
    ResponseEntity<Book> extraer(@RequestParam Optional<String> storageFileName) throws IOException {

        //1580768555676-Programa_lectivo.pdf
        if (storageFileName.isPresent()) {

            StorageService storageService = new StorageService();
            storageService.descargarFromStorage(storageFileName.get());

            PdfService pdfService = new PdfService();
            pdfService.extraerDatos();
            Book book = pdfService.getBook();

            storageService = null;
            pdfService = null;
            System.gc();

            return new ResponseEntity<>(book, HttpStatus.OK);

        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }
}
