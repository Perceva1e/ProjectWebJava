package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.dto.BulkTextRequestDTO;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.service.TextService;
import bsuir.group.projectweb.service.impl.CounterServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;

@Controller
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
     * @param model model for html
     * @return restore html page getText
     */
    @GetMapping()
    public String findAllText(final Model model) {
        LOGGER.info("start display all text");
        CounterServiceImpl.enhanceCounter();
        int numberOfRequest = CounterServiceImpl.getCounter();
        LOGGER.info("number of access to service is {}", numberOfRequest);
        List<Text> texts = service.findAllText();
        model.addAttribute("texts", texts);
        return "getText";
    }

    /**
     * This method show html page.
     *
     * @param model model for html
     * @return restore html page saveText
     */
    @GetMapping("/save_information")
    public String showSavePersonForm(final Model model) {
        Author author = new Author();
        model.addAttribute("information", new Text());
        model.addAttribute("author", author);
        return "saveText";
    }

    /**
     * This method save text.
     *
     * @param information is an entity for save
     * @param author      is entity for save
     * @return restore http status
     */
    @PostMapping("/save_information")
    public String saveText(
            @ModelAttribute final Text information,
            @ModelAttribute final Author author) {
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        information.setAuthors(authors);
        LOGGER.info("start save a text");
        boolean checkError = service.saveText(information);
        if (checkError) {
            return "successMethod";
        } else {
            LOGGER.error("text yet save ");
            return "errorMethod";
        }
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
     * This method show html page.
     *
     * @param model model for html
     * @return restore html page updateText
     */
    @GetMapping("/change_information")
    public String showChangeTextForm(final Model model) {
        model.addAttribute("informationExist", "");
        model.addAttribute("information", "");
        return "updateText";
    }

    /**
     * This method change by text.
     *
     * @param informationExist is a string for existing
     * @param information      is a string for change
     * @return restore http status
     */
    @PutMapping("/change_information")
    public String changeByText(
            @RequestParam("informationExist") final String informationExist,
            @RequestParam("information") final String information) {
        LOGGER.info("start change a text");
        boolean checkError = service.changeByText(
                informationExist, information);
        if (checkError) {
            return "successMethod";
        } else {
            LOGGER.error("text not found");
            return "errorMethod";
        }
    }

    /**
     * This method find by information.
     *
     * @param information is a string for find
     * @return restore http status
     */
    @GetMapping("/{information}")
    public ResponseEntity<String> findTextByInformation(
            @PathVariable final String information) {
        LOGGER.info("start find information");
        boolean checkError = service.findTextByInformation(information);
        if (checkError) {
            return new ResponseEntity<>("information is find", HttpStatus.OK);
        } else {
            LOGGER.error("information not found");
            return new ResponseEntity<>(
                    "information not found",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method show html page.
     *
     * @param model model for html
     * @return restore html page deleteAuthorInText
     */
    @GetMapping("/delete_author_in_text")
    public String showDeleteAuthorInTextForm(final Model model) {
        Long idAuthor = null;
        model.addAttribute("idAuthor", idAuthor);
        return "deleteAuthorInText";
    }

    /**
     * This method delete text.
     *
     * @param idAuthor is an idAuthor of entity for delete
     * @return restore http status
     */
    @DeleteMapping("delete_author_in_text")
    public String deleteAuthorInText(
            @RequestParam("idAuthor") final Long idAuthor) {
        LOGGER.info("start delete text");
        boolean checkError = service.deleteAuthorInText(idAuthor);
        if (checkError) {
            return "successMethod";
        } else {
            LOGGER.error("text not found by idAuthor");
            return "errorMethod";
        }
    }

    /**
     * This method save several text.
     *
     * @param bulkTextRequestDTO is a List of entity Text
     * @return restore http status
     */
    @PostMapping("bulk_insert_text")
    public ResponseEntity<String> bulkInsertText(
            @RequestBody final BulkTextRequestDTO bulkTextRequestDTO) {
        LOGGER.info("start bulk insert text");
        boolean checkError = service.saveBulkText(bulkTextRequestDTO);
        if (checkError) {
            return new ResponseEntity<>(
                    "Bulk insert information", HttpStatus.OK);
        } else {
            LOGGER.error("error Bulk insert information");
            return new ResponseEntity<>(
                    "Bulk insert information not execute",
                    HttpStatus.NOT_FOUND);
        }
    }
}
