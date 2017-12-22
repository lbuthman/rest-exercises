package com.lbuthman.springexercises.services;

import com.lbuthman.springexercises.domain.Visitor;

import java.util.List;

public interface VisitorService {

    List<Visitor> getAllVisitors();

    Visitor getVisitorByName(String name);

    Visitor createVisitor(Visitor visitor);

    Visitor updateVisitor(Long id, Visitor visitor);

    void deleteVisitor(Long id);
}
