package com.openinnovations.fileprocessing.rest;

import com.openinnovations.fileprocessing.model.Book;
import com.openinnovations.fileprocessing.model.SentimentResult;
import com.openinnovations.fileprocessing.services.PdfService;
import com.openinnovations.fileprocessing.services.SentimentAnalyzerService;
import com.openinnovations.fileprocessing.services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookRest {

    @PostMapping
    ResponseEntity<Book> extraer(@RequestParam Optional<String> storageFileName) throws IOException {

        //1580768555676-Programa_lectivo.pdf
        if (storageFileName.isPresent()) {
            StorageService storageService = new StorageService();

            storageService.descargarFromStorage(storageFileName.get());

            Book book = new PdfService().getBook();

            SentimentAnalyzerService sentimentAnalyzer = new SentimentAnalyzerService();
            sentimentAnalyzer.initialize();
            SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(book.getKeywords().stream().collect(Collectors.joining()));
            book.setSentimentResult(sentimentResult);
            return new ResponseEntity<>(book, HttpStatus.OK);

        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }
}
