package com.openinnovations.searchservice.rest;

import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.repository.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRest {

    @Autowired
    IBook repo;

    @GetMapping
    public List<Book> listarTodo() {
        return repo.findAll();
    }

    //Solo para pruebas
    @PostMapping
    public Book registrar(@RequestBody Book book) {
        return repo.save(book);
    }

    @GetMapping("/keywords/{keywords}")
    public List<Book> buscarKeywords(@PathVariable("keywords") String keywords) {
        return repo.findAllByKeywordsContains(keywords);
    }

    @GetMapping("/titulo/{titulo}")
    public List<Book> buscarTitulo(@PathVariable("titulo") String titulo) {
        return repo.findAllByTitleContains(titulo);
    }

    @GetMapping("/autor/{autor}")
    public List<Book> buscarAutor(@PathVariable("autor") String autor) {
        return repo.findAllByAuthorContains(autor);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Book> buscarCategoria(@PathVariable("categoria") String categoria) {
        return repo.findAllByCategoryContains(categoria);
    }
}
