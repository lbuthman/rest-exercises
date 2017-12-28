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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringExercisesApplication.class)
public class VisitorControllerTest extends AbstractRestControllerTest {

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
        repository.deleteAll();
        visitor = createVisitor();
    }

    @Test
    public void getAllVisitors() throws Exception {
        //initialize repository
        repository.saveAndFlush(visitor);

        when(service.getAllVisitors()).thenReturn(repository.findAll());

        mockMvc.perform(get("/api/v1/visitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItems(visitor.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItems(DEFAULT_NAME)));
    }

    @Test
    public void getVisitor() throws Exception {
        //initialize repository
        repository.saveAndFlush(visitor);

        when(service.getVisitorByName(DEFAULT_NAME)).thenReturn(repository.findByName(DEFAULT_NAME));

        mockMvc.perform(get("/api/v1/visitors/{name}", visitor.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(visitor.getId().intValue()))
                .andExpect(jsonPath("$.name").value(visitor.getName()));
    }

    @Test
    public void getNonExistentVisitor() throws Exception {
        mockMvc.perform(get("/api/v1/visitors/bad"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewVisitor() throws Exception {
        int repoCurrentSize = repository.findAll().size();

//        when(service.createVisitor(visitor)).thenReturn(visitor);
        mockMvc.perform(post("/api/v1/visitors")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(visitor)))
                .andExpect(status().isCreated());

        List<Visitor> visitors = repository.findAll();
        assertThat(visitors.size() == repoCurrentSize + 1);
        assertThat(visitors.contains(DEFAULT_NAME));

    }

    @Test
    public void createVisitorWithExistingId() throws Exception {
        //initialize repository
        repository.saveAndFlush(visitor);

        mockMvc.perform(post("/api/v1/visitors")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(visitor)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void updateVisitor() throws Exception {
        //initialize repository
        repository.saveAndFlush(visitor);

        int sizeBeforeUpdate = repository.findAll().size();

        Visitor updatedVisitor = repository.findOne(visitor.getId());
        updatedVisitor.setName(UPDATED_NAME);

        mockMvc.perform(put("/api/v1/visitors")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(updatedVisitor)))
                .andExpect(status().isOk());

        List<Visitor> allVisitors = repository.findAll();
        assertThat(allVisitors).hasSize(sizeBeforeUpdate);
        assertThat(allVisitors.contains(UPDATED_NAME));
    }
//
//    @Test
//    public void deleteVisitor() {
//    }
}