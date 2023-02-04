package com.supercassa.apitask.repository;

import com.supercassa.apitask.entity.SuperKassa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperCassaRepository extends JpaRepository<SuperKassa, Integer> {
}
