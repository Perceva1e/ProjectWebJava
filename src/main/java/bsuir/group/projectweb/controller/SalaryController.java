package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static bsuir.group.projectweb.controller.TextController.loggerMain;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class SalaryController {
    private TextService service;

    @DeleteMapping("delete_salary/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable final Long id) {
        loggerMain.info("start delete salary");
        if (service.deleteSalary(id)) {
            return new ResponseEntity<>("Delete salary", HttpStatus.OK); }
        else {
            loggerMain.error("salary not found by id");
            return new ResponseEntity<>("salary not found", HttpStatus.NOT_FOUND); }
    }
    @PutMapping("change_salary/{id}/{price}")
    public ResponseEntity<String> changeSalaryById(@PathVariable final Long id, @PathVariable Integer price) {
        loggerMain.info("start change salary");
        boolean checkError =service.changeSalaryById(id,price);
        if (checkError) {
            return new ResponseEntity<>("salary is change", HttpStatus.OK); }
        else {
            loggerMain.error("id not find");
            return new ResponseEntity<>("id not find", HttpStatus.NOT_FOUND); }
    }
    @PostMapping("/save_salary")
    public Salary saveSalary(@RequestBody final Salary salary) {
        loggerMain.info("start save a salary");
        return service.saveSalary(salary);
    }
}
