package com.pe2.api.repository;

import com.pe2.api.domain.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AssigneeRepository  extends JpaRepository<Assignee,Long> {
    Optional<Assignee> findByName(String name);

    boolean existsByEmail(String value);
}
