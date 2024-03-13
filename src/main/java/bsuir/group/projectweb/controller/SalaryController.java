package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static bsuir.group.projectweb.controller.TextController.LOGGER;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class SalaryController {
    /**This method demonstrates javadoc format.
     * is a server
     */
    private TextService service;
    /**This method demonstrates javadoc format.
     *@param id is an entity for delete
     *@return restore http status
     */
    @DeleteMapping("delete_salary/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable final Long id) {
        LOGGER.info("start delete salary");
        if (service.deleteSalary(id)) {
            return new ResponseEntity<>("Delete salary", HttpStatus.OK); }
        else {
            LOGGER.error("salary not found by id");
            return new ResponseEntity<>(
                    "salary not found", HttpStatus.NOT_FOUND); }
    }
    /**This method demonstrates javadoc format.
     *@param id is an entity for change
     *@param price is a string for change
     *@return restore http status
     */
    @PutMapping("change_salary/{id}/{price}")
    public ResponseEntity<String> changeSalaryById(
            @PathVariable final Long id, @PathVariable final Integer price) {
        LOGGER.info("start change salary");
        boolean checkError = service.changeSalaryById(id, price);
        if (checkError) {
            return new ResponseEntity<>("salary is change", HttpStatus.OK); }
        else {
            LOGGER.error("id not find");
            return new ResponseEntity<>("id not find", HttpStatus.NOT_FOUND); }
    }
    /**This method demonstrates javadoc format.
     *@param salary is an entity for save
     *@return restore text after save
     */
    @PostMapping("/save_salary")
    public Salary saveSalary(@RequestBody final Salary salary) {
        LOGGER.info("start save a salary");
        return service.saveSalary(salary);
    }
}
