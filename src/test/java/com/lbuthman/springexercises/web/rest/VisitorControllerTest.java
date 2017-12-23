package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.services.VisitorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class VisitorResourceTest {

    @Mock
    private VisitorResource visitorResource;

    @Mock
    private VisitorService visitorService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(visitorResource).build();
    }

    @Test
    public void getAllVisitors() {
    }

    @Test
    public void createVisitor() {
    }

    @Test
    public void updateVisitor() {
    }

    @Test
    public void deleteVisitor() {
    }
}