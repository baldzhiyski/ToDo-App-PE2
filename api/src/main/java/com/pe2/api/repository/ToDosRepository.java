package com.pe2.api.repository;

import com.pe2.api.domain.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDosRepository extends JpaRepository<ToDo,Long> {
}
