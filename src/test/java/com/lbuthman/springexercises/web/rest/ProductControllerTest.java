package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.SpringExercisesApplication;
import com.lbuthman.springexercises.domain.Product;
import com.lbuthman.springexercises.repositories.ProductRepository;
import com.lbuthman.springexercises.services.ProductService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringExercisesApplication.class)
public class ProductControllerTest extends AbstractRestControllerTest {

    private final static String DEFAULT_DESCRIPTION = "product description";
    private final static String UPDATED_DESCRIPTION = "updated description";
    private final static double DEFAULT_PRICE = 9.99;
    private final static double UPDATED_PRICE = 4.99;
    private final static int DEFAULT_STOCK = 5;
    private final static int UPDATED_STOCK = 10;

    @MockBean
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    private Product product;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        final ProductController controller = new ProductController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     *  Create new Product
     */
    public static Product createProduct() {
        Product product = new Product();
        product.setDescription(DEFAULT_DESCRIPTION);
        product.setPrice(DEFAULT_PRICE);
        product.setStock(DEFAULT_STOCK);
        return product;
    }

    /**
     *  Initialize for each test
     */
    @Before
    public void initTest() {
        repository.deleteAll();
        product = createProduct();
    }

    @Test
    public void getAllProducts() throws Exception {
        //initialize repository
        repository.saveAndFlush(product);

        when(service.getAllProducts()).thenReturn(repository.findAll());

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItems(product.getId().intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItems(product.getDescription())))
                .andExpect(jsonPath("$.[*].price").value(hasItems(product.getPrice())))
                .andExpect(jsonPath("$.[*].stock").value(hasItems(product.getStock())));
    }

    @Test
    public void getOneProduct() throws Exception {
        //initialize repository
        repository.saveAndFlush(product);

        when(service.getProduct(product.getId())).thenReturn(repository.findOne(product.getId()));

        mockMvc.perform(get("/api/v1/products/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(product.getId().intValue()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.stock").value(product.getStock()));
    }

    @Test
    public void getNonExistentProduct() throws Exception {
        //initialize repository
        repository.saveAndFlush(product);

        mockMvc.perform(get("/api/v1/products/{id}", 33L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addProduct() throws Exception {

        int repoSizeBefore = repository.findAll().size();

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(product)))
                .andExpect(status().isCreated());

        assertThat(repoSizeBefore == repository.findAll().size() + 1);
    }

    @Test
    public void checkDescriptionRequired() throws Exception {

        int repoSizeBefore = repository.findAll().size();
        product.setDescription(null);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(product)))
                .andExpect(status().isBadRequest());

        assertThat(repoSizeBefore == repository.findAll().size());
    }

    @Test
    public void createExistingProduct() throws Exception {
        //initialize repository
        repository.saveAndFlush(product);

        int repoSizeBefore = repository.findAll().size();
        product.setDescription(UPDATED_DESCRIPTION);
        product.setPrice(UPDATED_PRICE);
        product.setStock(UPDATED_STOCK);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(product)))
                .andExpect(status().isBadRequest());

        assertThat(repoSizeBefore == repository.findAll().size());
    }

}