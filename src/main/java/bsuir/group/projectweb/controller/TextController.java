package bsuir.group.projectweb.controller;


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
    @PostMapping("/save_information")
    public Text saveText(@RequestBody final Text information) {
        loggerMain.info("start sava a text");
        return service.saveText(information);
    }
    @PostMapping("/find_phone_number_email")
    public Text findNumberPhoneAndEmail(@RequestBody final Text information) {
        loggerMain.info("start find telephone and email");
        return service.findNumberPhoneAndEmail(information);
    }
    @PutMapping("change_information/{informationExist}/{information}")
    public ResponseEntity<String> changeByText(@PathVariable final String informationExist, @PathVariable String information) {
        loggerMain.info("start change a text");
        boolean checkError =service.changeByText(informationExist,information);
        if (checkError) {
            return new ResponseEntity<>("change a text", HttpStatus.OK); }
        else {
            loggerMain.error("text not found");
            return new ResponseEntity<>("change a text not complete", HttpStatus.NOT_FOUND); }
    }
    @GetMapping("/{information}")
    public ResponseEntity<String> findByInformation(@PathVariable final String information) {
        loggerMain.info("start find information");
        if (service.existByText(information)) {
            service.findByText(information);
            return new ResponseEntity<>("information is find", HttpStatus.OK);
        }
        else {
            loggerMain.error("information not found");
            return new ResponseEntity<>("information not found", HttpStatus.NOT_FOUND); }

    }
    @DeleteMapping("delete_text/{id}")
    public ResponseEntity<String> deleteText(@PathVariable final Long id) {
        loggerMain.info("start delete text");
        if (service.deleteText(id)) {
            return new ResponseEntity<>("Delete information", HttpStatus.OK); }
        else {
            loggerMain.error("text not found by id");
            return new ResponseEntity<>("information for delete not found", HttpStatus.NOT_FOUND); }
    }


}
