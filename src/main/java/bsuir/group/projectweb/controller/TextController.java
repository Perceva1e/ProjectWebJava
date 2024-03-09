package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class  TextController {
    private TextService service;
    static final Logger loggerMain = LogManager.getLogger(TextController.class);
    @GetMapping
    public List<Text> findAllText() {
        loggerMain.info("display all text");
        return service.findAllText();
    }
    @GetMapping("/getText/{id}")
    public Text getText(@PathVariable Long id) {
        loggerMain.info("get text: {}",id);
        return service.getText(id);
    }
    @GetMapping("/find_text_lastname/{lastName}/{nameList}")
    public List<Author> findAuthorByParameters(@PathVariable final String lastName,
                                               @PathVariable final List<String> nameList) {
        loggerMain.info("find author by lastname and name");
        return service.findAuthorByParameters(lastName, nameList);
    }
    @PostMapping("/save_people")
    public Author saveAuthor(@RequestBody final Author author) {
        loggerMain.info("save a author");
        return  service.savePerson(author);
    }
    @PostMapping("/save_salary")
    public Salary saveSalary(@RequestBody final Salary salary) {
        loggerMain.info("save a salary");
        return service.saveSalary(salary);
    }
    @PostMapping("/save_information")
    public Text saveText(@RequestBody final Text information) {
        loggerMain.info("sava a text");
        return service.saveText(information);
    }
    @PostMapping("/find_phone_number_email")
    public Text findNumberPhoneAndEmail(@RequestBody final Text information) {
        loggerMain.info("find telephone and email");
        return service.findNumberPhoneAndEmail(information);
    }
    @PutMapping("change")
    public Text changeText(@RequestBody final Text information) {
        loggerMain.info("change a text");
        return service.changeText(information);
    }
    @GetMapping("/{information}")
    public ResponseEntity<String> findByInformation(@PathVariable final String information) {
        loggerMain.info("find information");
        if (service.existByText(information)) {
            service.findByText(information);
            return new ResponseEntity<>("information is find", HttpStatus.OK);
        }
        else {
            loggerMain.info("information not found");
            return new ResponseEntity<>("information not found", HttpStatus.NOT_FOUND); }

    }
    @DeleteMapping("delete_text/{id}")
    public ResponseEntity<String> deleteText(@PathVariable final Long id) {
        loggerMain.info("delete text");
        if (service.deleteText(id)) {
            return new ResponseEntity<>("Delete information", HttpStatus.OK); }
        else {
            loggerMain.info("text not found by id");
            return new ResponseEntity<>("information not found", HttpStatus.NOT_FOUND); }
    }
}
