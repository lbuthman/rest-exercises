package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.SpringExercisesApplication;
import com.lbuthman.springexercises.domain.Visitor;
import com.lbuthman.springexercises.repositories.VisitorRepository;
import com.lbuthman.springexercises.services.VisitorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringExercisesApplication.class)
public class VisitorControllerTest {

    private final static String DEFAULT_NAME = "Luke";
    private final static String UPDATED_NAME = "Calvin";

    @MockBean
    private VisitorService service;

    @Autowired
    private VisitorRepository repository;

    private MockMvc mockMvc;

    private Visitor visitor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        final VisitorController controller = new VisitorController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Create a Visitor entity for tests
     */
    public static Visitor createVisitor() {
        Visitor visitor = new Visitor();
        visitor.setName(DEFAULT_NAME);
        return visitor;
    }

    /**
     * Initialize default visitor before each test runs
     */
    @Before
    public void initTest() {
        visitor = createVisitor();
    }

    @Transactional
    @Test
    public void getAllVisitors() throws Exception {
        //initialize repository
        repository.saveAndFlush(visitor);

        when(service.getAllVisitors()).thenReturn(repository.findAll());

        //when
        mockMvc.perform(get("/api/v1/visitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItems(visitor.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItems(DEFAULT_NAME)));
    }

//    @Test
//    public void createVisitor() {
//    }
//
//    @Test
//    public void updateVisitor() {
//    }
//
//    @Test
//    public void deleteVisitor() {
//    }
}