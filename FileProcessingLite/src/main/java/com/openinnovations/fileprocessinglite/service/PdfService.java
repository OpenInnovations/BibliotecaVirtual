package com.openinnovations.fileprocessinglite.service;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.openinnovations.fileprocessinglite.model.Book;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PdfService {

    private Book book;

    public PdfService() {
        this.book = new Book();
    }

    public void extraerDatos(String storageFileName) throws IOException {
        Set<String> texto = new HashSet<>();

        StorageService storageService = new StorageService();
        storageService.descargarFromStorage(storageFileName);

        PdfReader reader = new PdfReader(storageService.getTemporalPdf().getAbsolutePath());
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
//            texto.clear();

        }

        reader.close();
        storageService.getTemporalPdf().delete();
    }

    public Book getBook() {
        return book;
    }
}
