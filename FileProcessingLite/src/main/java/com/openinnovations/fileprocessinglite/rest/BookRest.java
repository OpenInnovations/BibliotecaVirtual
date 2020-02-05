package com.openinnovations.fileprocessinglite.rest;

import com.openinnovations.fileprocessinglite.clients.SentimentClient;
import com.openinnovations.fileprocessinglite.model.Book;
import com.openinnovations.fileprocessinglite.service.PdfService;
import com.openinnovations.fileprocessinglite.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin({"*"})
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

            MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
            payload.add("api_key", "Gmq904sbJhCd4eDBhVEgXeurGJurzEuQuHrLQ9dgTKA");
            payload.add("text", book.getKeywords().stream().collect(Collectors.joining()));
            book.setSentiment(SentimentClient.analizar(payload));
            payload = null;
            System.gc();

            return new ResponseEntity<>(book, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }
}
