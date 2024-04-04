package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.service.AuthorService;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


@Controller
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class AuthorController {
    /**
     * This is a logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(
            AuthorController.class);
    /**
     * This is a server.
     */
    private AuthorService service;

    /**
     * This method find author by parameters.
     *
     * @param lastName is a string for find
     * @param nameList is a list of string for find
     * @return restore list of author after find
     */
    @GetMapping("/find_author_parameters/{lastName}/{nameList}")
    public List<Author> findAuthorByParameters(
            @PathVariable final String lastName,
            @PathVariable final List<String> nameList) {
        LOGGER.info("start find author by lastname and name");
        return service.findAuthorByParameters(lastName, nameList);
    }

    /**
     * This method save an author.
     *
     * @param author is an entity for save
     * @return restore author after save
     */
    @PostMapping("/save_people")
    public ResponseEntity<String> saveAuthor(@RequestBody final Author author) {
        LOGGER.info("start save a author");
        boolean checkError = service.savePerson(author);
        if (checkError) {
            return new ResponseEntity<>("save a author", HttpStatus.OK);
        } else {
            LOGGER.error("author yet save ");
            return new ResponseEntity<>(
                    "author yet save in db", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method add author in text.
     *
     * @param informationExist is a string extisting
     * @param authorAdd        is an entity for add
     * @return restore http status
     */
    @PutMapping("add_author_text/{informationExist}")
    public ResponseEntity<String> addAuthorInText(
            @PathVariable final String informationExist,
            @RequestBody final Author authorAdd) {
        LOGGER.info("start add author in text");
        boolean checkError = service.addAuthorInText(
                informationExist, authorAdd);
        if (checkError) {
            return new ResponseEntity<>("add author in text", HttpStatus.OK);
        } else {
            LOGGER.error("text not found");
            return new ResponseEntity<>(
                    "add author in text not complete", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method delete connection author.
     *
     * @param idAuthor is an id of author for delete connection
     * @param idText   is an id of text from delete connection
     * @return restore http status
     */
    @DeleteMapping("delete_author_connection/{idAuthor}/{idText}")
    public ResponseEntity<String> deleteAuthorConnection(
            @PathVariable final Long idAuthor,
            @PathVariable final Long idText) {
        LOGGER.info("start delete author");
        boolean checkError = service.deleteAuthorConnection(idAuthor, idText);
        if (checkError) {
            return new ResponseEntity<>("Delete author", HttpStatus.OK);
        } else {
            LOGGER.error("author not found by id");
            return new ResponseEntity<>(
                    "author not found", HttpStatus.NOT_FOUND);
        }
    }
}
