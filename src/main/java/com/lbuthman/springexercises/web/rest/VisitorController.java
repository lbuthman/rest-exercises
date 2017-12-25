package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.domain.Visitor;
import com.lbuthman.springexercises.services.VisitorService;
import com.lbuthman.springexercises.web.rest.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
public class VisitorController {

    private final Logger log = LoggerFactory.getLogger(VisitorController.class);

    private static final String ENTITY_NAME = "visitor";

    private final VisitorService service;

    public VisitorController(VisitorService service) {
        this.service = service;
    }

    /**
     * GET / : get all the visitors
     *
     * @return a status of 200 (OK) and a List of visitors
     */
    @GetMapping
    public List<Visitor> getAllVisitors() {
        log.debug("REST request to get all visitors");
        return service.getAllVisitors();
    }

    /**
     * GET /:name : get the visitor by name
     *
     * @param name the name of the visitor to retrieve
     * @return a status of 200 (OK) and the found visitor
     * or a status of 404 if visitor entity is not found
     */
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Visitor getVisitor(@PathVariable String name) {
        log.debug("REST request user with provided name : {}", name);
        Visitor visitor = service.getVisitorByName(name);
        if (visitor == null) {
            throw new ResourceNotFoundException("Visitor with the name " +
                    name + " was not found.", ENTITY_NAME);
        }
        return visitor;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Visitor createVisitor(@RequestBody Visitor visitor) {
        log.debug("REST request to save Visitor : {}", visitor);
        return service.createVisitor(visitor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Visitor updateVisitor(@PathVariable Long id, @RequestBody Visitor visitor) {
        log.debug("REST request to update Visitor : {}", visitor);
        return  service.updateVisitor(id, visitor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVisitor(@PathVariable Long id) {
        log.debug("REST request to delete Visitor Id : {}", id);
        service.deleteVisitor(id);
    }
}
