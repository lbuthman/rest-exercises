package com.lbuthman.springexercises.web.rest;

import com.lbuthman.springexercises.domain.Visitor;
import com.lbuthman.springexercises.services.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
public class VisitorResource {

    private final Logger log = LoggerFactory.getLogger(VisitorResource.class);

    private static final String ENTITY_NAME = "visitor";

    private final VisitorService service;

    public VisitorResource(VisitorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Visitor> getAllVisitors() {
        log.debug("REST request to get all visitors");
        return service.getAllVisitors();
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
