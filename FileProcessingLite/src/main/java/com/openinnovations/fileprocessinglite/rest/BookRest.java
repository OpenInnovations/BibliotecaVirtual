package com.openinnovations.fileprocessinglite.rest;

import com.openinnovations.fileprocessinglite.clients.SentimentClient;
import com.openinnovations.fileprocessinglite.model.Book;
import com.openinnovations.fileprocessinglite.service.PdfService;
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

@CrossOrigin({"*"})
@RestController
public class BookRest {

    @PostMapping
    ResponseEntity<Book> extraer(@RequestParam Optional<String> storageFileName) throws IOException {

        //1580768555676-Programa_lectivo.pdf
        if (storageFileName.isPresent()) {


            PdfService pdfService = new PdfService();
            pdfService.extraerDatos(storageFileName.get());
            Book book = pdfService.getBook();

            pdfService = null;

            MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
            payload.add("api_key", "Gmq904sbJhCd4eDBhVEgXeurGJurzEuQuHrLQ9dgTKA");
            payload.add("text", String.join(" ", book.getKeywords()));
            book.setSentiment(SentimentClient.analizar(payload));
            payload = null;
            System.gc();

            return new ResponseEntity<>(book, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }
}
