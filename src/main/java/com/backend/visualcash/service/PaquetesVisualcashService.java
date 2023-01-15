package com.backend.visualcash.service;

import com.backend.visualcash.entity.Paquete;
import com.backend.visualcash.repository.PaquetesVisualcashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaquetesVisualcashService {

    @Autowired
    PaquetesVisualcashRepository paqueteRepository;

    public List<Paquete> list(){
        return paqueteRepository.findAll();
    }

    public Optional<Paquete> getOne(int id){
        return paqueteRepository.findById(id);
    }

    public Optional<Paquete> getByNombre(String nombre){
        return paqueteRepository.findByNombre(nombre);
    }

    public void  save(Paquete producto){
        paqueteRepository.save(producto);
    }

    public void delete(int id){
        paqueteRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return paqueteRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return paqueteRepository.existsByNombre(nombre);
    }
}
