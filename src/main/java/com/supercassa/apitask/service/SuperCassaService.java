package com.supercassa.apitask.service;

import com.supercassa.apitask.entity.SuperKassa;
import com.supercassa.apitask.repository.SuperCassaRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SuperCassaService {

    private SuperCassaRepository superCassaRepository;

    @Async
    @Transactional(readOnly = true)
    public SuperKassa findSupperKassaById(Integer id) {
        return superCassaRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("SuperKassa was not found", id));
    }

    @Async
    @Transactional
    public void save(SuperKassa superKassa) {
        superCassaRepository.save(superKassa);
    }
}
