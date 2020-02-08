package com.openinnovations.searchservice.service;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.openinnovations.searchservice.client.SentimentClient;
import com.openinnovations.searchservice.model.Book;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PdfService {


    public Book extraerDatos(File file, Book book) throws IOException {
        Set<String> texto = new HashSet<>();


        PdfReader reader = new PdfReader(file.getAbsolutePath());
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        SimpleTextExtractionStrategy strategy;

        book.setInfo(reader.getInfo());
        book.setNumberPages(reader.getNumberOfPages());
        book.setSize(reader.getFileLength());

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());

            Arrays.stream(strategy.getResultantText()
                    .replace("\r", "")
                    .trim()
                    .toLowerCase()
                    .split("\n"))
                    .forEach((e) -> {
                        if (e.contains("isbn"))
                            try {
                                String isbn = e.split(" ")[1];
                                if (isbn.contains("-"))
                                    if (isbn.split("-").length >= 4)
                                        book.setIsbn(isbn);

                            } catch (Exception ignored) {
                            }

                        Arrays.stream(e.replaceAll("[^a-zA-Z0-9]", " ").split(" "))
                                .filter(t -> t.length() > 4 && !t.matches("-?\\d+(\\.\\d+)?"))
                                .forEach(texto::add);

                    });

            book.setKeywords(texto);
            file.delete();

        }

        reader.close();
        file.delete();
        MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
        payload.add("api_key", "Gmq904sbJhCd4eDBhVEgXeurGJurzEuQuHrLQ9dgTKA");
        payload.add("text", String.join(" ", book.getKeywords()));
        book.setSentiment(SentimentClient.analizar(payload));
        return book;
    }
}
