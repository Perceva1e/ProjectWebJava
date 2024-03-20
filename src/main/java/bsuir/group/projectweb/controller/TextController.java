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
public class TextController {
    /**
     * This is a server.
     */
    private TextService service;
    /**
     * This logger for info.
     */
    static final Logger LOGGER = LogManager.getLogger(TextController.class);

    /**
     * This method find all text.
     *
     * @return restore list of text
     */
    @GetMapping
    public List<Text> findAllText() {
        LOGGER.info("display all text");
        return service.findAllText();
    }

    /**
     * This method save text.
     *
     * @param information is an entity for save
     * @return restore text after save
     */
    @PostMapping("/save_information")
    public Text saveText(@RequestBody final Text information) {
        LOGGER.info("start sava a text");
        return service.saveText(information);
    }

    /**
     * This method make main task of service.
     *
     * @param information is an entity for hard code
     * @return restore text after hard code
     */
    @PostMapping("/find_phone_number_email")
    public Text findNumberPhoneAndEmail(@RequestBody final Text information) {
        LOGGER.info("start find telephone and email");
        return service.findNumberPhoneAndEmail(information);
    }

    /**
     * This method change by text.
     *
     * @param informationExist is a string for existing
     * @param information      is a string for change
     * @return restore http status
     */
    @PutMapping("change_information/{informationExist}/{information}")
    public ResponseEntity<String> changeByText(
            @PathVariable final String informationExist,
            @PathVariable final String information) {
        LOGGER.info("start change a text");
        boolean checkError = service.changeByText(
                informationExist, information);
        if (checkError) {
            return new ResponseEntity<>("change a text", HttpStatus.OK);
        } else {
            LOGGER.error("text not found");
            return new ResponseEntity<>(
                    "change a text not complete", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method find by information.
     *
     * @param information is a string for find
     * @return restore http status
     */
    @GetMapping("/{information}")
    public ResponseEntity<String> findByInformation(
            @PathVariable final String information) {
        LOGGER.info("start find information");
        if (service.findByText(information)) {
            return new ResponseEntity<>("information is find", HttpStatus.OK);
        } else {
            LOGGER.error("information not found");
            return new ResponseEntity<>(
                    "information not found",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method delete text.
     *
     * @param id is an id of entity for delete
     * @return restore http status
     */
    @DeleteMapping("delete_text/{id}")
    public ResponseEntity<String> deleteText(@PathVariable final Long id) {
        LOGGER.info("start delete text");
        if (service.deleteText(id)) {
            return new ResponseEntity<>("Delete information", HttpStatus.OK);
        } else {
            LOGGER.error("text not found by id");
            return new ResponseEntity<>(
                    "information for delete not found", HttpStatus.NOT_FOUND);
        }
    }
}
