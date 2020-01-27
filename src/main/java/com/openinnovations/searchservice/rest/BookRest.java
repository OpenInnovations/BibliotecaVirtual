package com.openinnovations.searchservice.rest;

import com.openinnovations.searchservice.model.Book;
import com.openinnovations.searchservice.repository.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
public class BookRest {

    @Autowired
    IBook repo;

    @GetMapping("/keywords")
    public List<Book> buscarKeywords(@RequestBody List<String> keywords) {
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

    //Solo para pruebas
    @PostMapping
    public List<Book> registrar(@RequestBody List<Book> books) {
        for (Book book : books) {
            repo.save(book);
        }
        return repo.findAll();
    }

    //Solo para pruebas
    @DeleteMapping
    public List<Book> borrarTodo() {
        repo.deleteAll();
        return repo.findAll();
    }

    //En Desarrollo
//    @GetMapping
//    public List<Book> listarTodo(@RequestBody List<String> palabras) {
//        System.out.println(palabras);
//        return repo.findAllByKeywordsContainsOrTitleContainsOrAuthorContainsOrCategoryContains(palabras, palabras.get(0), palabras.get(0), palabras.get(0));
//    }

    @GetMapping
    public List<Book> listarTodo() {
        return repo.findAll();
    }
}
