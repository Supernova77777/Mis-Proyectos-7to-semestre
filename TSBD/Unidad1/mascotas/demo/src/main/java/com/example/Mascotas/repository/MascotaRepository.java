package com.example.Mascotas.repository;

import com.example.Mascotas.entity.Mascota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends MongoRepository<Mascota, String> {
}
