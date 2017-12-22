package com.lbuthman.springexercises.services;

import com.lbuthman.springexercises.domain.Visitor;
import com.lbuthman.springexercises.repositories.VisitorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VisitorServiceTest {

    private VisitorService service;

    @Mock
    VisitorRepository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new VisitorServiceImpl(repository);
    }

    @Test
    public void getAllVisitors() {
        //given
        List<Visitor> visitors = Arrays.asList(new Visitor(), new Visitor());

        when(repository.findAll()).thenReturn(visitors);

        //when
        List<Visitor> foundVisitors = service.getAllVisitors();

        //then
        assertEquals(2, foundVisitors.size());

    }

    @Test
    public void getVisitorByName() {
        final Long ID = 1L;
        final String NAME = "Luke";

        //given
        Visitor visitor = new Visitor();
        visitor.setName(NAME);
        visitor.setId(ID);

        when(repository.findByName(NAME)).thenReturn(visitor);

        //when
        Visitor foundVisitor = service.getVisitorByName(NAME);

        //then
        assertEquals(ID, foundVisitor.getId());
        assertEquals(NAME, foundVisitor.getName());
    }
}