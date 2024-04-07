package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.service.SalaryService;
import bsuir.group.projectweb.service.impl.CounterServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class SalaryController {
    /**
     * This is a logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(
            SalaryController.class);
    /**
     * This is a server.
     */
    private SalaryService service;

    /**
     * This method delete salary.
     *
     * @param id is an entity for delete
     * @return restore http status
     */
    @DeleteMapping("delete_salary_in_author/{id}")
    public ResponseEntity<String> deleteSalaryInAuthor(
            @PathVariable final Long id) {
        LOGGER.info("start delete salary");
        boolean checkError = service.deleteSalaryInAuthor(id);
        if (checkError) {
            return new ResponseEntity<>("""
                    {
                    Delete salary
                    }""", HttpStatus.OK);
        } else {
            LOGGER.error("salary not found by id");
            return new ResponseEntity<>("""
                    {
                    salary not found
                    }""", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method change salary by id.
     *
     * @param id    is an entity for change
     * @param price is a string for change
     * @return restore http status
     */
    @PutMapping("change_salary_in_author/{id}/{price}")
    public ResponseEntity<String> changeSalaryByIdInAuthor(
            @PathVariable final Long id, @PathVariable final Integer price) {
        LOGGER.info("start change salary");
        boolean checkError = service.changeSalaryByIdInAuthor(id, price);
        if (checkError) {
            return new ResponseEntity<>("""
                    {
                    salary is change
                    }""", HttpStatus.OK);
        } else {
            LOGGER.error("id not find");
            return new ResponseEntity<>("""
                    {
                    id not find
                    }""", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method save salary.
     *
     * @param salary is an entity for save
     * @return restore text after save
     */
    @PostMapping("/save_salary")
    public ResponseEntity<String> saveSalary(
            @RequestBody final Salary salary) {
        LOGGER.info("start save a salary");
        CounterServiceImpl.enhanceCounter();
        int numberOfRequest = CounterServiceImpl.getCounter();
        LOGGER.info("number of access to service is {}", numberOfRequest);
        boolean checkError = service.saveSalary(salary);
        if (checkError) {
            return new ResponseEntity<>("""
                    {
                    salary is save
                    }""", HttpStatus.OK);
        } else {
            LOGGER.error("salary is yet save");
            return new ResponseEntity<>("""
                    {
                    salary is yet save in bd
                    }""",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
