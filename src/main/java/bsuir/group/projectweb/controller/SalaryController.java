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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class SalaryController {
    /**
     * This is a SUCCESSSTATUS.
     */
    private static final String SUCCESSSTATUS = "Method success";
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
    public ResponseEntity<Message> deleteSalaryInAuthor(
            @PathVariable final Long id) {
        LOGGER.info("start delete salary");
        String errorMessage = "Error 404: Not Found";
        boolean checkError = service.deleteSalaryInAuthor(id);
        if (checkError) {
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new Message(SUCCESSSTATUS));
        } else {
            LOGGER.error("bad request");
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(new Message(errorMessage));
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
    public ResponseEntity<Message> changeSalaryByIdInAuthor(
            @PathVariable final Long id, @PathVariable final Integer price) {
        LOGGER.info("start change salary");
        String errorMessage = "Error 404: Not Found";
        boolean checkError = service.changeSalaryByIdInAuthor(id, price);
        if (checkError) {
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new Message(SUCCESSSTATUS));
        } else {
            LOGGER.error("id not find");
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(new Message(errorMessage));
        }
    }

    /**
     * This method save salary.
     *
     * @param salary is an entity for save
     * @return restore text after save
     */
    @PostMapping("/save_salary")
    public ResponseEntity<Message> saveSalary(
            @RequestBody final Salary salary) {
        LOGGER.info("start save a salary");
        String errorMessage = "Error 400: Bad request";
        CounterServiceImpl.enhanceCounter();
        int numberOfRequest = CounterServiceImpl.getCounter();
        LOGGER.info("number of access to service is {}", numberOfRequest);
        boolean checkError = service.saveSalary(salary);
        if (checkError) {
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new Message(SUCCESSSTATUS));
        } else {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new Message(errorMessage));
        }
    }
    private record Message(String message) {
    }
}
