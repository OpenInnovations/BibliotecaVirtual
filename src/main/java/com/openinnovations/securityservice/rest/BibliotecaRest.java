package com.openinnovations.securityservice.rest;

import com.openinnovations.securityservice.clients.IBibliotecaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/biblioteca")
public class BibliotecaRest {

    @Autowired
    private IBibliotecaClient bibliotecaClient;

    final String SEARCH = "/search";
    final String TRANSFER = "/file";

    @GetMapping(value = SEARCH)
    List<Object> findAll() {
        return bibliotecaClient.findAll();
    }

    @GetMapping(value = SEARCH + "/keywords")
    List<Object> findByKeywords(@RequestBody List<String> keywords) {
        return bibliotecaClient.findByKeywords(keywords);
    }

    @GetMapping(value = SEARCH + "/titulo/{titulo}")
    List<Object> buscarTitulo(@PathVariable("titulo") String titulo) {
        return bibliotecaClient.buscarTitulo(titulo);
    }

    @GetMapping(value = SEARCH + "/autor/{autor}")
    List<Object> buscarAutor(@PathVariable("autor") String autor) {
        return bibliotecaClient.buscarAutor(autor);
    }

    @GetMapping(value = SEARCH + "/categoria/{categoria}")
    List<Object> buscarCategoria(@PathVariable("categoria") String categoria) {
        return bibliotecaClient.buscarCategoria(categoria);
    }


    @PostMapping(value = TRANSFER + "/upload")
    Object uploadFile(
            @RequestParam("file") MultipartFile filePart,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            @RequestParam("category") String category
    ) {
        return bibliotecaClient.uploadFile(
                filePart,
                author,
                description,
                category
        );
    }

    @DeleteMapping(value = TRANSFER + "/delete/{id}")
    ResponseEntity<Void> deleteFile(
            @PathVariable("id") String id
    ) {
        return bibliotecaClient.deleteFile(id);
    }


}
