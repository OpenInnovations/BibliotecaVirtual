package com.openinnovations.searchservice.rest;

import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.repository.IBook;
import com.openinnovations.searchservice.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/search")
public class BookRest {

    @Autowired
    IBook repo;

    @GetMapping
    public List<Book> listarTodo() {
        return repo.findAll();
    }

    @GetMapping("/keywords")
    public List<Book> buscarKeywords(@RequestBody List<String> keywords) {
        return repo.findAllByKeywordsContains(keywords);
    }

    @GetMapping("/titulo/{titulo}")
    public List<Book> buscarTitulo(@PathVariable("titulo") String titulo) {
        return repo.findAllByTitleContains(titulo.toLowerCase());
    }

    @GetMapping("/autor/{autor}")
    public List<Book> buscarAutor(@PathVariable("autor") String autor) {
        return repo.findAllByAuthorContains(autor.toLowerCase());
    }

    @GetMapping("/categoria/{categoria}")
    public List<Book> buscarCategoria(@PathVariable("categoria") String categoria) {
        return repo.findAllByCategoryContains(categoria.toLowerCase());
    }

    //**
    // Solo para pruebas

    @PostMapping
    public List<Book> registrar(@RequestBody List<Book> books) {
        for (Book book : books) {
            repo.save(book);
        }
        return repo.findAll();
    }

    @DeleteMapping
    public List<Book> borrarTodo() {
        repo.deleteAll();
        return repo.findAll();
    }

}
