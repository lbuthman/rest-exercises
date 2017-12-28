package com.lbuthman.springexercises.services;

import com.lbuthman.springexercises.domain.Visitor;
import com.lbuthman.springexercises.repositories.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private VisitorRepository repository;

    public VisitorServiceImpl(VisitorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return repository.findAll();
    }

    @Override
    public Visitor getVisitorByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Visitor createVisitor(Visitor visitor) {
        return repository.save(visitor);
    }

    @Override
    public Visitor updateVisitor(Visitor visitor) {
        return repository.save(visitor);
    }

    @Override
    public void deleteVisitor(Long id) {
        repository.delete(id);
    }
}
