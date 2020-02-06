package com.openinnovations.securityservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "bibliotecaClient", url = "http://35.237.68.44:8081")
public interface IBibliotecaClient {

    final String SEARCH = "/search";
    final String TRANSFER = "/file";

    @GetMapping(value = SEARCH)
    List<Object> findAll();

    @GetMapping(value = SEARCH + "/keywords")
    List<Object> findByKeywords(@RequestBody List<String> keywords);

    @GetMapping(value = SEARCH + "/titulo/{titulo}")
    List<Object> buscarTitulo(@PathVariable("titulo") String titulo);

    @GetMapping(value = SEARCH + "/autor/{autor}")
    List<Object> buscarAutor(@PathVariable("autor") String autor);

    @GetMapping(value = SEARCH + "/categoria/{categoria}")
    List<Object> buscarCategoria(@PathVariable("categoria") String categoria);


    @PostMapping(value = TRANSFER + "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Object uploadFile(
            @RequestPart("file") MultipartFile filePart,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            @RequestParam("category") String category
    ) throws IOException;

    @DeleteMapping(value = TRANSFER + "/delete/{id}")
    ResponseEntity<Void> deleteFile(
            @PathVariable("id") String id
    );
}
