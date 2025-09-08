package com.example.Mascotas.controller;

import com.example.Mascotas.entity.Mascota;
import com.example.Mascotas.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> getAllMascotas() {
        return mascotaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable String id) {
        return mascotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mascota> createMascota(@Valid @RequestBody Mascota mascota) {
        Mascota saved = mascotaService.save(mascota);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable String id, @Valid @RequestBody Mascota mascota) {
        return mascotaService.findById(id).map(existing -> {
            existing.setNombre(mascota.getNombre());
            existing.setTipo(mascota.getTipo());
            existing.setEdad(mascota.getEdad());
            existing.setRaza(mascota.getRaza());
            Mascota updated = mascotaService.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable String id) {
        if (mascotaService.findById(id).isPresent()) {
            mascotaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
