package com.librossss.demo.controller;

import com.librossss.demo.entity.Libro;
import com.librossss.demo.repository.LibroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository repo;

    public LibroController(LibroRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Libro> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return repo.save(libro);
    }

    @GetMapping("/{id}")
    public Libro buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
