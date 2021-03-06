package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.domain.Visitor;
import com.lbuthman.springexercises.services.VisitorService;
import com.lbuthman.springexercises.web.rest.errors.BadRequestException;
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
     * GET /visitors : get all the visitors
     *
     * @return a status of 200 (OK) and a List of visitors
     */
    @GetMapping
    public List<Visitor> getAllVisitors() {
        log.debug("REST request to get all visitors");
        return service.getAllVisitors();
    }

    /**
     * GET /visitors/:name : get the visitor by name
     *
     * @param name the name of the visitor to retrieve
     * @return a status of 200 (OK) and the found visitor
     * or a status of 404 (NOT FOUND) if visitor entity is not found
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

    /**
     * POST /visitors : post a new visitor entity
     *
     * @param visitor the visitor entity to create
     * @return a status of 201 (CREATED) and new visitor
     * or a status of 400 (BAD REQUEST) if id already exists
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Visitor createVisitor(@RequestBody Visitor visitor) {
        log.debug("REST request to save Visitor : {}", visitor);
        if (visitor.getId() != null) {
            throw new BadRequestException("A new visitor cannot already have an id",
                    ENTITY_NAME);
        }
        return service.createVisitor(visitor);
    }

    /**
     * PUT /visitors :
     *
     * @param visitor the visitor entity to update
     * @return a status of 200 (OK) and updated visitor
     * or a status of 400 (BAD REQUEST) if visitor doesn't exist
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Visitor updateVisitor(@RequestBody Visitor visitor) {
        log.debug("REST request to update Visitor : {}", visitor);
        if (visitor.getId() == null) {
            throw new BadRequestException("A visitor must have an ID to be updated",
                    ENTITY_NAME);
        }
        return  service.updateVisitor(visitor);
    }

    /**
     * DELETE /visitors/:id : delete the visitor with id
     *
     * @param id the id of the visitor to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVisitor(@PathVariable Long id) {
        log.debug("REST request to delete Visitor Id : {}", id);
        service.deleteVisitor(id);
    }
}
