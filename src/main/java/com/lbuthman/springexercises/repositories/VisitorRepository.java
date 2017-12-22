package com.lbuthman.springexercises.repositories;

import com.lbuthman.springexercises.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>{

    Visitor findByName(String name);
}
